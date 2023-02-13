package com.info.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	
	public List<Item> findByCategory_CategoryId(long CategoryId);
	public List<Item> findBySize(String size);
	public List<Item> findByBrand(String brand);
	
}
