package com.sist.web.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JejuFindVO {
	private int no;
	private int curpage,totalpage;
	private String title,poster;
}
