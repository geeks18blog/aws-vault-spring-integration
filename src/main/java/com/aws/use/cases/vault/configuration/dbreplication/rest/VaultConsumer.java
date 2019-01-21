package com.aws.use.cases.vault.configuration.dbreplication.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class VaultConsumer {

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private Environment env;

	public VaultResponse fetchIAMCredentailsForUsingVault() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("X-Vault-Token", "s.xD4bKEY2V7TA0BxCAisbHXn0");

	

		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);

		VaultResponse retrievedVaultResponse = new VaultResponse();

		try {


			ResponseEntity<VaultResponse> entity = restTemplate.exchange(
	                "http://localhost:" + 8200 + "/v1/aws/creds/access-all", HttpMethod.GET, httpEntity,
	                VaultResponse.class);
			retrievedVaultResponse = entity.getBody();
			System.out.println(entity.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retrievedVaultResponse;
	}
}
