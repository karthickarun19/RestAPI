package com.api.rest.api.model;

public class RestResponse {
	private int StatusCode;

	public int getStatusCode() {
		return StatusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	private String responseBody;
	
	public RestResponse(int StatusCode, String responseBody ) {
		this.StatusCode=StatusCode;
		this.responseBody=responseBody;
	}
	
	@Override
	public String toString() {
		return String.format("Status COde :%1s Body :%2s", this.StatusCode,this.responseBody);
	}
   
}
