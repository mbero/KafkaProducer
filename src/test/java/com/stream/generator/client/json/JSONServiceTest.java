package com.stream.generator.client.json;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.stream.generator.bms.SingleBMSReadRecord;
import com.stream.generator.bms.sqlite.StreamDataObjectsGenerator;

public class JSONServiceTest {

	private String jsonFilePath;
	private String sqliteDBFilePath;
	private JSONService jsonService;

	@Before
	public void prepareJsonService() {
		jsonFilePath = "E:\\PCSS\\bms_analytics_workspace\\StreamGenerator\\tests\\resultsJSONFile.json";
		sqliteDBFilePath = "E:\\PCSS\\bms_analytics_workspace\\StreamGenerator\\BMS_DB.db";
	}

	@Test
	public void testSaveJSONObjectToFile() {
		StreamDataObjectsGenerator bmsDataStreamGenerator = new StreamDataObjectsGenerator();
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory(sqliteDBFilePath);
			jsonService = new JSONService();
			jsonService.saveJSONObjectToFile(jsonFilePath, bmsDataStreamGenerator.getWholeListOfBMSReadRecords());
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(false);
	}

}
