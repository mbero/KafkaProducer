package com.stream.generator.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDBClient {

	private Statement statement;

	/**
	 * 
	 * @param sqliteDBFilePath
	 *            : String is proper path for SQLite Database file F.Example :
	 *            f.e E:/PCSS/bms_analytics_workspace/StreamGenerator/BMS_DB.db
	 * @return Connection object
	 */
	// TODO - should be singleton
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

	public void closeStatement() {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet getResultSetFromGivenSelectQuery(Connection connection, String selectQuery) throws SQLException {
		// Statement stmt = null;
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(selectQuery);
		// stmt.close();
		return rs;
	}

}
