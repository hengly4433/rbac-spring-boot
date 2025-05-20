package com.blockcode.rbac;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRbacApplication.class, args);
	}

}
