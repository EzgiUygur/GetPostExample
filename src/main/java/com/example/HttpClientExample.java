package com.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientExample {

    public static void main(String[] args) {
        // HttpClient create
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // GET request
            sendGetRequest(httpClient);

            // POST request
            sendPostRequest(httpClient);

            // HttpClient close
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendGetRequest(CloseableHttpClient httpClient) {
        try {
            // GET request create
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/1");

            // Request and response
            HttpResponse response = httpClient.execute(request);

            // Response status
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("GET Response Status Code: " + statusCode);

            // Response content
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println("GET Response Content: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendPostRequest(CloseableHttpClient httpClient) {
        try {
            // POST Request
            HttpPost postRequest = new HttpPost("https://jsonplaceholder.typicode.com/posts");
            postRequest.addHeader("Content-Type", "application/json");

            // Request body
            String json = "{ \"title\": \"question\", \"body\": \"two\", \"userId\": 1 }";
            StringEntity entity = new StringEntity(json);
            postRequest.setEntity(entity);

            // Request and response
            HttpResponse response = httpClient.execute(postRequest);

            // Response status
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("POST Response Status Code: " + statusCode);

            //  Response content
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String result = EntityUtils.toString(responseEntity);
                System.out.println("POST Response Content: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
