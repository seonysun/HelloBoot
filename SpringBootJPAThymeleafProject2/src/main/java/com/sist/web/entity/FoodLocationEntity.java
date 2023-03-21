package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food_location")
@Getter
@Setter
public class FoodLocationEntity {
	@Id
	private int fno;
	
	private int hit;
	private String poster,name,address,tel,type,price,parking,time,menu;
	private double score;
}
