package com.revature.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class User {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
}
