package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.news.*;
@RestController
@CrossOrigin("http://localhost:3000")
public class NewsController {
	@Autowired
	private NewsManager mgr;
	
	@GetMapping("news/news_list_react")
	public List<NewsVO> news_list(String title){
		List<NewsVO> list=mgr.newsListData(title);
		return list;
	}
	
	@GetMapping("news/news_aop_react")
	public List<NewsVO> news_aop(){
		List<NewsVO> list=mgr.newsListData("캠핑");
		List<NewsVO> nList=new ArrayList<NewsVO>();
		for(int i=0;i<6;i++) {
			NewsVO vo=list.get(i);
			nList.add(vo);
		}
		return nList;
	}
}
