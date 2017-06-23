package com.stream.generator.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDBClient {
	
	/**
	 * 
	 * @param sqliteDBFilePath  : String is proper path for SQLite Database file
	 * F.Example : f.e E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db
	 * @return Connection object 
	 */
	public Connection getConnection(String sqliteDBFilePath) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:".concat(sqliteDBFilePath);
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

	public ResultSet getResultSetFromGivenSelectQuery(Connection connection, String selectQuery) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(selectQuery);

		return rs;
	}

}
