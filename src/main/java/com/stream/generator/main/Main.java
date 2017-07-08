package com.stream.generator.main;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stream.generator.bms.SingleBMSReadRecord;
import com.stream.generator.bms.sqlite.StreamDataObjectsGenerator;
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
		StreamDataObjectsGenerator bmsDataStreamGenerator = new StreamDataObjectsGenerator();
		List<SingleBMSReadRecord> bmsDataRecords = null;
		try {
			bmsDataRecords = bmsDataStreamGenerator.getListOfSingleBMSRecordsFromProperJSONFile(
					"E:\\PCSS\\bms_analytics_workspace\\StreamGenerator\\tests\\resultsJSONFile.json");
		} catch (ClassNotFoundException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SingleBMSReadRecord> randomlySelectedBMSReadRecords = bmsDataStreamGenerator
				.getGivenAmountOfRandomRecordsFromWholeListOfBMSReadRecords(bmsDataRecords, 100000);
		int i = 0;
		for (SingleBMSReadRecord singleBMSRead : randomlySelectedBMSReadRecords) {
			i += 1;
			// SingleBMSReadRecord singleBMSReadRecord =
			// bmsDataStreamGenerator.getRandomRecordFromWholeListOfBMSReadRecords();
			String singleBMSRecordString = null;
			try {
				singleBMSRecordString = Tools.getJSONStringFromGivenObject(singleBMSRead);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kafkaProducer.produceMessage(topicName, String.valueOf(i), singleBMSRecordString);
			System.out.println(singleBMSRecordString);
		}
		kafkaProducer.closeProducer();
	}

}
