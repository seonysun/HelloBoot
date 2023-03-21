package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.CategoryDAO;
import com.sist.web.dao.FoodDAO;
import com.sist.web.entity.CategoryEntity;
import com.sist.web.entity.FoodEntity;

@Controller
@RequestMapping("food/")
public class FoodController {
	@Autowired
	private FoodDAO dao;
	@Autowired
	private CategoryDAO cdao;
	
	@GetMapping("food_list")
	public String food_list(int cno, Model model) {
		CategoryEntity cvo=cdao.findByCno(cno);
		List<FoodEntity> list=dao.findByCno(cno);
		for(FoodEntity vo:list) {
			String addr=vo.getAddress();
			addr=addr.substring(0, addr.lastIndexOf("지"));
			vo.setAddress(addr);
			
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		model.addAttribute("cvo", cvo);
		model.addAttribute("list", list);
		model.addAttribute("main_html", "food/food_list");
		return "main";
	}
}
