
# Read the daily topic

kafka-console-consumer.sh --bootstrap-server localhost:9092 \
--topic daily \
--property key.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer
--property value.deserializer=org.apache.kafka.common.serialization.DoubleDeserializer \
--property print.key=true \
--delimiter " - "


kafka-topics.sh --bootstrap-server localhost:9092 --topic history --delete

kafka-topics.sh --bootstrap-server localhost:9092 --list









