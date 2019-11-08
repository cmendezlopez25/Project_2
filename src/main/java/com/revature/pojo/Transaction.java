package com.revature.pojo;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="transaction_table")
public class Transaction {
	@Id
	@SequenceGenerator(name="transaction_id_seq", sequenceName="transaction_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "transaction_id_seq")
	@Column(name="transaction_id")
	private int transactionId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	@JsonIgnoreProperties("transactions")
	private Account account;
	
	@Column(name="amount")
	@NotEmpty
	@DecimalMin("0.00")
	@Digits(integer=10, fraction=2)
	private double amount;
	
	@Column(name="date")
	@NotEmpty
	private LocalDate date;
	
	@Column(name="name")
	@NotEmpty
	@Size(max=100)
	private String transactionName;
	
	@Column(name="note")
	private String note;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="transaction_category",
			joinColumns=@JoinColumn(name="transaction_id"),
			inverseJoinColumns=@JoinColumn(name="category_id"))
	@JsonIgnoreProperties("transactions")
	private Set<Category> categories;

	public Transaction() {
		super();
	}

	public Transaction(int transactionId, Account account,
			@DecimalMin("0.00") @Digits(integer = 10, fraction = 2) double amount, LocalDate date,
			@Size(max = 100) String transactionName, String note, String recurring, Set<Category> categories) {
		super();
		this.transactionId = transactionId;
		this.account = account;
		this.amount = amount;
		this.date = date;
		this.transactionName = transactionName;
		this.note = note;
		this.categories = categories;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + transactionId;
		result = prime * result + ((transactionName == null) ? 0 : transactionName.hashCode());
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
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionName == null) {
			if (other.transactionName != null)
				return false;
		} else if (!transactionName.equals(other.transactionName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", account=" + account + ", amount=" + amount + ", date="
				+ date + ", transactionName=" + transactionName + ", note=" + note + "]";
	}

	

	
	
}
