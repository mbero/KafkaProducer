package com.stream.generator.bms.sqlite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stream.generator.bms.SingleBMSReadRecord;
import com.stream.generator.client.sqlite.SQLiteDBClient;

public class StreamDataObjectsGenerator {

	private static ArrayList<SingleBMSReadRecord> listOfReadRecord;

	/**
	 * 
	 * @param bmsSQLiteDBPath
	 * @throws Exception
	 */
	public void putAllDataFromTrendTableIntoMemory(String bmsSQLiteDBPath) throws Exception {
		SQLiteDBClient sqliteDBClient = new SQLiteDBClient();
		ResultSet rs = null;
		listOfReadRecord = new ArrayList<SingleBMSReadRecord>();
		Connection connection = sqliteDBClient.getConnection(bmsSQLiteDBPath);
		try {
			rs = sqliteDBClient.getResultSetFromGivenSelectQuery(connection, "SELECT * FROM TREND");
			while (rs.next()) {
				String readDate = rs.getString("DATETIME");
				String readValue = rs.getString("VALUE");
				String readIOdev_id = rs.getString("IODEV_ID");
				String readTag_id = rs.getString("TAG_ID");
				SingleBMSReadRecord singleBMSReadRecord = new SingleBMSReadRecord(readDate, readValue, readIOdev_id,
						readTag_id);
				listOfReadRecord.add(singleBMSReadRecord);
			}
			rs.close();
			rs.clearWarnings();
			sqliteDBClient.closeStatement();
			sqliteDBClient.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private <T> T getRandomItem(List<T> list) {
		Random random = new Random();
		int listSize = list.size();
		int randomIndex = random.nextInt(listSize);
		return list.get(randomIndex);
	}

	/**
	 * Returns given amount of randomly selected items from given list of whole
	 * BMSReadRecords objects. Important : random elements could be repeated
	 * depends on amountOfRecords parameter !
	 * 
	 * @param wholeListOfBMSReadRecords
	 * @param amountOfRecords
	 * @return List<SingleBMSReadRecord> list of randomly selected items.
	 */
	public List<SingleBMSReadRecord> getGivenAmountOfRandomRecordsFromWholeListOfBMSReadRecords(
			List<SingleBMSReadRecord> wholeListOfBMSReadRecords, int amountOfRecords) {
		List<SingleBMSReadRecord> listOfRandomItems = new ArrayList<SingleBMSReadRecord>();
		Random random = new Random();
		int listSize = wholeListOfBMSReadRecords.size();
		for (int i = 0; i < amountOfRecords; i++) {
			int randomIndex = random.nextInt(listSize);
			SingleBMSReadRecord singleBMSReadRecord = wholeListOfBMSReadRecords.get(randomIndex);
			listOfRandomItems.add(singleBMSReadRecord);
		}

		return listOfRandomItems;
	}

	public SingleBMSReadRecord getRandomRecordFromWholeListOfBMSReadRecords() {
		SingleBMSReadRecord randomRecord = getRandomItem(listOfReadRecord);
		return randomRecord;
	}

	public ArrayList<SingleBMSReadRecord> getWholeListOfBMSReadRecords() {
		return listOfReadRecord;
	}
	

	public List<SingleBMSReadRecord> getListOfSingleBMSRecordsFromProperJSONFile(String fullPathToJSONFile)
			throws JsonParseException, JsonMappingException, IOException, ParseException, ClassNotFoundException {
		List<SingleBMSReadRecord> bmsReadRecordsList = new ArrayList<SingleBMSReadRecord>();
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(fullPathToJSONFile));
		String stringifiedJsonObject = object.toString();
		JSONArray array = (JSONArray)parser.parse(stringifiedJsonObject);
		for(int i=0; i<array.size(); i++){
			
			org.json.simple.JSONObject currentJSONObject = (JSONObject) array.get(i);
			//{"readValue":"22.0","readIOdev_id":"1","readTag_id":"1","readDate":"2017-03-31 10:13:37.088000"}
			SingleBMSReadRecord singleBMSReadRecord = new SingleBMSReadRecord(
					currentJSONObject.get("readDate").toString(), 
					currentJSONObject.get("readValue").toString(), 
					currentJSONObject.get("readIOdev_id").toString(), 
					currentJSONObject.get("readTag_id").toString()
			);
			bmsReadRecordsList.add(singleBMSReadRecord);
		}
		
		return bmsReadRecordsList;
	}

	
}
