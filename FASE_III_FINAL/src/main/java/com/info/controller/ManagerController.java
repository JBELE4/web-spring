package com.info.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.info.model.Category;
import com.info.model.Item;
import com.info.service.CategoryService;

import com.info.service.ItemService;

@Controller
@RequestMapping("manager")
public class ManagerController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;
	

	@GetMapping("index")
	public String index() {
		return "manager/index";
	}

//	For Category--------------------------------------------------
	@GetMapping("category-form")
	public ModelAndView listCategory() {
		ModelAndView mv = new ModelAndView("manager/category-form");
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}

	@PostMapping("add-category")
	public ModelAndView addCategory(Category category) {
		ModelAndView mv = new ModelAndView("manager/category-form");
		categoryService.addCategory(category);
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}
	
	@GetMapping("delete-Category/{categoryId}")
	public ModelAndView deleteCategory(@PathVariable("categoryId")String categoryId) {
		ModelAndView mv = new ModelAndView("manager/category-form");
		categoryService.deleteCategory(Long.parseLong(categoryId));
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}
	
	@GetMapping("updateCategory/{categoryId}")
	public ModelAndView updateCategory(@PathVariable("categoryId")String categoryId) {
		ModelAndView mv = new ModelAndView("manager/updateCategory");
		mv.addObject("Category", categoryService.getCategory(Long.parseLong(categoryId)).get());
		return mv;
	}
	
//	For Item--------------------------------------------------
	@GetMapping("item-form")
	public ModelAndView listItem() {
		ModelAndView mv = new ModelAndView("manager/item-form");
		mv.addObject("categoryList", categoryService.listCategory());
		mv.addObject("itemList", itemService.listItem());
		return mv;
	}

	@PostMapping("add-item")
	public ModelAndView addItem(Item item) {
		ModelAndView mv = new ModelAndView("manager/item-form");
		itemService.addItem(item);
		mv.addObject("itemList", itemService.listItem());
		return mv;
	}
	
	@GetMapping("delete-item/{itemId}")
	public ModelAndView deleteItem(@PathVariable("itemId")long itemId) {
		ModelAndView mv = new ModelAndView("manager/item-form");
		itemService.deleteItem(itemId);
		mv.addObject("itemList", itemService.listItem());
		return mv;
	}
	
	@GetMapping("updateItem/{itemId}")
	public ModelAndView updateItem(@PathVariable("itemId")long itemId) {
		ModelAndView mv = new ModelAndView("manager/updateItem");
		mv.addObject("categoryList", categoryService.listCategory());
		mv.addObject("item", itemService.getItemById(itemId).get());
		return mv;
	}
	
	@PostMapping("update-item-submit/{itemId}")
	public ModelAndView updateItemSubmit(Item item,  @PathVariable("itemId")long itemId) {
		item.setId(itemId);
		itemService.addItem(item);
		ModelAndView mv = new ModelAndView("manager/updateItem");
		mv.addObject("categoryList", categoryService.listCategory());
		mv.addObject("item", item);
		return mv;
	}
	
	@GetMapping ("/allItems1")
	public String items (Model model, @RequestParam Optional<String> brand, @RequestParam Optional<String> size){
		
        List<Item> items = itemService.listItem();
		
		
		if(brand.isPresent()) {
			items = itemService.filterByBrand(brand.get());
		}
		
        if(size.isPresent()) {
        	items = itemService.filterBySize(size.get());
        }
   
        model.addAttribute("itemsList",items);
        return "items"; // html for show all the items in the shop
		
	}

}
