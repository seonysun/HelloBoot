package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
public class ItemEntity {
	@Id
	private int ino;
	
	private int price,sale,stock,like_cnt,jjim_cnt,icno;
	private String image,name,description,status,brand;
}
