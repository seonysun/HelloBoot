package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "camping")
@Getter
@Setter
public class CampingEntity {
	@Id
	private int cno;
	
	private int hit;
	private String image,name,tel,address,camp_env,camp_type,homepage,period,day;
}
