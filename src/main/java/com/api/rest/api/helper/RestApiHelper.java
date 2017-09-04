package com.api.rest.api.helper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class RestApiHelper {

	public static RestResponse performGetRequest(String url) {
		try {
			return performGetRequest(new URI(url));
		} catch (URISyntaxException e) {

			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static RestResponse performGetRequest(URI uri) {
		HttpGet get = new HttpGet(uri);
		CloseableHttpResponse response=null;
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			response = client.execute(get);
			ResponseHandler<String> body = new BasicResponseHandler();

			RestResponse restResponse = new RestResponse(response
					.getStatusLine().getStatusCode(),
					body.handleResponse(response));
			client.close();
			response.close();
			return restResponse;

		} catch (Exception e) {
			if(e instanceof HttpResponseException)
				return new RestResponse(response
						.getStatusLine().getStatusCode(),
						e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
