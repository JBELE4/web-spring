package com.info.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.info.Respository.ItemRepository;
import com.info.model.Item;

@Service
@Transactional
public class ItemService{
	
	@Autowired
	private ItemRepository itemrepository;
	
	@Autowired
	private EntityManager entityManager;

	public Item addItem(Item item) {
		itemrepository.save(item);
		return item;
	}

	public List<Item> listItem() {
		return itemrepository.findAll();
	}

	public Optional<Item> getItemById(long itemId) {
		 return itemrepository.findById(itemId);
	
	}

	public List<Item> findByCategory(long categoryId) {
		return itemrepository.findByCategory_CategoryId(categoryId);
	}

	public void deleteItem(long itemId) {
		itemrepository.deleteById(itemId);
	}
	
	// use to PUT (update) one item by id
	public Item updateItem(long itemId, Item newItem) {

		Optional<Item> aux = itemrepository.findById(itemId);
		Item item2 = aux.get();

		if (item2 != null) {
			item2.setName(newItem.getName());
			item2.setSize(newItem.getSize());
			item2.setBrand(newItem.getBrand());
			item2.setPrice(newItem.getPrice());
			item2.setComment(newItem.getComment());
		}

		Item defItem = new Item(itemId,item2);
		itemrepository.saveAndFlush(defItem);
		return defItem;

	}

	// use to PATCH (change one field) by id
	public Item patchItem(long id, Item updateItem) {

		Optional<Item> item = itemrepository.findById(id);
		Item item2 = item.get();

		// Check if shop is not empty and then apply the same condition to the
		// attributes
		if (item2 != null) {

			if (updateItem.getName() != null) {
				item2.setName(updateItem.getName());
			}
			if (updateItem.getSize() != null) {
				item2.setSize(updateItem.getSize());
			}
			if (updateItem.getBrand() != null) {
				item2.setBrand(updateItem.getBrand());
			}
			if (updateItem.getPrice() != 0) {
				item2.setPrice(updateItem.getPrice());
			}
			if (updateItem.getItemUnit() != 0) {
				item2.setItemUnit(updateItem.getItemUnit());
			}
			if (updateItem.getComment() != null) {
				item2.setComment(updateItem.getComment());
			}


			Item defItem = new Item(id,item2);
			itemrepository.saveAndFlush(defItem);
			return defItem;

		}
		return null;
	}

	// filters by size, brand and type
	
	public List<Item> findByCategory_CategoryId(long categoryId) {
		return itemrepository.findByCategory_CategoryId(categoryId);
	}

	
	public List<Item> filterBySizeByBrand(@PathVariable String brand,@PathVariable String size) {
		TypedQuery<Item> query = entityManager.createQuery("SELECT c FROM Item c WHERE c.brand = :brand and c.size=:size", Item.class);
		return query.setParameter("brand", brand).setParameter("size", size).getResultList();
	}
	
	// filters by size, brand and type
		public List<Item> filterBySize(String size){
			return itemrepository.findBySize(size);
		}
		
		public List<Item> filterByBrand(String brand){
			return itemrepository.findByBrand(brand);
			
		}
		

}
