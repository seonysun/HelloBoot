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
			+ "ORDER BY ino LIMIT 0,4", nativeQuery = true)
	public List<ItemEntity> mainItemData();
	
	@Query(value = "SELECT * FROM item "
			+ "ORDER BY ino LIMIT 0,3", nativeQuery = true)
	public List<ItemEntity> itemListData();
	
	@Query(value = "SELECT CEIL(COUNT(*)/8.0) FROM item", nativeQuery = true)
	public int itemTotalpage();
	
	public ItemEntity findByIno(@Param("ino") Integer ino);
}
