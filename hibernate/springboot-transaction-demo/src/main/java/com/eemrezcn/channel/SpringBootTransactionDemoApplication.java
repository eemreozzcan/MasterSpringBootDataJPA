package com.eemrezcn.channel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootTransactionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionDemoApplication.class, args);
	}

}
