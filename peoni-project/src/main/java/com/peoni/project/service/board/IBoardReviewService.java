package com.peoni.project.service.board;

import java.util.List;

import com.peoni.project.dto.board.ReviewDTO;
import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardReviewEntity;
import com.peoni.project.entity.member.MemberEntity;

public interface IBoardReviewService {

	List<ReviewDTO> getListOfBoard(Long boardId);
	
	Long register(ReviewDTO dto);
	
	void modify(ReviewDTO dto);
	
	void remove(Long reviewId);
	
	default BoardReviewEntity dtoToEntity(ReviewDTO dto) {
		BoardReviewEntity boardReview = BoardReviewEntity.builder()
				.reviewId(dto.getReviewId())
				.content(dto.getContent())
				.board(BoardEntity.builder().boardId(dto.getBoardId()).build())
				.writer(MemberEntity.builder().mno(dto.getMno()).build())
				.build(); 
		
		return boardReview;
	}
	
	default ReviewDTO entityToDto(BoardReviewEntity entity) {
		ReviewDTO boardReviewDTO = ReviewDTO.builder()
				.reviewId(entity.getReviewId())
				.boardId(entity.getBoard().getBoardId())
				.mno(entity.getWriter().getMno())
				.memberName(entity.getWriter().getUserName())
				.content(entity.getContent())
				.regDate(entity.getRegDate())
				.updateDate(entity.getUpdateDate())
				.build();
		
		return boardReviewDTO;
	}
}









