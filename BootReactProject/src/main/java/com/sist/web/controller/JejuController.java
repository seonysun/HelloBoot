package com.sist.web.controller;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class JejuController {
	@Autowired
	private JejuFoodDAO fdao;
	@Autowired
	private JejuLocationDAO ldao;
	
	@GetMapping("jeju/food_top6")
	public List<JejuFoodEntity> jejutop6(){
		List<JejuFoodEntity> list=fdao.jejuFoodTop6Data();
		return list;
	}
	
	@GetMapping("jeju/food_list_react")
	public List<JejuFoodEntity> food_list_react(String page){
		if(page==null) page="1";
		int rowsize=20;
		int start=rowsize*(Integer.parseInt(page)-1);
		List<JejuFoodEntity> list=fdao.jejuFoodListData(start);
		return list;
	}
	
	@GetMapping("jeju/food_page_react")
	public PageVO food_page_react(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=fdao.jejuFoodTotalPage();
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		PageVO vo=new PageVO();
		vo.setCurpage(curpage);
		vo.setEndpage(endpage);
		vo.setStartpage(startpage);
		vo.setTotalpage(totalpage);
		return vo;
	}
	
	@GetMapping("jeju/food_detail_react")
	public JejuFoodEntity food_detail_react(int no) {
		JejuFoodEntity vo=fdao.findByNo(no);
		return vo;
	}
	
	@GetMapping("jeju/location_list_react")
	public List<JejuLocationEntity> location_list_react(String page){
		if(page==null) page="1";
		int rowsize=20;
		int start=rowsize*(Integer.parseInt(page)-1);
		List<JejuLocationEntity> list=ldao.jejuLocationListData(start);
		return list;
	}

	@GetMapping("jeju/location_page_react")
	public PageVO location_page_react(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=ldao.jejuLocationTotalPage();
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		PageVO vo=new PageVO();
		vo.setCurpage(curpage);
		vo.setEndpage(endpage);
		vo.setStartpage(startpage);
		vo.setTotalpage(totalpage);
		return vo;
	}

	@GetMapping("jeju/location_detail_react")
	public JejuLocationEntity location_detail_react(int no) {
		JejuLocationEntity vo=ldao.findByNo(no);
		return vo;
	}
}
