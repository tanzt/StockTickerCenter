
StockTickerCenter
=================
Stock Exchange Information Center demo by java

 Project Function: Stock Exchange Information Center demo by java myeclipse IDE:
 * 1.Generate serveral Stock information demo data and Exchange information demo data continuously 
 * 2.send the stock information and exchange information to Stock Router Middleware Center by Asynchronous Socket java mina;

in the src/main/java/com package:

1.util: some tools ,[mina] file contains  some tools for mina communications ,such as Json parse tool , Random class; [filter] file contains the filter for the Mina ,such as character encoder and decoder; 

2.stockinfo: stock information generation . generate some stock inforamtion demo data and stock Exchange information demo data; 

3.stockcenter: send the stock information and exchange information to Stock Router Middleware Center by Asynchronous Socket java mina;


use step:
step1：mvn compile
step2: mvn test   or   mvn exec:java -Dexec.mainClass=com.stockcenter.StockMinaServer


you will see "[log server]:Mina server is listing port:8891" , congratulations!!!

next step is to run StockRouter project . 

Note: when there is at least one client connected to this server on Port 8891, it will start send the data; 
