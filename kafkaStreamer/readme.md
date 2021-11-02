
- create the necessary streams

`kafka-topics.sh --bootstrap-server localhost:9092 --create --topic input-stream --partitions 1 --replication-factor 1`
`kafka-topics.sh --bootstrap-server localhost:9092 --create --topic output-stream --partitions 1 --replication-factor 1`

# perpare for testing

- start a producer
`kafka-console-producer.sh --bootstrap-server localhost:9092 --tocalhost:9092 --topic input-stream`

- start the spring app
Hit F5 inside VS Code to in debugging mode

- start the consumer
`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic output-stream --from-beginning`

# run test

- enter message in producer console
- see message in consumer console 