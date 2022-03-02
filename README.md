#arweave
Arweave is a distributed, cryptographically verified permanent archive built on a cryptocurrency that aims to, for the first time, provide feasible data permanence. By leveraging our novel Blockweave datastructure, data is stored in a decentralised, peer-to-peer manner where miners are incentivised to store rare data.


# arlink
this is the arlink 

arlink is java verison of goar:  https://github.com/everFinance/goar

any code commit or suggestion is welcome..









#### Client [80%]
read the testing sample in ClientTest.

- [x] GetInfo
- [x] GetTransactionByID
- [x] GetTransactionStatus
- [x] GetTransactionField
- [x] GetTransactionData
- [x] GetTransactionPrice
- [x] GetTransactionAnchor
- [x] SubmitTransaction
- [x] Arql(Deprecated)
- [x] GraphQL
- [x] GetWalletBalance
- [x] GetLastTransactionID
- [x] GetBlockByID
- [x] GetBlockByHeight
- [x] BatchSendItemToBundler
- [x] GetBundle
- [x] GetTxDataFromPeers
- [x] BroadcastData




#### Wallet  [60%]
read the samples in WalletTest.

- [x] SendAR
  -       wallet.setClientUrl("http://178.62.222.154:1984");
          String str = wallet.SendAr(amount, target, tags);
          System.out.println("the transaction id is " + str);
- 
- [x] SendARSpeedUp
- [x] SendWinston
- [x] SendWinstonSpeedUp
- [x] SendData
- [x] SendDataSpeedUp
- [x] SendTransaction
- [x] CreateAndSignBundleItem
- [x] SendBundleTxSpeedUp
- [x] SendBundleTx
- [x] SendPst



#### Utils [80%]
Read the samples in Test


- [x] Base64Encode
- [x] Base64Decode
- [x] Sign
- [x] Verify
- [x] DeepHash
- [x] GenerateChunks
- [x] ValidatePath
- [x] OwnerToAddress
- [x] OwnerToPubKey
- [x] TagsEncode
- [x] TagsDecode
- [x] PrepareChunks
- [x] GetChunk
- [x] SignTransaction
- [x] GetSignatureData
- [x] VerifyTransaction
- [x] NewBundle


============================================================

