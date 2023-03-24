package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TwinkDAO extends JpaRepository<TwinkEntity, Integer>{
	@Query(value = "SELECT * FROM twink "
			+ "ORDER BY no LIMIT :start,12", nativeQuery = true)
	public List<TwinkEntity> twinkListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM twink", nativeQuery = true)
	public int twinkTotalpage();
	
	public TwinkEntity findByNo(@Param("no") Integer no);
}
