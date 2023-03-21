package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.CategoryDAO;
import com.sist.web.dao.FoodDAO;
import com.sist.web.dao.FoodLocationDAO;
import com.sist.web.entity.CategoryEntity;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.FoodLocationEntity;

@Controller
@RequestMapping("food/")
public class FoodController {
	@Autowired
	private FoodDAO dao;
	@Autowired
	private CategoryDAO cdao;
	@Autowired
	private FoodLocationDAO fdao;
	
	@GetMapping("food_list")
	public String food_list(int cno, Model model) {
		CategoryEntity cvo=cdao.findByCno(cno);
		model.addAttribute("cvo", cvo);

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
		model.addAttribute("list", list);
		
		model.addAttribute("main_html", "food/food_list");
		return "main";
	}
	
	@GetMapping("food_detail")
	public String food_detail(int fno, Model model) {
		FoodEntity vo=dao.findByFno(fno);
		model.addAttribute("vo", vo);

		String poster=vo.getPoster();
		List<String> pList=new ArrayList<String>();
		StringTokenizer st=new StringTokenizer(poster,"^");
		while(st.hasMoreTokens()) {
			pList.add(st.nextToken());
		}
		model.addAttribute("pList", pList);
		
		String addr1=vo.getAddress();
		addr1=addr1.substring(0, addr1.lastIndexOf("지"));
		model.addAttribute("addr1", addr1.trim());
		
		model.addAttribute("main_html", "food/food_detail");
		return "main";
	}
	
	@RequestMapping("food_find")
	public String food_find(String address, String page, Model model) {
		if(address==null) address="역삼";
		model.addAttribute("address", address);
		if(page==null) page="1";
		
		int curpage=Integer.parseInt(page);
		model.addAttribute("curpage", curpage);
		int totalpage=fdao.foodFindTotalpage(address);
		model.addAttribute("totalpage", totalpage);
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);

		int rowsize=20;
		int start=rowsize*(curpage-1);
		List<FoodLocationEntity> list=fdao.foodFindData(address, start);
		for(FoodLocationEntity vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		model.addAttribute("list", list);
		
		model.addAttribute("main_html", "food/food_find");
		return "main";
	}

	@GetMapping("find_detail")
	public String find_detail(int fno, Model model) {
		FoodLocationEntity vo=fdao.findByFno(fno);
		model.addAttribute("vo", vo);

		String poster=vo.getPoster();
		List<String> pList=new ArrayList<String>();
		StringTokenizer st=new StringTokenizer(poster,"^");
		while(st.hasMoreTokens()) {
			pList.add(st.nextToken());
		}
		model.addAttribute("pList", pList);
		
		String addr1=vo.getAddress();
		addr1=addr1.substring(0, addr1.lastIndexOf("지"));
		model.addAttribute("addr1", addr1.trim());
		
		model.addAttribute("main_html", "food/find_detail");
		return "main";
	}
}
