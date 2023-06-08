package com.totem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.totem.util.PostUserTokenResponse;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException {

		testLdap();
	}

	private static void testLdap() {

		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("client_id", "9acf6fa1-0ef6-4e6e-945a-d3d01153a53c");
		map.add("grant_type", "password");
		map.add("scope", "openid");
		map.add("username", "tulio.vasconcellos@okeanyachts.com");
		map.add("password", "83375191Neo");
		String tenantId = "5637ee95-0643-498a-a568-b684e6b438ad";
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<PostUserTokenResponse> response = restTemplate.postForEntity(
				"https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token", request,
				PostUserTokenResponse.class);

		System.out.println(response.getBody().getAccess_token());

	}

}