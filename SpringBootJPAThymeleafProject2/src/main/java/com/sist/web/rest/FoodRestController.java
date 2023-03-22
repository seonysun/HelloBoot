package com.sist.web.rest;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("food/")
@CrossOrigin("http://localhost:3000")
public class FoodRestController {
	@Autowired
	private CategoryDAO cdao;
	@Autowired
	private FoodDAO fdao;
	@Autowired
	private FoodLocationDAO ldao;
	
	@GetMapping(value = "category_react")
	public List<CategoryEntity> categoryChangeData(int cno){
		int start=0, end=0;
		if(cno==1) {
			start=1;
			end=12;
		} else if(cno==2) {
			start=13;
			end=18;
		} else if(cno==3) {
			start=19;
			end=30;
		}
		List<CategoryEntity> list=cdao.categoryChangeData(start, end);
		return list; //Boot에서는 RestController 사용 시 JSON 자동 변환
	}
	
	@GetMapping("location_react")
	public List<FoodLocationEntity> locationData(){
		List<FoodLocationEntity> list=ldao.locationData(0);
		for(FoodLocationEntity vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		return list;
	}
	
	@GetMapping("category_info_react")
	public CategoryEntity cateInfo(int cno) {
		CategoryEntity vo=cdao.findByCno(cno);
		return vo;
	}
	
	@GetMapping("food_list_react")
	public List<FoodEntity> foodList(int cno){
		List<FoodEntity> list=fdao.findByCno(cno);
		for(FoodEntity vo:list) {
			String addr=vo.getAddress();
			addr=addr.substring(0, addr.lastIndexOf("지"));
			vo.setAddress(addr);
			
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		return list;
	}
	
	@GetMapping("food_detail_react")
	public FoodEntity foodDetail(int fno) {
		FoodEntity vo=fdao.findByFno(fno);
		String addr1=vo.getAddress();
		addr1=addr1.substring(0, addr1.lastIndexOf("지"));
		vo.setAddress(addr1);
		return vo;
	}
}
