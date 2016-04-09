#/usr/bin/bash

java -cp target/kafka-stream-example-0.0.1.jar org.kafka.stream.example.launch.DoyleProducerDemo --textFile ${1} --topic ${2}