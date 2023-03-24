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
public class TwinkController {
	@Autowired
	private TwinkDAO dao;
	
	@GetMapping("twink/twink_list_react")
	public List<TwinkEntity> twinkListData(String page){
		int rowsize=20;
		int start=rowsize*(Integer.parseInt(page)-1);
		List<TwinkEntity> list=dao.twinkListData(start);
		return list;
	}

	@GetMapping("twink/twink_page_react")
	public JejuPageVO recipe_page_react(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.twinkTotalpage();
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		JejuPageVO vo=new JejuPageVO();
		vo.setCurpage(curpage);
		vo.setEndpage(endpage);
		vo.setStartpage(startpage);
		vo.setTotalpage(totalpage);
		return vo;
	}
}
