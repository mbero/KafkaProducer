package com.stream.generator.bms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

import com.stream.generator.sqlite.SQLiteDBClient;

public class BMSDataStreamGenerator {

	public static ArrayDeque<SingleBMSReadRecord> listOfReadRecord;

	public void putAllDataFromTrendTableIntoMemory() throws Exception {
		SQLiteDBClient sqliteDBClient = new SQLiteDBClient();
		ResultSet rs = null;
		listOfReadRecord = new ArrayDeque<SingleBMSReadRecord>();
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

}
