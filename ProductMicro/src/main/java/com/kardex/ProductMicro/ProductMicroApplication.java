package com.kardex.ProductMicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kardex.ProductMicro")
public class ProductMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMicroApplication.class, args);
	}

}
