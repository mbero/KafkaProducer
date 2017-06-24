package com.stream.generator.bms;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BMSDataStreamGeneratorTest {

	private BMSDataStreamGenerator bmsDataStreamGenerator;

	@Before
	public void setUp() throws Exception {
		bmsDataStreamGenerator = new BMSDataStreamGenerator();
	}

	@Test
	public void testputAllDataromTrendTableInMemory() {
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory();
			List<SingleBMSReadRecord> allRecordsFromTestTable = bmsDataStreamGenerator.getWholeListOfBMSReadRecords();
			assertTrue(allRecordsFromTestTable.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getRandomRecordFromWholeListOfBMSReadRecords() {
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory();
			SingleBMSReadRecord singleBMSRecord = bmsDataStreamGenerator.getRandomRecordFromWholeListOfBMSReadRecords();
			assertNotNull(singleBMSRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
