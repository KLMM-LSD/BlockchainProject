#!/bin/bash

echo "Create nodes on 2000, 2010, 2020, 2030, 2040"

PORT=2000 node bcnode.js > /dev/null &
PORT=2010 node bcnode.js > /dev/null &
PORT=2020 node bcnode.js > /dev/null &
PORT=2030 node bcnode.js > /dev/null &
PORT=2040 node bcnode.js > /dev/null &

echo "Create miners on 3000, 3010"
PORT=3000 node bcminer.js > /dev/null &
PORT=3010 node bcminer.js > /dev/null &

sleep 2
echo "Let 2000 know all nodes"
echo "------------------------------"

curl -X POST "http://localhost:2000/discover"	\
	-H "Content-Type: application/json"	\
	-d '{"nodes": ["http://localhost:2000", "http://localhost:2010", "http://localhost:2020", "http://localhost:2030", "http://localhost:2040"], "miners": []}'
echo
echo "------------------------------"
sleep 2

echo "Which peers does 2010 know?"
echo "------------------------------"
curl "http://localhost:2010"
echo
echo "------------------------------"
echo "Know the miners"

echo "------------------------------"
curl -X POST "http://localhost:2010/discover"	\
	-H "Content-Type: application/json"	\
	-d '{"nodes": [], "miners": ["http://localhost:3000", "http://localhost:3010"]}'
echo
echo "------------------------------"
sleep 10

echo "Post a LOREM transaction"

echo "------------------------------"
curl -X POST "http://localhost:3000/transaction"	\
	-H "Content-Type: application/json"		\
	-d '{"Something": "LOREM"}'
echo
curl -X POST "http://localhost:3010/transaction"	\
	-H "Content-Type: application/json"		\
	-d '{"Something": "LOREM"}'
echo
echo "------------------------------"

wait

