package com.peoni.project.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardReviewEntity;
import com.peoni.project.entity.member.MemberEntity;

public interface IBoardReviewRepository extends JpaRepository<BoardReviewEntity, Long>{

	
	// 게시글 기준으로 댓글 + 작성자(member) 함께 조회
	@EntityGraph(attributePaths = {"writer"})
	List<BoardReviewEntity> findByBoard(BoardEntity board);
	
	// 특정 회원이 작성한 모든 댓글 삭제 (회원 탈퇴 시 연관 삭제용)
	@Modifying
	@Query("DELETE FROM BoardReviewEntity r WHERE r.writer = :member")
	void deleteByWriter(@Param("member") MemberEntity member);
}
