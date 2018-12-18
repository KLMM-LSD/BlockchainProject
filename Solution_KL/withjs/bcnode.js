"use strict";

const express		= require("express");
const md5		= require("md5");
const request		= require("request");
const bodyparser	= require("body-parser");
const app		= express();

const genesis		= "00000000000000000000000000000000";
const proof		= "000";

let longest_hash	= genesis;
let longest_len		= 0;

let peers = {
	known_nodes:	[],
	known_miners:	[],
};
let blockchain = {};
let port = process.env.PORT ? process.env.PORT
			    : 2000;

app.use(bodyparser.urlencoded({extended: true}));
app.use(bodyparser.json());

let broadcast_peers = (endpoint, body) => {
	peers.known_nodes.forEach((cur) => {
		request.post(`${cur}${endpoint}`, {
			json: body
		}, (err, res, body) => {
			if (err) {
				console.log(`Some error: ${err}, remove ${cur} from known_nodes`);
				let idx = peers.known_nodes.indexOf(cur);
				peers.known_nodes.splice(idx, 1);
			}
		})
	});

	peers.known_miners.forEach((cur) => {
		request.post(`${cur}${endpoint}`, {
			json: body
		}, (err, res, body) => {
			if (err) {
				console.log(`Some error: ${err}, remove ${cur} from known_miners`);
				let idx = peers.known_miners.indexOf(cur);
				peers.known_miners.splice(idx, 1);
			}
		})
	});
};

app.get("/", (req, res) => {
	res.send(`I am a blockchain node!
		<h1>Peers</h1>
		${JSON.stringify(peers)}<h1>Blockchain</h1>
		${JSON.stringify(blockchain)}`
	);
});

app.post("/discover", (req, res) => {
	let added = 0;
	req.body["nodes"].forEach((n) => {
		if (!peers.known_nodes.includes(n)) {
			peers.known_nodes.push(n);
			added++;
		}
	});
	req.body["miners"].forEach((n) => {
		if (!peers.known_miners.includes(n)) {
			peers.known_miners.push(n);
			added++;
		}
	});

	if (added) {
		setTimeout(() => {
			broadcast_peers("/discover", {
				nodes: peers.known_nodes,
				miners: peers.known_miners
			});
		}, 1000);
	}

	res.send(peers);
});

app.post("/block", (req, res) => {
	console.log(req.body);

	let hash = req.body["hash"];
	let prevhash = req.body["content"]["prevhash"];
	let thelen = req.body["content"]["len"];

	if (!hash.startsWith(proof)) {
		console.log("startswith");
		res.send({
			"hash": longest_hash,
			"len": longest_len
		});
		return;
	}

	if (!hash == md5(JSON.stringify(req.body["content"]))) {
		res.send({
			"hash": longest_hash,
			"len": longest_len
		});
		console.log("mismatch");
		return;
	}

	blockchain[hash] = req.body;
	if (thelen == longest_len + 1) {
		longest_hash = hash;
		longest_len = thelen;
	}

	res.send({
		"hash": longest_hash,
		"len": longest_len
	});
});

app.listen(port, (err) => {
	if (err)
		return console.log(`Oops! ${err}`);
});

