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
public class WineController {
	@Autowired
	private WineDAO dao;
	
	@GetMapping("wine/wine_list_react")
	public List<WineEntity> wineListData(String page){
		int rowsize=20;
		int start=rowsize*(Integer.parseInt(page)-1);
		List<WineEntity> list=dao.wineListData(start);
		return list;
	}

	@GetMapping("wine/wine_page_react")
	public PageVO recipe_page_react(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.wineTotalpage();
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
}
