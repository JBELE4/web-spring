package com.info.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Item;
import com.info.model.User;
import com.info.service.ItemService;
import com.info.service.UserService;

@Controller
@RequestMapping("profile")
public class ProfileController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("cart-item")
	public ModelAndView cartItem(Principal principal) {
		ModelAndView mv = new ModelAndView("profile/cart-item");
		User user = userService.findByEmail(principal.getName());
		mv.addObject("user", user);
		int total = findSum(user);
		mv.addObject("total", total);
		return mv;
	}
	
	private int findSum(User user) {
		List<Item> list = user.getItemList();
		int sum =0;
		for(int i=0; i<list.size(); i++) {
			Item it = list.get(i);
			sum += it.getPrice();
		}
		return sum;
	}

	@GetMapping("addToCart/{itemId}")
	public ModelAndView addToCart(@PathVariable("itemId")String itemId,Principal principal) {
		ModelAndView mv = new ModelAndView("profile/cart-item");
		User user = userService.findByEmail(principal.getName());
		long itemLongId = Long.parseLong(itemId);
		Item item = itemService.getItemById(itemLongId).get();
		user.setItem(item);	
		userService.update(user);
		int total = findSum(user);
		mv.addObject("total", total);
		mv.addObject("user", user);
		return mv;
	}
	
	@GetMapping("delete-itemcarrito/{itemId}")
	public ModelAndView deleteItem(@PathVariable("itemId")long itemId,Principal principal) {
		ModelAndView mv = new ModelAndView("profile/cart-item");
		User user = userService.findByEmail(principal.getName());
		Item item = itemService.getItemById(itemId).get();
		
		user.removeItem(item);
		userService.update(user);
		
		int total = findSum(user);
		mv.addObject("total", total);
		mv.addObject("user", user);
		return mv;
	}
	
}
