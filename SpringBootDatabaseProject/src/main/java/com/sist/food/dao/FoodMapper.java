package com.sist.food.dao;
import java.util.*;
import com.sist.food.vo.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FoodMapper {
	public List<CategoryVO> categoryListData();
}
