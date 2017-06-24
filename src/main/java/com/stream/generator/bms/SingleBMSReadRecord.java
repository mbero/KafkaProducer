package com.stream.generator.bms;

/**
 * POJO class which represents value of single BMS read variable value
 * 
 * @author Marcin
 *
 */
public class SingleBMSReadRecord {
	private String readDate;
	private String readValue;
	private String readIOdev_id;
	private String readTag_id;

	public String getReadDate() {
		return readDate;
	}

	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}

	public String getReadValue() {
		return readValue;
	}

	public void setReadValue(String readValue) {
		this.readValue = readValue;
	}

	public String getReadIOdev_id() {
		return readIOdev_id;
	}

	public void setReadIOdev_id(String readIOdev_id) {
		this.readIOdev_id = readIOdev_id;
	}

	public String getReadTag_id() {
		return readTag_id;
	}

	public void setReadTag_id(String readTag_id) {
		this.readTag_id = readTag_id;
	}

	public SingleBMSReadRecord(String readDate, String readValue, String readIOdev_id, String readTag_id) {
		super();
		this.readDate = readDate;
		this.readValue = readValue;
		this.readIOdev_id = readIOdev_id;
		this.readTag_id = readTag_id;
	}

}
