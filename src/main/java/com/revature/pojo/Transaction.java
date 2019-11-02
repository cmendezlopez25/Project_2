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

@Entity
@Table(name="transaction_table")
public class Transaction {
	@Id
	@SequenceGenerator(name="transaction_id_seq", sequenceName="transaction_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "transaction_id_seq")
	@Column(name="transaction_id")
	private int transactionId;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private int accountId;
	
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
	@NotEmpty
	private String note;
	
	@Column(name="recurring")
	private String recurring;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="transaction_category",
			joinColumns=@JoinColumn(name="transaction_id"),
			inverseJoinColumns=@JoinColumn(name="category_id"))
	private Set<Category> categories;

	public Transaction() {
		super();
	}

	public Transaction(int transactionId, int accountId,
			@DecimalMin("0.00") @Digits(integer = 10, fraction = 2) double amount, LocalDate date,
			@Size(max = 100) String transactionName, String note, String recurring, Set<Category> categories) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.amount = amount;
		this.date = date;
		this.transactionName = transactionName;
		this.note = note;
		this.recurring = recurring;
		this.categories = categories;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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

	public String getRecurring() {
		return recurring;
	}

	public void setRecurring(String recurring) {
		this.recurring = recurring;
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
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((recurring == null) ? 0 : recurring.hashCode());
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
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
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
		if (recurring == null) {
			if (other.recurring != null)
				return false;
		} else if (!recurring.equals(other.recurring))
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
		return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId + ", amount=" + amount
				+ ", date=" + date + ", transactionName=" + transactionName + ", note=" + note + ", recurring="
				+ recurring + ", categories=" + categories + "]";
	}
	
}
