package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.CategoryDataMapping;
import com.sist.web.entity.CategoryEntity;
@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity, Integer>{
	@Query(value = "SELECT cno,title,poster FROM project_category", nativeQuery = true)
	public List<CategoryDataMapping> categoryListData();
	
	public CategoryEntity findByCno(@Param("cno") Integer cno);
	
	@Query(value = "SELECT * FROM project_category "
			+ "WHERE cno BETWEEN :start AND :end", nativeQuery = true)
	public List<CategoryEntity> categoryChangeData(@Param("start") Integer start, @Param("end") Integer end);
}
