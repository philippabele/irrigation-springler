kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic daily --property value.deserializer=org.apache.kafka.common.serialization.DoubleDeserializer