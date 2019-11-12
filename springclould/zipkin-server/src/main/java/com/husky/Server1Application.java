package com.husky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class Server1Application {
	public static void main(String[] args) {
		SpringApplication.run(Server1Application.class, args);
	}

}
