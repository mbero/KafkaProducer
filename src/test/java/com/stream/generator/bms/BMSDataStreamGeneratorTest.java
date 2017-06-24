package com.stream.generator.bms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BMSDataStreamGeneratorTest {

	private BMSDataStreamGenerator bmsDataStreamGenerator;
	
	@Before
	public void setUp() throws Exception {
		bmsDataStreamGenerator = new BMSDataStreamGenerator();
	}

	@Test
	public void test() {
		try {
			bmsDataStreamGenerator.putAllDataFromTrendTableIntoMemory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
