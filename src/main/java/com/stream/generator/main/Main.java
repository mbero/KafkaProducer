package com.stream.generator.main;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stream.generator.bms.SingleBMSReadRecord;
import com.stream.generator.bms.sqlite.StreamDataObjectsGenerator;
import com.stream.generator.kafka.KafkaProducer;
import com.stream.generator.tools.Tools;

public class Main {
	
	public static void main(String[] args) {
		if (args.length < 6) {
			System.err.println("Parameters should consist of : "  
					+ "<kafka_bootstrap_server_adress> "
					+ "<zookeper_adress> (NONE if dont wanna use Zookeper parameter)" 
					+ "<topic_name>"
					+ "<type_of_data_source> - JSON or SQLITE"
					+ "<path_to_data_source> (full path to proper JSON file or SQLITE.DB file"
					+ "<time_between_meessages> - in seconds, for example : 1,2 etc");
			System.exit(1);
		}

		String kafkaBootstrapServerAdress = args[0]; // f.e "localhost:9092"
		String zookeperAdress = args[1]; // f.e //"localhost:2181"
		String topicName = args[2]; // f.e 'tweets-text'
		String typeOfDataSource = args[3];
		String pathToDataSource = args[4];
		String timeBetweenMessages = args[5];
		Integer timeBetweenMessagesInt = Integer.valueOf(timeBetweenMessages);

		KafkaProducer kafkaProducer = new KafkaProducer();
		kafkaProducer.initializeProducer(kafkaBootstrapServerAdress, zookeperAdress);
		StreamDataObjectsGenerator bmsDataStreamGenerator = new StreamDataObjectsGenerator();
		List<SingleBMSReadRecord> bmsDataRecords = null;
		try {
			if (typeOfDataSource.equals("JSON")) {
				bmsDataRecords = bmsDataStreamGenerator.getListOfSingleBMSRecordsFromProperJSONFile(pathToDataSource);
			} else if (typeOfDataSource.equals("SQLITE")) {
				bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory(pathToDataSource);
				bmsDataRecords = bmsDataStreamGenerator.getWholeListOfBMSReadRecords();
			}

		} catch (ClassNotFoundException | IOException | ParseException e) {
			System.out.println("Overall type exception during ");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SingleBMSReadRecord> randomlySelectedBMSReadRecords = bmsDataStreamGenerator
				.getGivenAmountOfRandomRecordsFromWholeListOfBMSReadRecords(bmsDataRecords, 10000000);
		int i = 0;
		for (SingleBMSReadRecord singleBMSRead : randomlySelectedBMSReadRecords) {
			i += 1;
			String singleBMSRecordString = null;
			try {
				singleBMSRecordString = Tools.getJSONStringFromGivenObject(singleBMSRead);
				kafkaProducer.produceMessage(topicName, String.valueOf(i), singleBMSRecordString);
				System.out.println(singleBMSRecordString);
				TimeUnit.SECONDS.sleep(timeBetweenMessagesInt);
			} catch (JsonProcessingException e) {
				System.out.println("JsonProcessingException - error during getting JSON from single BMS read object");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("JsonProcessingException - error during getting JSON from single BMS read object");
				e.printStackTrace();
			}
		}
		kafkaProducer.closeProducer();
	}

}