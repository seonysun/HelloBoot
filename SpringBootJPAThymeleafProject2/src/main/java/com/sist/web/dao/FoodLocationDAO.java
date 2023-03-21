package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.FoodLocationEntity;
@Repository
public interface FoodLocationDAO extends JpaRepository<FoodLocationEntity, Integer>{
	@Query(value = "SELECT * FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%') "
			+ "ORDER BY fno LIMIT :start,20", nativeQuery = true)
	public List<FoodLocationEntity> foodFindData(@Param("address") String address, @Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%')", nativeQuery = true)
	public int foodFindTotalpage(String address);
								//1개 입력할 때는 Param 생략 가능
	
	public FoodLocationEntity findByFno(@Param("fno") Integer fno);
}
