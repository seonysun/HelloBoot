package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "twink")
@Getter
@Setter
public class TwinkEntity {
	@Id
	private int no;
	
	private String name,poster,price,material,origin,detail;
}
