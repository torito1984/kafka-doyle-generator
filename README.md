Generator of data for kafka - Pushing Sherlock Holmes histories
===============================================================

This project is intended to produce some messages to show capabilities of some streaming systems. See
- Spark https://github.com/torito1984/spark-doyle.git
- Storm https://github.com/torito1984/trident-doyle.git
- Flink https://github.com/torito1984/flink-sherlock.git
- Samza https://github.com/torito1984/samza-locations.git

The code picks random passages from Doyle stories in doyle.txt and pushes these passages to Kafka.

In order to run the producer execute runDoyleFeed.sh giving as arguments the location of the stories file and the topic where
to publish. It assumes that a Kafka instalation is avaible and listening in localhost:6667. It has been tested with 
Kafka 0.9.0.4 available with Hortonworks HDP 2.4.0.0.
