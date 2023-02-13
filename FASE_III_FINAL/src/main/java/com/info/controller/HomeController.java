package com.info.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Item;
import com.info.model.User;
import com.info.service.CategoryService;
import com.info.service.ItemService;
import com.info.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping({"index", "/"})
	public String index(Model model) {
		model.addAttribute("categoryList", categoryService.listCategory());
		model.addAttribute("itemList", itemService.listItem());
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("signup")
	public ModelAndView signUp(User user) {
		ModelAndView mv = new ModelAndView("/index");
		userService.save(user);
		mv.addObject("itemList", itemService.listItem());
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	} 
	
	
	@GetMapping("allItems")
	public String allItems(Model model, @RequestParam (required=false)String brand, @RequestParam (required=false)String size) {
		
		 List<Item> items = itemService.listItem();
			if(brand==null &&size==null) {
			items=itemService.listItem();
			}else {
			if(!brand.equals("")&&size.equals("")) {
				items = itemService.filterByBrand(brand);
			}else if(brand.equals("")&&!size.equals("")) {
	        	items = itemService.filterBySize(size);
	        }else if(!brand.equals("")&&!size.equals("")) {
	        	items = itemService.filterBySizeByBrand(brand,size);
	        }
			}
	        	
			
		model.addAttribute("itemList", items);
		model.addAttribute("categoryList", categoryService.listCategory());
		return "index";
	}
	
	@GetMapping("getItems/{categoryId}")
	public ModelAndView getItemFromCategory(@PathVariable("categoryId")String categoryId) {
		ModelAndView mv = new ModelAndView("index");
		long categoryLongId = Long.parseLong(categoryId);
		System.out.println(categoryLongId);
		mv.addObject("itemList", itemService.findByCategory(categoryLongId));
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}
	
	
	@GetMapping("error")
	public String error() {
		return "error";
	}
	
}
