package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.User;

@Component
public class SessionFactoryUtil {
	private static SessionFactory sf;

	static {
		//StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://" + System.getenv("PROJ_2_POSTGRES_URL") + ":5432/postgres?");
		configuration.setProperty("hibernate.connection.username", System.getenv("PROJ_2_POSTGRES_USERNAME"));
		configuration.setProperty("hibernate.connection.password", System.getenv("PROJ_2_POSTGRES_PASSWORD"));
		configuration.setProperty("hibernate.default_schema", "proj2_release");
		configuration.addAnnotatedClass(User.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		//sf = configuration.buildSessionFactory(serviceRegistry);
		sf = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}
	
	//@Autowired
	//public void setSessionFactory(SessionFactory sf) {
	//	System.out.println("AmIHere, though?");
	//	this.sf = sf;
	//}
}
