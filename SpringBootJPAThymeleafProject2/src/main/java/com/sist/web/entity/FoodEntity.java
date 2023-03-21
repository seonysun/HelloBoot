package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project_food")
@Getter
@Setter
public class FoodEntity {
	@Id
	private int fno;
	private int cno,jjim_count,like_count,hit,good,soso,bad;
	private String poster,name,address,tel,type,price,parking,time,menu;
	private double score;
}
