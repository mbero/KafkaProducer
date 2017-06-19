package com.stream.generator.main;

import com.stream.generator.kafka.KafkaProducer;

import kafka.Kafka;

public class Main {

	public static void main(String[] args) {
		KafkaProducer kafkaProducer = new KafkaProducer();
		kafkaProducer.test();
	}

}
