package com.peoni.project.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardReviewEntity;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.repository.board.IBoardRepository;
import com.peoni.project.repository.board.IBoardReviewRepository;
import com.peoni.project.repository.member.IMemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class BoardReviewRepositoryTests {

	@Autowired
    private IBoardReviewRepository boardReviewRepository;
	
	@Autowired
	private IBoardRepository boardRepository;
	
	@Autowired
	private IMemberRepository memberRepository;

//	@Commit
//	@Transactional
//	@Test
//	void testInsertBoardReviews() {
//	    IntStream.rangeClosed(1, 100).forEach(i -> {
//	        Long boardId = (long)(Math.random() * 100) + 1; // 게시글 ID (1~100)
//	        Long memberId = (long)(Math.random() * 100) + 1; // 회원 ID (1~100)
//
//	        MemberEntity member = MemberEntity.builder().mno(memberId).build();
//	        BoardEntity board = BoardEntity.builder().boardId(boardId).build();
//
//	        BoardReviewEntity review = BoardReviewEntity.builder()
//	                .writer(member)
//	                .board(board)
//	                .content("이 게시글에 대한 느낌..." + i)
//	                .build();
//
//	        boardReviewRepository.save(review);
//	    });
//	}
	
//	@Test
//	public void testGetBoardReviews() {
//		BoardEntity board = BoardEntity.builder().boardId(73L).build();
//		
//		List<BoardReviewEntity> result = boardReviewRepository.findByBoard(board);
//		
//		result.forEach(review -> {
//			log.info("리뷰 ID: {}", review.getReviewId());
//			log.info("리뷰 내용: {}", review.getContent());
//			log.info("작성자 이름: {}", review.getWriter().getUserName());
//			log.info("작성자 이메일: {}", review.getWriter().getEmail());
//			log.info("등록일: {}", review.getRegDate());
//			log.info("====================================");
//		});
//	}

}






