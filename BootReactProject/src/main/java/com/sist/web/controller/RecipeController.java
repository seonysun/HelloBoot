package com.sist.web.controller;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.web.entity.*;
import com.sist.web.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class RecipeController {
	@Autowired
	private RecipeDAO dao;
	
	@GetMapping("recipe/list_react")
	public List<RecipeEntity> recipeListData(String page){
		int rowsize=20;
		int start=rowsize*(Integer.parseInt(page)-1);
		List<RecipeEntity> list=dao.recipeListData(start);
		return list;
	}

	@GetMapping("recipe/recipe_page_react")
	public PageVO recipe_page_react(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.recipeTotalPage();
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
	
	@GetMapping("recipe/cookie_react")
	public List<RecipeEntity> cookieListDate(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		List<RecipeEntity> list=new ArrayList<RecipeEntity>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("recipe")) {
					String no=cookies[i].getValue();
					RecipeEntity vo=dao.findByNo(Integer.parseInt(no));
					list.add(vo);
				}
			}
		}
		return list;
	}
}
