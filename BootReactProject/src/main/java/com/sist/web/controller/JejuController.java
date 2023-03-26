package com.sist.web.controller;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
	
	@GetMapping("jeju/food_top9")
	public List<JejuFoodEntity> jejutop9(){
		List<JejuFoodEntity> list=fdao.jejuFoodTop9Data();
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
	
//	@GetMapping("jeju/jeju_cookie_react")
//	public List<JejuFoodEntity> jeju_cookie(HttpServletRequest request){
//		List<JejuFoodEntity> list=new ArrayList<JejuFoodEntity>();
//		Cookie[] cookies=request.getCookies();
//		if(cookies!=null) {
//			for(int i=cookies.length-1;i>=0;i--) {
//				if(cookies[i].getName().startsWith("jeju")) {
//					String no=cookies[i].getValue();
//					JejuFoodEntity vo=fdao.findByNo(Integer.parseInt(no));
//					list.add(vo);
//				}
//			}
//		}
//		return list;
//	}
	
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
	
	@GetMapping("jeju/food_find_react")
	public List<JejuFindVO> food_find_react(String page, String title){
		List<JejuFindVO> list=new ArrayList<JejuFindVO>();
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int start=12*(curpage-1);
		List<JejuFoodEntity> fList=fdao.jejuFindData(title, start);
		int totalpage=fdao.jejuFindTotalPage(title);
		int i=0;
		for(JejuFoodEntity fvo:fList) {
			JejuFindVO vo=new JejuFindVO(); //필요한 데이터만 선별해서 저장
			vo.setNo(fvo.getNo());
			vo.setPoster(fvo.getPoster());
			vo.setTitle(fvo.getTitle());
			if(i==0) {
				vo.setCurpage(curpage);
				vo.setTotalpage(totalpage);
			}
			list.add(vo);
			i++;
		}
		return list;
	}
}
