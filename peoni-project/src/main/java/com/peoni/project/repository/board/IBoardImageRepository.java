package com.peoni.project.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardImageEntity;

public interface IBoardImageRepository extends JpaRepository<BoardImageEntity, Long>{

	
	// 게시글에 등록된 모든 이미지 조회
	List<BoardImageEntity> findByBoard(BoardEntity board);
	
	void deleteByBoard(BoardEntity board);
}
