package com.stream.generator.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tools {

	/**
	 * Transforms given simple object (POJO) for JSON String
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String getJSONStringFromGivenObject(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(object);
		return jsonInString;
	}

	/**
	 * Returns milliseconds as String from date in given format
	 * 
	 * @param dateFormat
	 * @param date
	 * @return String
	 * @throws ParseException 
	 */
	public static String getMillisecondsFromBMSDateStringPrefixedByRandomUUID(String date) throws ParseException {
		//String[] cuttedDate = date.split(".");
		String cuttedDate = date.substring(0,date.length()-7);
		String millisecondsPart = date.substring(date.length()-7, date.length());//cuttedDate[1];
		String cuttedMillisecondsPart = millisecondsPart.substring(0, 4);
		String properlyFormattedDate = cuttedDate  + cuttedMillisecondsPart;
		String millisecondsFromProperlyFormattedString = getMilliSecondsFromGivenDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
				properlyFormattedDate);
		 String uuid = UUID.randomUUID().toString();

		return uuid + "_" +millisecondsFromProperlyFormattedString;
	}

	public static String getMilliSecondsFromGivenDateFormat(String dateFormat, String date) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		Date d = formatter.parse(date);
		long timestamp = d.getTime();
		String timestampString = String.valueOf(timestamp);

		return timestampString;
	}
}
