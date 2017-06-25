package com.stream.generator.tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tools {

	/**
	 * Transforms given simple object (POJO) for JSON String
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String getJSONStringFromGivenObject(Object object) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(object);
		return jsonInString;
	}
}
