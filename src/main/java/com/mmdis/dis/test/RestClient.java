package com.mmdis.dis.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/dis/test";
	
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send user-credentials.
     */
    private static HttpHeaders getHeaders(){
    	String credentials="ivan:123456";
    	String base64Credentials = new String(Base64.encodeBase64(credentials.getBytes()));
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + base64Credentials);
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	return headers;
    }
    
    public static void listUsers() {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/users", HttpMethod.GET, request, List.class);

        List<Map<String, Object>> usersMap = (List<Map<String, Object>>)response.getBody();
        
        for ( Map userMap : usersMap ) {
        	System.out.println(userMap);
        }
    }
	
	public static void main(String[] args) {
		
		listUsers();
		
		
	}

}
