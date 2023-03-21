package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.BoardDAO;
import com.sist.web.entity.BoardEntity;
import com.sist.web.entity.FoodLocationEntity;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board_list")
	public String board_list(String page, Model model) {
		if(page==null) page="1";
		
		int curpage=Integer.parseInt(page);
		model.addAttribute("curpage", curpage);
		int totalpage=dao.boardTotalPage();
		model.addAttribute("totalpage", totalpage);
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);

		int rowsize=10;
		int start=rowsize*(curpage-1);
		List<BoardEntity> list=dao.boardListData(start);
		model.addAttribute("list", list);
		
		model.addAttribute("main_html", "board/board_list");
		return "main";
	}
	
	@GetMapping("board_detail")
	public String board_detail(int no, Model model) {
		BoardEntity vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/board_detail");
		return "main";
	}
	
	@GetMapping("board_insert")
	public String board_insert(Model model) {
		model.addAttribute("main_html", "board/board_insert");
		return "main";
	}
	
	@PostMapping("board_insert_ok")
	public String board_insert_ok(BoardEntity vo) {
		dao.save(vo);
		return "redirect:../board/board_list";
	}
	
	@GetMapping("board_update")
	public String board_update(int no, Model model) {
		BoardEntity vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/board_update");
		return "main";
	}
	
	@GetMapping("board_delete")
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("main_html", "board/board_delete");
		return "main";
	}
}
