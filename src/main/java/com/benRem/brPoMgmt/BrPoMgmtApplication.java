package com.benRem.brPoMgmt;

import com.benRem.brPoMgmt.services.springsocial.config.AppProperties;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.Bean;

import org.apache.catalina.Context;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BrPoMgmtApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BrPoMgmtApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(BrPoMgmtApplication.class, args);
	}


	private Connector redirectConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8085);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}

}
