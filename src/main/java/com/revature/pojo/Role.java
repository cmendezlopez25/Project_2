package com.revature.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="role_table")
public class Role {
	@Id
	@SequenceGenerator(name="role_id_seq", sequenceName="role_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "role_id_seq")
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="name")
	@NotEmpty
	@Size(max=25)
	private String roleName;

	@OneToMany(mappedBy="role")
	private Set<UserRoleAccount> userRoleAccounts;
	
	public Role() {
		super();
	}
	
	public Role(int roleId, @Size(max = 25) String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Role(int roleId, @Size(max = 25) String roleName, Set<UserRoleAccount> userRoleAccounts) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.userRoleAccounts = userRoleAccounts;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		result = prime * result + roleId;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roleId != other.roleId)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
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
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", userRoleAccounts=" + userRoleAccounts + "]";
	}


}
