package com.peoni.project.controller.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peoni.project.dto.board.ReviewDTO;
import com.peoni.project.dto.member.MemberDTO;
import com.peoni.project.service.board.IBoardReviewService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board/reviews")
public class BoardReviewController {

	private final IBoardReviewService boardReviewService;
	
	// 댓글 목록 조회
	@GetMapping("/{boardId}/all")
	public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("boardId") Long boardId) {
		
		log.info("댓글 목록 요청 boardId = {}", boardId);
		
		return ResponseEntity.ok(boardReviewService.getListOfBoard(boardId));
	}
	
	// 댓글 등록
	@PostMapping("/{boardId}")
	public ResponseEntity<Long> register(@PathVariable("boardId") Long boardId,
										 @RequestBody ReviewDTO reviewDTO,
										 HttpSession session) {
		
		log.info("댓글 등록: {}", reviewDTO);
		
		// 세션에서 로그인 정보 가져오기
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
	    if (memberDTO == null) {
	        return ResponseEntity.status(401).build(); // 비로그인 사용자
	    }
		
	 // 세션 정보를 기반으로 안전하게 작성자 정보 설정
	    reviewDTO.setBoardId(boardId);
	    reviewDTO.setMno(memberDTO.getMno());
	    reviewDTO.setMemberName(memberDTO.getUserName());

	    Long reviewId = boardReviewService.register(reviewDTO);
	    return ResponseEntity.ok(reviewId);
	}
	
	// 댓글 수정
	@PutMapping("/{boardId}/{reviewId}")
	public ResponseEntity<Long> modify(@PathVariable("boardId") Long boardId,
									   @PathVariable("reviewId") Long reviewId,
									   @RequestBody ReviewDTO reviewDTO) {
		
		log.info("댓글 수정: {}", reviewDTO);
		
		reviewDTO.setBoardId(boardId);
		reviewDTO.setReviewId(reviewId);
		boardReviewService.modify(reviewDTO);
		
		return ResponseEntity.ok(reviewId);
	}
	
	// 댓글 삭제
	@DeleteMapping("/{boardId}/{reviewId}")
	public ResponseEntity<Long> remove(@PathVariable("boardId") Long boardId,
									   @PathVariable("reviewId") Long reviewId) {
		
		log.info("댓글 삭제: {}", reviewId);
		
		boardReviewService.remove(reviewId);
		
		return ResponseEntity.ok(reviewId);
	}
}














