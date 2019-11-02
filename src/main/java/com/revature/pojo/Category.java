package com.revature.pojo;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="category")
public class Category {
	@Id
	@SequenceGenerator(name="category_id_seq", sequenceName="category_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "category_id_seq")
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="name")
	@NotEmpty
	@Size(max=100)
	private String categoryName;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="transaction_category",
			joinColumns=@JoinColumn(name="category_id"),
			inverseJoinColumns=@JoinColumn(name="transaction_id"))
	private Set<Transaction> transactions;

	public Category() {
		super();
	}

	public Category(int categoryId, @Size(max = 100) String categoryName, Set<Transaction> transactions) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.transactions = transactions;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
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
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", transactions="
				+ transactions + "]";
	}

	
}
