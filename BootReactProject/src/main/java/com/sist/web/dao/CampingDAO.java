package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingDAO extends JpaRepository<CampingEntity, Integer>{
	@Query(value = "SELECT * FROM camping "
			+ "ORDER BY hit DESC LIMIT 127,6", nativeQuery = true)
	public List<CampingEntity> mainCampingData();
	
	@Query(value = "SELECT * FROM camping "
			+ "ORDER BY cno LIMIT :start,15", nativeQuery = true)
	public List<CampingEntity> campingListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/15.0) FROM camping", nativeQuery = true)
	public int campingTotalpage();
	
	public CampingEntity findByCno(@Param("cno") Integer cno);
	
	@Query(value = "SELECT * FROM camping "
			+ "WHERE address LIKE CONCAT('%',:addr,'%') "
			+ "LIMIT :start,15", nativeQuery = true)
	public List<CampingEntity> campingFindData(@Param("addr") String addr, @Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/15.0) FROM camping "
			+ "WHERE address LIKE CONCAT('%',:addr,'%')", nativeQuery = true)
	public int campingFindTotalPage(@Param("addr") String addr);
}
