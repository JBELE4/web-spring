package com.info.RESTController;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.info.model.Category;
import com.info.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryRESTController {

	@Autowired
	CategoryService categoryService;
	
		// Get all categories
		@GetMapping("/categories")
		public ResponseEntity<Collection<Category>> categories() {
			Collection<Category> categories = categoryService.listCategory();
			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(categories, HttpStatus.OK);
			}
		}
		
		// Get one post of categories
		@GetMapping("/{id}")
		public ResponseEntity<Category> category(@PathVariable long id) {
			Optional<Category> op = categoryService.getById(id);
			if (op.isPresent()) {
				Category category = op.get();
				return new ResponseEntity<>(category, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		// Create category
		@PostMapping("/insertCategory")
		@ResponseStatus(HttpStatus.CREATED) // Send the status of the post
		public ResponseEntity<Category> insertCategory(Model model, @RequestBody Category category) {
			if (categoryService.addCategory(category) != null) {
				return new ResponseEntity<>(category, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		
		// Remove one category by id
		@DeleteMapping("/{id}/delete")
		public ResponseEntity<Category> deleteCategory( @PathVariable  long id,Model model) {
			Optional<Category> op = categoryService.getById(id);
			if (op.isPresent()) {
				
				Category category = op.get();
				categoryService.deleteCategory(id);
				return new ResponseEntity<>(category, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		// Update all categories
		@PutMapping("/{id}/update")
		public ResponseEntity<Category> updateCategory(@PathVariable long  id,@RequestBody Category upCategory  ) {
			Category categoryU = categoryService.updateCategory(id,upCategory);
			if (categoryU != null) {
				return new ResponseEntity<>(upCategory, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
}
