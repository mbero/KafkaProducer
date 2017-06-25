package com.stream.generator.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stream.generator.bms.BMSDataStreamGenerator;
import com.stream.generator.bms.SingleBMSReadRecord;
import com.stream.generator.kafka.KafkaProducer;
import com.stream.generator.tools.Tools;

public class Main {

	public static void main(String[] args) {
		if (args.length < 4) {
			System.err.println(
					"Parameters should consist of : <kafka_bootstrap_server_adress> <zookeper_adress> <topic_name> <direct_path_to_sqlite_db_file>");
			System.exit(1);
		}
		String kafkaBootstrapServerAdress = args[0]; // f.e "localhost:9092"
		String zookeperAdress = args[1]; // f.e //"localhost:2181"
		String topicName = args[2]; // f.e 'tweets-text'
		String directPathToSQLiteDBFile = args[3]; // "E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db"
		KafkaProducer kafkaProducer = new KafkaProducer();
		kafkaProducer.initializeProducer(kafkaBootstrapServerAdress, zookeperAdress);
		BMSDataStreamGenerator bmsDataStreamGenerator = new BMSDataStreamGenerator();
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory(directPathToSQLiteDBFile);
			for (int i = 0; i < 100000000; i++) {
				SingleBMSReadRecord singleBMSReadRecord = bmsDataStreamGenerator
						.getRandomRecordFromWholeListOfBMSReadRecords();
				String singleBMSRecordString = Tools.getJSONStringFromGivenObject(singleBMSReadRecord);
				kafkaProducer.produceMessage(topicName, String.valueOf(i), singleBMSRecordString);
				System.out.println(singleBMSRecordString);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		kafkaProducer.closeProducer();
	}

}
