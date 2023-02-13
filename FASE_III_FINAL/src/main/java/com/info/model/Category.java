package com.info.model;

import  javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="categories")
public class Category { 
	    @Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private long categoryId;
	    
	    private String categoryName;
	    
	    @JsonIgnore
	    @OneToMany ( cascade = CascadeType.ALL,mappedBy = "category")    
	 
	     private List <Item> itemList;

	    
	    public Category() {};
	    
		public Category(long categoryId, String categoryName) {
			super();
			this.categoryId = categoryId;
			this.categoryName = categoryName;
			this.itemList=new ArrayList<Item>();
		}
		
		public Category(long categoryId, Category c) {
			super();
			this.categoryId = categoryId;
			this.categoryName = c.getCategoryName();
			this.itemList=new ArrayList<Item>();
		}


		public long getCategoryId() {
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

		public List<Item> getItemList() {
			return itemList;
		}

		public void setItemList(List<Item> itemList) {
			this.itemList = itemList;
		}

}
