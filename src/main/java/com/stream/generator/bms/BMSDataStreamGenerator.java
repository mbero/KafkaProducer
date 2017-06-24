package com.stream.generator.bms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.stream.generator.sqlite.SQLiteDBClient;

public class BMSDataStreamGenerator {

	private static ArrayList<SingleBMSReadRecord> listOfReadRecord;

	public void putAllDataFromTrendTableIntoMemory() throws Exception {
		SQLiteDBClient sqliteDBClient = new SQLiteDBClient();
		ResultSet rs = null;
		listOfReadRecord = new ArrayList<SingleBMSReadRecord>();
		Connection connection = sqliteDBClient
				.getConnection("E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db");
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

	public SingleBMSReadRecord getRandomRecordFromWholeListOfBMSReadRecords() {
		SingleBMSReadRecord randomRecord = getRandomItem(listOfReadRecord);
		return randomRecord;
	}

	public ArrayList<SingleBMSReadRecord> getWholeListOfBMSReadRecords() {
		return listOfReadRecord;
	}

}
