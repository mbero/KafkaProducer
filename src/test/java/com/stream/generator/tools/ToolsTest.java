package com.stream.generator.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stream.generator.bms.SingleBMSReadRecord;

public class ToolsTest {

	private Tools tools;

	@Before
	public void setUp() throws Exception {
		tools = new Tools();
	}

	@Test
	public void testGetJsonFromGivenObject() {
		SingleBMSReadRecord singleBMSRecord = new SingleBMSReadRecord("2018294", "some value", "some id", "readTag_id");
		String jsonString = "";
		try {
			jsonString = Tools.getJSONStringFromGivenObject(singleBMSRecord);
		} catch (JsonProcessingException e) {
			System.out.println("Error during processing Object into JSON string");
			e.printStackTrace();
		}
		assertNotEquals("", jsonString);
	}

}
