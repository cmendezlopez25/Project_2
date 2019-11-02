package com.revature.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_table")
public class User {
	@Id
	@Column(name="email")
	@NotEmpty
	@Pattern(regexp="^(.+)@(.+)$")
	@Size(max=100)
	private String email;
	
	@Column(name="password")
	@NotEmpty
	@Size(max=100)
	private String password;
	
	@Column(name="first_name")
	@NotEmpty
	@Size(max=100)
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty
	@Size(max=100)
	private String lastName;
	
	@OneToMany(mappedBy="user")
	private Set<UserRoleAccount> userRoleAccounts;
	
	public User() {
		super();
	}

	public User(@Pattern(regexp = "^(.+)@(.+)$") @Size(max = 100) String email, @Size(max = 100) String password,
			@Size(max = 100) String firstName, @Size(max = 100) String lastName,
			Set<UserRoleAccount> userRoleAccounts) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRoleAccounts = userRoleAccounts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<UserRoleAccount> getUserRoleAccounts() {
		return userRoleAccounts;
	}

	public void setUserRoleAccounts(Set<UserRoleAccount> userRoleAccounts) {
		this.userRoleAccounts = userRoleAccounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userRoleAccounts == null) ? 0 : userRoleAccounts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userRoleAccounts == null) {
			if (other.userRoleAccounts != null)
				return false;
		} else if (!userRoleAccounts.equals(other.userRoleAccounts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userRoleAccounts=" + userRoleAccounts + "]";
	}

	
}
