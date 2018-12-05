# Blockchaintest
import hashlib
import lorem
import time

class BlockChain:
    def __init__(self):
        self.blocks = []
    
    def __str__(self):
        s = ""
        for i, b in enumerate(self.blocks):
            s = s + "\n\n" + str(i) + ":" + str(b)
        return s
    
    def appendBlock(self, block):
        self.blocks.append(block)

    def isChainValid(self):
        if len(self.blocks) < 2:
            return True
        for t in zip(self.blocks, self.blocks[1:]):
            if not t[0].shaHash() == t[1].lastHash:
                return False
                
        return self.isLastValid()

    def isLastValid(self):
        if len(self.blocks) < 2:
            return True
        return self.blocks[-2].shaHash() == self.blocks[-1].lastHash and self.blocks[-1].checkSolution()

class Block:
    def incrementAndHash(self):
        self.nonce += 1
        self.hash = self.shaHash()
        return self.hash 

    def checkSolution(self):
        return (not len(self.hash) == 0) and self.hash.startswith("b0b")

    def shaHash(self):
        self.hash = hashlib.sha256((str(self.messages) + str(self.lastHash) + str(self.nonce)).encode('utf-8')).hexdigest()
        return self.hash

    def __str__(self):
        return "[\n\tlastHash: {lastHash}\n\thash: {hash}\n\tnonce: {nonce}\n\tmessages: {messages}\n]".format(lastHash = self.lastHash, hash=self.hash, nonce=self.nonce, messages=self.messages)
        
    def __init__(self, lastHash):
        self.messages = []
        
        self.hash = ""
    
        
        self.lastHash = lastHash
        self.nonce = 0
        #transactions.append(Transaction())

class Transaction:
    def __init__(self):
        self.inputs = []
        self.receiver = None
    
def main():
    chain = BlockChain()
    block1 = Block("First Block. N/A")
    block1.messages.append("Blah. This should affect the hash value")
    newBlock = Block(block1.shaHash())
    newBlock. messages.append(lorem.sentence())
    
    chain.appendBlock(block1)
    chain.appendBlock(newBlock)
    
    print(chain)
    
    while True:
        print("~~~~~~~~~~~~~---MINING---~~~~~~~~~~~~~~~")
    
        while not chain.isChainValid(): 
           newBlock.incrementAndHash()
           print(newBlock.hash, flush=True)
        
        print(chain, flush=True)
        
        
        time.sleep(3)
        
        newBlock = Block(newBlock.shaHash())
        newBlock.messages.append(lorem.sentence())
        chain.appendBlock(newBlock)
        
        
print("FUCK")
main()