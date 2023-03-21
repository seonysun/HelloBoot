package com.sist.web.rest;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@PostMapping("board/board_update_ok")
	public String board_update_ok(BoardEntity vo, Model model) {
		BoardEntity dbvo=dao.findByNo(vo.getNo());
		String result="";
		if(dbvo.getPwd().equals(vo.getPwd())) {
			dao.save(vo);
			result="<script>"
					+ "location.href=\"/board/board_detail?no="+vo.getNo()+"\";"
					+ "</script>";
		} else {
			result="<script>"
					+ "alert(\"비밀번호가 틀립니다!\");"
					+ "history.back()"
					+ "</script>";
		}
		return result;
	}
	
	@PostMapping("board/board_delete_ok")
	public String board_delete_ok(int no, String pwd, Model model) {
		BoardEntity vo=dao.findByNo(no);
		String result="";
		if(vo.getPwd().equals(pwd)) {
			dao.delete(vo);
			result="<script>"
					+ "location.href=\"/board/board_list\";"
					+ "</script>";
		} else {
			result="<script>"
					+ "alert(\"비밀번호가 틀립니다!\");"
					+ "history.back()"
					+ "</script>";
		}
		return result;
	}
}
