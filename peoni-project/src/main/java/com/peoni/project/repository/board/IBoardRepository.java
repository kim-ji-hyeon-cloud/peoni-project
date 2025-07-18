package com.peoni.project.repository.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peoni.project.entity.board.BoardEntity;

public interface IBoardRepository extends JpaRepository<BoardEntity, Long>{

	
	@Query("SELECT b, bi, COUNT(br) "
		 + "FROM BoardEntity b "
		 + "LEFT JOIN BoardImageEntity bi ON bi.board = b "
		 + "AND bi.imageId = (SELECT MAX(bi2.imageID) " 
		 + "				  FROM BoardImageEntity bi2 "
		 + "				  WHERE bi2.board = b) "
		 + "LEFT JOIN BoardReviewEntity br ON br.board = b "
		 + "GROUP BY b, bi")
	Page<Object[]> getListPage(Pageable pageable);
	
	@Query("SELECT b, bi, COUNT(br) "
		 + "FROM BoardEntity b "
		 + "LEFT JOIN BoardImageEntity bi ON bi.board = b "
		 + "LEFT JOIN BoardReviewEntity br ON br.board = b "
		 + "WHERE b.boardId = :boardId "
		 + "GROUP BY b, bi")
	List<Object[]> getBoardWithAll (@Param("boardId") Long boardId);
}










