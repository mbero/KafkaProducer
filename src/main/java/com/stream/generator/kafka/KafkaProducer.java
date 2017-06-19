package com.stream.generator.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducer {

	private Producer<String, String> producer;

	public KafkaProducer(boolean configureProducer) {
		if (configureProducer) {
			configureProducer();
		}
	}

	public KafkaProducer() {
	}

	public void initializeProducer() {
		configureProducer();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void configureProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("zookeper.connect", "localhost:2181");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		producer = new org.apache.kafka.clients.producer.KafkaProducer(props);
	}

	/**
	 * Sends message using Kafka Producer API
	 * 
	 * @param topicName
	 * @param key
	 * @param value
	 */
	public void produceMessage(String topicName, String key, String value) {
		producer.send(new ProducerRecord<String, String>(topicName, key, value));
	}

	/**
	 * Closes
	 */
	public void closeProducer() {
		producer.close();
	}
}
