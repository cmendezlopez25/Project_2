package com.revature.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.util.SessionFactoryUtil;

@Configuration
public class SessionFactoryUtilConfig {
	@Bean
	public SessionFactory sessionFactoryBean() {
		return SessionFactoryUtil.getSessionFactory();
	}
}
