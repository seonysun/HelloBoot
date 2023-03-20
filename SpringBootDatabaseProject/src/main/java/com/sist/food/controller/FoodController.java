package com.sist.food.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.food.vo.*;
import com.sist.food.service.*;
@Controller
public class FoodController {
	@Autowired
	private FoodService service;
	
	@GetMapping("/")
	public String food_category(Model model) {
		List<CategoryVO> list=service.categoryListData();
		model.addAttribute("list", list);
		return "category";
	}
}
