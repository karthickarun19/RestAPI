package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.model.RestResponse;

public class TestGetMethod {
	@Test
	public void testGetPingAlive() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/ping/hello";
		RestResponse response = RestApiHelper.performGetRequest(url,null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		Assert.assertEquals("Hi! hello", response.getResponseBody());
	}
	@Test
	public void testGetAll(){
		String url = "http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestApiHelper.performGetRequest(url,headers);
		Assert.assertTrue("Expected status code not present",HttpStatus.SC_OK ==response.getStatusCode()|| 
				HttpStatus.SC_NO_CONTENT== response.getStatusCode()
				);
	}
	
	@Test
	public void testGetFindwithID(){
		String url = "http://localhost:8080/laptop-bag/webapi/api/find/127";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestApiHelper.performGetRequest(url,headers);
		Assert.assertTrue("Expected status code not present",HttpStatus.SC_NOT_FOUND ==response.getStatusCode()|| 
				HttpStatus.SC_NO_CONTENT== response.getStatusCode()
				);
	}

}
