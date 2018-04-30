package com.benRem.brPoMgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableAutoConfiguration
@EnableOAuth2Sso // Without this, basic authentication is invoked
public class BrPoMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrPoMgmtApplication.class, args);
	}
}
