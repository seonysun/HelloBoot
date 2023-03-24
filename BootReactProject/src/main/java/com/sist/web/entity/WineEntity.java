package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sul_item")
@Getter
@Setter
public class WineEntity {
	@Id
	private int ino;
	
	private int icno,hit,jjim,price,account;
	private String poster,name,nation,alcohol,capacity,name_sub,place,vintage,winery,first_price,discount;
}
