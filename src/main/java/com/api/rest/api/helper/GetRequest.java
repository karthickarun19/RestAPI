package com.api.rest.api.helper;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

/*
 * Step 1: Create the Http get method
 *Step 2: Create the Http client
 *Step 3: Execute the http method using the client
 *Step 4: Catch the response of execution
 *Step 5: Display the response at the console
 * */

public class GetRequest {

	public static void main(String[] args) {
		/*
		 * HttpGet get = new
		 * HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/hello");
		 * try{ CloseableHttpClient client = HttpClientBuilder.create().build();
		 * CloseableHttpResponse response =client.execute(get); StatusLine
		 * status =response.getStatusLine();
		 * System.out.println(status.getStatusCode());
		 * System.out.println(status.getProtocolVersion());
		 * ResponseHandler<String> body = new BasicResponseHandler();
		 * RestResponse restResponse = new
		 * RestResponse(response.getStatusLine().getStatusCode(),
		 * body.handleResponse(response)); //String getBody =
		 * body.handleResponse(response); System.out.println(getBody);
		 * System.out.println(restResponse.toString()); client.close();
		 * response.close(); } catch(Exception e){ e.printStackTrace(); }
		 */
		/*RestResponse response = RestApiHelper
				.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/ping/hello",null);
		System.out.println(response.toString());*/
		String jsonBody ="{\r\n" + 
				"	\"BrandName\": \"Dell\"," + 
				"	\"Features\": {\r\n" + 
				"		\"Feature\": [\"8GB RAM\"," + 
				"		\"1TB Hard Drive\"]" + 
				"	}," + 
				"	\"Id\": " + (int)(1000*(Math.random()))+","+
				"	\"LaptopName\": \"Latitude\"" + 
				"}";
		
		HttpPost post = new HttpPost("http://localhost:8080/laptop-bag/webapi/api/add");
		try{
			CloseableHttpClient client = HttpClientBuilder.create().build();
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			StringEntity data = new StringEntity(jsonBody,ContentType.APPLICATION_JSON);
			post.setEntity(data);
			CloseableHttpResponse response =client.execute(post);
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restReponse = new RestResponse (response.
					getStatusLine().getStatusCode(),handler.handleResponse(response));
			System.out.println(restReponse.toString());
			client.close();
			response.close();
		}catch(Exception e)
		{ 
			e.printStackTrace(); 
		}
	}
}
