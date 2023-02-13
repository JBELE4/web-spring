package com.info.RESTController;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.info.model.Category;
import com.info.model.Item;
import com.info.service.CategoryService;
import com.info.service.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/item")
public class ItemRESTController {

	@Autowired
	ItemService service;
	@Autowired
	CategoryService categoryService;

	@GetMapping("/items")
	public ResponseEntity<Collection<Item>> items() {
		Collection<Item> items = service.listItem();

		if (items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(items, HttpStatus.OK);

	}

	// Get one post of clothes
	@GetMapping("/{id}")
	public ResponseEntity<Item> item(@PathVariable long id) {
		Optional<Item> op = service.getItemById(id);
		if (op.isPresent()) {
			Item item = op.get();
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Create item
	@PostMapping("/insertItem/{category}")
	@ResponseStatus(HttpStatus.CREATED) // Send the status of the post
	public ResponseEntity<Item> insertItem(Model model, @RequestBody Item item,@PathVariable Category category) {
		
		item.setCategory(category);
 
		if (service.addItem(item) != null) {
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	// Remove one item by id
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Item> deleteItem(Model model, @PathVariable long id) {

		Optional<Item> op = service.getItemById(id);

		if (op.isPresent()) {
			Item item = op.get();
			service.deleteItem(id);
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	
	// Update all clothes
	@PutMapping("/{id}/update")
	public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item updateShop) {

		Item shopU = service.updateItem(id, updateShop);

		if (shopU != null) {

			return new ResponseEntity<>(updateShop, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	
	// Update one field of a item
	@PatchMapping("/{id}/patch")
	public ResponseEntity<Item> patchItem(@PathVariable long id, @RequestBody Item updateItem) {

		Item shopP = service.patchItem(id, updateItem);

		if (shopP != null) {
			return new ResponseEntity<Item>(shopP, HttpStatus.OK);
		} else {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

	}
	 
	
}
