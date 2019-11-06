package com.revature.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_role_account")
public class UserRoleAccount {
	@Id
	@SequenceGenerator(name="ura_id_seq", sequenceName="ura_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ura_id_seq")
	@Column(name="ura_id")
	private int uraId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="email")
	@JsonIgnoreProperties("userRoleAccounts")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	@JsonIgnoreProperties("userRoleAccounts")
	private Role role;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	@JsonIgnoreProperties("userRoleAccounts")
	private Account account;
	
	public UserRoleAccount() {
		super();
	}

	public UserRoleAccount(int uraId, User user, Role role, Account account) {
		super();
		this.uraId = uraId;
		this.user = user;
		this.role = role;
		this.account = account;
	}



	public int getUraId() {
		return uraId;
	}

	public void setUraId(int uraId) {
		this.uraId = uraId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + uraId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserRoleAccount other = (UserRoleAccount) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (uraId != other.uraId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRoleAccount [uraId=" + uraId + ", user=" + user + ", role=" + role + ", account=" + account + "]";
	}

}
