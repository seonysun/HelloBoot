package com.sist.board.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.board.entity.BoardEntity;

@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer>{
	public BoardEntity findByNo(@Param("no") Integer no);
						//SELECT * FROM board WHERE no=
	/* JPA : 메소드를 이용하여 쿼리 문장 자동 생성
	findByName, findByNameLike(String name)
	자동 : save(insert, update), delete(remove)
	검색 : findBy+column
	*/
	
	@Query(value="SELECT no,name,subject,content,pwd,regdate,hit "
			+ "FROM board ORDER BY no DESC "
			+ "LIMIT :start,10",nativeQuery=true)
	public List<BoardEntity> boardListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/10.0) FROM board")
	public int boardTotalPage();
}
