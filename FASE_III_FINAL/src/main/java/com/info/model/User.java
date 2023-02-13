package com.info.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique=true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	private int active;

	
	@ManyToMany
	@JoinTable(name = "userItemList", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
	private List<Item> itemList;
		
	public User() {}
	
	public User(long userId, String firstName, String lastName, String email, String password, String role) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = 1;
		this.role = role;
		this.itemList=new ArrayList<Item>();
	}
	
	
	
	public User(long userId,User us) {
		this.userId = userId;
		this.firstName = us.getFirstName();
		this.lastName = us.getLastName();
		this.email = us.getEmail();
		this.password = us.getPassword();
		this.role =us.getRole();
		this.active = 1;
		this.itemList=new ArrayList<Item>();
		}
	

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	} 
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", productList=" + itemList + "]";
	}

	public List<String> getRoleList() {
		if (this.role.length() > 0) {
			return Arrays.asList(this.role.split(","));
		}

		return new ArrayList<String>();
	}

	public void removeItem(Item item) {
	 this.itemList.remove(item);
		
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(),user.getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	public void setItem(Item item) {
		this.itemList.add(item);
	
		
	}

}