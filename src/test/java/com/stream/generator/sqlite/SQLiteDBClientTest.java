package com.stream.generator.sqlite;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.stream.generator.client.sqlite.SQLiteDBClient;

public class SQLiteDBClientTest {

	private SQLiteDBClient sqliteClient;
	private final static String sqliteDBPath = "/home/centos/Desktop/StreamGenerator/BMS_DB.db";

	@Before
	public void setUp() throws Exception {
		sqliteClient = new SQLiteDBClient();
	}

	@Test
	public void testGetConnection() {
		Connection conn = sqliteClient.getConnection(sqliteDBPath);
		assertNotNull(conn);
	}

	@Test
	public void testCloseConnection() {
		Connection conn = sqliteClient.getConnection(sqliteDBPath);
		try {
			sqliteClient.closeConnection(conn);
			assertTrue(conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetResultSetFromGivenSelectQuery() {
		Connection conn = sqliteClient.getConnection(sqliteDBPath);
		try {
			ResultSet resultSet = sqliteClient.getResultSetFromGivenSelectQuery(conn, "SELECT * FROM TREND");
			assertNotNull(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
