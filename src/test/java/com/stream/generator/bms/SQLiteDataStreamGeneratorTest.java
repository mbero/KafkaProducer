package com.stream.generator.bms;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.stream.generator.bms.sqlite.StreamDataObjectsGenerator;

import static org.junit.Assert.*;

public class SQLiteDataStreamGeneratorTest {

	private StreamDataObjectsGenerator bmsDataStreamGenerator;

	@Before
	public void setUp() throws Exception {
		bmsDataStreamGenerator = new StreamDataObjectsGenerator();
	}
	
	@Test
	public void testGetListOfSingleBMSRecordsFromProperJSONFile(){
		try {
			List<SingleBMSReadRecord> bmsRecordReads = bmsDataStreamGenerator.getListOfSingleBMSRecordsFromProperJSONFile("E:\\PCSS\\bms_analytics_workspace\\StreamGenerator\\tests\\resultsJSONFile.json");
			assertTrue(bmsRecordReads.size()>0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testputAllDataromTrendTableInMemory() {
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory("E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db");
			List<SingleBMSReadRecord> allRecordsFromTestTable = bmsDataStreamGenerator.getWholeListOfBMSReadRecords();
			assertTrue(allRecordsFromTestTable.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getRandomRecordFromWholeListOfBMSReadRecords() {
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory("E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db");
			SingleBMSReadRecord singleBMSRecord = bmsDataStreamGenerator.getRandomRecordFromWholeListOfBMSReadRecords();
			assertNotNull(singleBMSRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
