package com.stream.generator.main;

import com.stream.generator.kafka.KafkaProducer;

public class Main {

	public static void main(String[] args) {
		KafkaProducer kafkaProducer = new KafkaProducer();
		kafkaProducer.initializeProducer();
		for (int i = 0; i < 100000; i++) {
			kafkaProducer.produceMessage("tweets-text", String.valueOf(i), String.valueOf(i));
		}
		kafkaProducer.closeProducer();
	}

}
