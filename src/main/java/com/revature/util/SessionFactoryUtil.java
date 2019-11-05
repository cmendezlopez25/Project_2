package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.pojo.Account;
import com.revature.pojo.Category;
import com.revature.pojo.Role;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;
import com.revature.pojo.UserRoleAccount;

@Component
public class SessionFactoryUtil {
	private static SessionFactory sf;
	static {		
		//StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = configureRegistry(config);
		sf = config.buildSessionFactory(serviceRegistry);
		//sf = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sf;
	}
	
	private static ServiceRegistry configureRegistry(Configuration config) {
		setupConnection(config);
		addAllAnnotatedClasses(config);
		return new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	}
	
	private static void setupConnection(Configuration config) {
		config.setProperty("hibernate.connection.url", "jdbc:postgresql://" + System.getenv("PROJ_2_POSTGRES_URL") + ":5432/postgres?");
		config.setProperty("hibernate.connection.username", System.getenv("PROJ_2_POSTGRES_USERNAME"));
		config.setProperty("hibernate.connection.password", System.getenv("PROJ_2_POSTGRES_PASSWORD"));
		config.setProperty("hibernate.default_schema", "proj2_release");
	}
	
	private static void addAllAnnotatedClasses(Configuration config){
		//Associated with hibernate's mapping
		//<mapping class="com.revature.pojo.User"/>
		//<mapping class="com.revature.pojo.Account"/>
		//<mapping class="com.revature.pojo.Transaction"/>
		//<mapping class="com.revature.pojo.Category"/>
		//<mapping class="com.revature.pojo.Role"/>
		
		//Programmatic way of adding these classes into hibernate
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Account.class);
		config.addAnnotatedClass(UserRoleAccount.class);
		config.addAnnotatedClass(Transaction.class);
		config.addAnnotatedClass(Category.class);
		config.addAnnotatedClass(Role.class);
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}
}
