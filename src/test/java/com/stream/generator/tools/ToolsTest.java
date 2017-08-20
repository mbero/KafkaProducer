package com.stream.generator.tools;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stream.generator.bms.SingleBMSReadRecord;

public class ToolsTest {

	@Test
	public void testGetJsonFromGivenObject() {
		SingleBMSReadRecord singleBMSRecord = new SingleBMSReadRecord("2018294", "some value", "some id", "readTag_id",
				"201203912");
		String jsonString = "";
		try {
			jsonString = Tools.getJSONStringFromGivenObject(singleBMSRecord);
		} catch (JsonProcessingException e) {
			System.out.println("Error during processing Object into JSON string");
			e.printStackTrace();
		}
		assertNotEquals("", jsonString);
	}

	@Test
	public void testGetMillisecondsFromBMSDateString() {
		try {
			String milliseconds = Tools.getMillisecondsFromBMSDateStringPrefixedByRandomUUID("2017-03-31 10:13:37.088000");
			assertTrue(milliseconds.equals("")!=true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetMillisecondsFromGivenDateString(){
		try {
			String milliseconds = Tools.getMilliSecondsFromGivenDateFormat("yyyy-MM-dd HH:mm:ss.SSS", "2017-03-31 10:13:37.088000");
			assertTrue(milliseconds.equals("")!=true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
