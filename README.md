A note on testing
Executing performance tests on local dev machine activemq and mongo instance, can handle 20,000 trade messages a second (at least that's as far as I've tested).
Executing same test on server, a much less powerful machine (vps) handles about 4000 trade messages a second
Trying load test over internet, I can't produce enough messages per second to properly test via my load tester (see after). 

Can load test system (minus rest front end) with following maven goal once the consumer app has been started. Modify host/port.
mvn activemq-perf:producer -Dfactory.brokerURL=tcp://host:port -Dproducer.destName=queue://CurFairQ -Dproducer.sendDuration=60000

Handling speed could be further improved by using bulk db inserts, multiple consumers, changing some config options on activemq like prefetch, acknowledgement, ..

Front end is just a simple page displaying results as they were received
  