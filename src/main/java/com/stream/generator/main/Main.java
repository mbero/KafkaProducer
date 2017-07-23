package com.stream.generator.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stream.generator.bms.SingleBMSReadRecord;
import com.stream.generator.bms.sqlite.StreamDataObjectsGenerator;
import com.stream.generator.kafka.KafkaProducer;
import com.stream.generator.tools.Tools;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 5) {
			System.err.println(
					"Parameters should consist of : <kafka_bootstrap_server_adress> <zookeper_adress> <topic_name> <direct_path_to_sqlite_db_file> <time_between_messages_in_seconds>");
			System.exit(1);
		}
		String kafkaBootstrapServerAdress = args[0]; // f.e "localhost:9092"
		String zookeperAdress = args[1]; // f.e //"localhost:2181"
		String topicName = args[2]; // f.e 'tweets-text'`
		String directPathToSQLiteDBFile = args[3]; // "E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db"
		KafkaProducer kafkaProducer = new KafkaProducer();
		kafkaProducer.initializeProducer(kafkaBootstrapServerAdress, zookeperAdress);
		StreamDataObjectsGenerator bmsDataStreamGenerator = new StreamDataObjectsGenerator();
		ArrayList<SingleBMSReadRecord> bmsDataRecords = null;
		try {
			bmsDataRecords = bmsDataStreamGenerator.getListOfSingleBMSRecordsFromProperJSONFile("/home/centos/Desktop/StreamGenerator/tests/resultsJSONFile.json");
			bmsDataStreamGenerator.setListOfReadRecord(bmsDataRecords);
		} 
		catch (ClassNotFoundException | IOException | ParseException e) {
			e.printStackTrace();
		}
		int i = 0;
		while (i<Integer.MAX_VALUE) {
			i += 1;
			String singleBMSRecordString = null;
			try {
				SingleBMSReadRecord singleBMSRecord = bmsDataStreamGenerator.getRandomRecordFromWholeListOfBMSReadRecords();
				singleBMSRecordString = Tools.getJSONStringFromGivenObject(singleBMSRecord);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			kafkaProducer.produceMessage(topicName, String.valueOf(i), singleBMSRecordString);
			System.out.println(singleBMSRecordString);
			TimeUnit.SECONDS.sleep(Integer.valueOf(args[4]));
		}
		kafkaProducer.closeProducer();
	}
}
