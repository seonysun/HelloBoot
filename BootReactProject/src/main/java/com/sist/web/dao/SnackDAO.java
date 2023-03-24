package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackDAO extends JpaRepository<SnackEntity, Integer>{
	@Query(value = "SELECT * FROM snack "
			+ "ORDER BY no LIMIT :start,12", nativeQuery = true)
	public List<SnackEntity> snackListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM snack", nativeQuery = true)
	public int snackTotalpage();
	
	public SnackEntity findByNo(@Param("no") Integer no);
}
