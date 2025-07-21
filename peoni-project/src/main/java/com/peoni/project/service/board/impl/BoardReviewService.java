package com.peoni.project.service.board.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.board.ReviewDTO;
import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardReviewEntity;
import com.peoni.project.repository.board.IBoardReviewRepository;
import com.peoni.project.service.board.IBoardReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardReviewService implements IBoardReviewService{
	
	private final IBoardReviewRepository boardReviewRepository;
	
	@Override
	public List<ReviewDTO> getListOfBoard(Long boardId) {
		
		BoardEntity board = BoardEntity.builder().boardId(boardId).build();
		List<BoardReviewEntity> result = boardReviewRepository.findByBoard(board);
		
		return result.stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public Long register(ReviewDTO dto) {
		
		BoardReviewEntity entity = dtoToEntity(dto);
		boardReviewRepository.save(entity);
		
		return entity.getReviewId();
	}

	@Transactional
	@Override
	public void modify(ReviewDTO dto) {
		
		Optional<BoardReviewEntity> result = boardReviewRepository.findById(dto.getReviewId());
		
		if (result.isPresent()) {
			BoardReviewEntity entity = result.get();
			entity.setContent(dto.getContent());
			
			boardReviewRepository.save(entity);
		}
	}

	@Transactional
	@Override
	public void remove(Long reviewId) {
		boardReviewRepository.deleteById(reviewId);
		
	}

}
