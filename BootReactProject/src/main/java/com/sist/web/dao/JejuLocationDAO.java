package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JejuLocationDAO extends JpaRepository<JejuLocationEntity, Integer>{
	@Query(value = "SELECT * FROM jeju_location "
			+ "LIMIT 0,6", nativeQuery = true)
	public List<JejuLocationEntity> jejuLocationTop6Data();
	
	@Query(value = "SELECT * FROM jeju_location "
			+ "ORDER BY no LIMIT :start,20", nativeQuery = true)
	public List<JejuLocationEntity> jejuLocationListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM jeju_location", nativeQuery = true)
	public int jejuLocationTotalPage();
	
	public JejuLocationEntity findByNo(@Param("no") Integer no);
}
