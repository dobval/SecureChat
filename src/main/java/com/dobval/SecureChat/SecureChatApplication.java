package com.dobval.SecureChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dobval.SecureChat.repositories") //DEBUG, TODO: REMOVE
public class SecureChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureChatApplication.class, args);
	}

}
