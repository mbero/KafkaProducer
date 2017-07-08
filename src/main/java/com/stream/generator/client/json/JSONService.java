package com.stream.generator.client.json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stream.generator.bms.SingleBMSReadRecord;

public class JSONService {

	

	public void saveJSONObjectToFile(String fullPathToFile, Object jsonDataObject)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		writer.writeValue(new File(fullPathToFile), jsonDataObject);
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
