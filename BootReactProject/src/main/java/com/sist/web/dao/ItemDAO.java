package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemEntity, Integer>{
	@Query(value = "SELECT * FROM item "
			+ "ORDER BY ino LIMIT :start,8", nativeQuery = true)
	public List<ItemEntity> snackListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/8.0) FROM item", nativeQuery = true)
	public int snackTotalpage();
	
	public ItemEntity findByINo(@Param("ino") Integer ino);
}
