package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "snack")
@Getter
@Setter
public class SnackEntity {
	@Id
	private int no;
	
	private int type;
	private String image,name,manu,origin,cost,point,content;
}
