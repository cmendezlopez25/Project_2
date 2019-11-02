package com.revature.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="account")
public class Account {
	@Id
	@SequenceGenerator(name="account_id_seq", sequenceName="account_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "account_id_seq")
	@Column(name="account_id")
	private int accountId;
	
	@Column(name="name")
	@NotEmpty
	@Size(max=100)
	private String accountName;
	
	@OneToMany(mappedBy="accountId", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Transaction> transactions;
	
	@OneToMany(mappedBy="account")
	private Set<UserRoleAccount> userRoleAccounts;
	
	public Account() {
		super();
	}

	public Account(int accountId, @Size(max = 100) String accountName, Set<Transaction> transactions,
			Set<UserRoleAccount> userRoleAccounts) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.transactions = transactions;
		this.userRoleAccounts = userRoleAccounts;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
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
		result = prime * result + accountId;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
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
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
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
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", transactions=" + transactions
				+ ", userRoleAccounts=" + userRoleAccounts + "]";
	}
	
}
