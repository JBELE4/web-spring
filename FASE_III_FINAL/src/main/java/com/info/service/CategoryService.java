
package com.info.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.Respository.CategoryRepository;
import com.info.model.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category addCategory(Category category) {
		categoryRepository.save(category);
		return category;
	}

	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	public void deleteCategory(long categoryId) {
		categoryRepository.deleteById(categoryId);
	}


	public Category updateCategory(long cateryId,Category category1) {
		Optional<Category> aux = categoryRepository.findById(cateryId);
		Category 	category2 = aux.get();

		if (category2 != null) {
			category2.setCategoryName(category1.getCategoryName());
			category2.setItemList(category1.getItemList());
		}
		
   
      Category 	category3=new Category(cateryId,category2);
		
	
		
		categoryRepository.save(category3);
		return category3;
	}

	public Optional<Category> getCategory(long categoryId) {
		return categoryRepository.findById(categoryId);
	}

	// use to GET one category by Id
	public Optional<Category> getById (long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		return category;
	}

}
