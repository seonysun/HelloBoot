package com.sist.web.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JejuPageVO {
	private int curpage;
	private int totalpage;
	private int startpage;
	private int endpage;
}
