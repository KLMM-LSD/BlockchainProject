"use strict";

const express		= require("express");
const md5		= require("md5");
const request		= require("request");
const bodyparser	= require("body-parser");
const app		= express();

const genesis		= "00000000000000000000000000000000";
const proof		= "000";
const rate		= process.env.RATE ? process.env.RATE
					   : 1000;
let latest_hash		= genesis;
let latest_len		= 0;

let commitset = [];
let peers = {
	known_nodes:	[],
	known_miners:	[],
};
let port = process.env.PORT ? process.env.PORT
			    : 3000;

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

			if (body["len"] > latest_len) {
				latest_hash = body["hash"];
				latest_len = body["len"];
				commitset = [];
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
			if (body["len"] > latest_len) {
				latest_hash = body["hash"];
				latest_len = body["len"];
				commitset = [];
			}
		})
	});
};

app.get("/", (req, res) => {
	res.send(`I am a blockchain miner!
		<h1>Peers</h1>
		${JSON.stringify(peers)}<h1>Commitset</h1>
		${JSON.stringify(commitset)}`
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
		}, 5000);
	}

	res.send(peers);
});

app.post("/transaction", (req, res) => {
	commitset.push(req.body);
	res.send("Received");
});

app.post("/block", (req, res) => {
	let hash = req.body["hash"];
	if (hash.startsWith(proof) &&
		hash == md5(JSON.stringify(req.body["content"]))) {

		commitset = [];
		latest_hash = hash;
		latest_len = req.body["content"]["len"];
		res.send("Acknowledged");
		return;
	} else {
		res.send("Wait a sec");
	}
});

app.listen(port, (err) => {
	if (err)
		return console.log(`Oops! ${err}`);

	setInterval(() => {
		let i = 0;
		let cur =  {
			content: {
				prevhash:	latest_hash,
				transactions:	commitset,
				len:		latest_len + 1,
				nonce:		Math.floor(Math.random() * 100000)
			}
		};

		while (i != rate) {
			let hash = md5(JSON.stringify(cur["content"]));
			if (!hash.startsWith(proof)) {
				i++;
				cur["content"]["nonce"]++;
				continue;
			}
			cur["hash"] = hash;
			console.log("Whoa!");
			console.log(cur);
			broadcast_peers("/block", cur);
			commitset = [];
			break;
		}
	}, 1000);
});

