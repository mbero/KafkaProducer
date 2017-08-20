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
	private String readDateMillis; //FIXME  - probably temporary property, readDate will be date in Milliseconds
	

	public String getReadDateMillis() {
		return readDateMillis;
	}

	public void setReadDateMillis(String readDateMillis) {
		this.readDateMillis = readDateMillis;
	}

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

	public SingleBMSReadRecord(String readDate, String readValue, String readIOdev_id, String readTag_id, String readDateMillis) {
		super();
		this.readDate = readDate;
		this.readValue = readValue;
		this.readIOdev_id = readIOdev_id;
		this.readTag_id = readTag_id;
		this.readDateMillis = readDateMillis;
	}

}
