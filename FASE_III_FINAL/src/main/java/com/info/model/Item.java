package com.info.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "items")
public class Item {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long itemId;
	
	@Column
	private String name;
	
	@Column
	private String size;
	
	@Column
	private String brand;
	
	@Column
	private double price;
	
	@Column
	private int itemUnit;
	
	@Column
	private String comment;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Category category = new Category();


	@ManyToMany(cascade = CascadeType.MERGE,mappedBy = "itemList")
	private List<User> userList;
	
	
	public Item () {}
	public Item(String name, String size, String brand, double price, String comment){
        setName(name);
        setSize(size);
        setBrand(brand);
        setPrice(price);
        setComment(comment);
    }
	
	public Item(long id, String name, String size, String brand, double price, String comment) {
		this.itemId = id;
		this.name = name;
		this.size = size;
		this.brand = brand;
		this.price = price;
		this.comment = comment;
		this.userList=new ArrayList<User>();
	
	}
	
	
	public Item(long id, Item a) {
		this.itemId = id;
		this.name = a.getName();
		this.size = a.getSize();
		this.brand = a.getBrand();
		this.price = a.getPrice();
		this.comment = a.getComment();
		this.userList = new ArrayList<User>();
		}
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public long getId() {
		return itemId;
	}

	
	public void setId(long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(int itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
	

}
