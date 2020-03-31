package com.example.bankSpring;



import com.example.bankSpring.Entity.User;
import com.example.bankSpring.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class BankSpringApplication{

	public static void main(String[] args) {

		SpringApplication.run(BankSpringApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.sqlite.JDBC");
		dataSourceBuilder.url("jdbc:sqlite:bank.db");
		return dataSourceBuilder.build();
	}

}
