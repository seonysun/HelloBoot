package com.sist.web.controller;
import java.util.*;

import com.sist.web.entity.*;
import com.sist.web.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class CampingController {
	@Autowired
	private CampingDAO dao;
	@Autowired
	private ItemDAO idao;
	@Autowired
	private SnackDAO sdao;
	
	@GetMapping("camping/camping_top6")
	public List<CampingEntity> camping_top6(){
		List<CampingEntity> list=dao.mainCampingData();
		for(CampingEntity vo:list) {
			String address=vo.getAddress();
			if(address.length()>=20) {
				address=address.substring(0,20);
				vo.setAddress(address);
			}
		}
		return list;
	}
	@GetMapping("item/item_top4")
	public List<ItemEntity> item_top4(){
		List<ItemEntity> list=idao.mainItemData();
		return list;
	}
	@GetMapping("snack/snack_top4")
	public List<SnackEntity> snack_top4(){
		List<SnackEntity> list=sdao.mainSnackData();
		return list;
	}
	
	@GetMapping("camping/list_react")
	public List<CampingEntity> campingListData(String page){
		int rowsize=15;
		int start=rowsize*(Integer.parseInt(page)-1);
		List<CampingEntity> list=dao.campingListData(start);
		return list;
	}

	@GetMapping("camping/camping_page_react")
	public PageVO camping_page_react(String page) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.campingTotalpage();
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
	
	@GetMapping("camping/camping_detail_react")
	public CampingEntity camping_detail_react(int cno) {
		CampingEntity vo=dao.findByCno(cno);
		return vo;
	}
	
	@GetMapping("camping/camp_find_react")
	public List<CampingEntity> camp_find_react(String page, String addr){
		int start=15*(Integer.parseInt(page)-1);
		List<CampingEntity> list=dao.campingFindData(addr, start);
		return list;
	}
	
	@GetMapping("camping/find_page_react")
	public PageVO find_page_react(String page, String addr) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.campingFindTotalPage(addr);
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
	
	@GetMapping("item/item_list_react")
	public List<ItemEntity> item_list_react(){
		List<ItemEntity> list=idao.itemListData();
		for(ItemEntity vo:list) {
			String pic=vo.getImage();
			pic=pic.substring(0, pic.indexOf(","));
			vo.setImage(pic);
		}
		return list;
	}
}
