package com.peoni.project.service.board.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.board.ReviewDTO;
import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardReviewEntity;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.repository.board.IBoardRepository;
import com.peoni.project.repository.board.IBoardReviewRepository;
import com.peoni.project.repository.member.IMemberRepository;
import com.peoni.project.service.board.IBoardReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardReviewServiceImpl implements IBoardReviewService{
	
	private final IBoardReviewRepository boardReviewRepository;
	private final IBoardRepository boardRepository;
	private final IMemberRepository memberRepository;
	
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

        // 🔥 BoardEntity, MemberEntity는 영속 객체로 가져오기
        BoardEntity board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));

        MemberEntity writer = memberRepository.getReferenceById(dto.getMno());

        // 🔄 dtoToEntity 사용하되, board와 writer는 교체
        BoardReviewEntity entity = dtoToEntity(dto);
        entity.setBoard(board);
        entity.setWriter(writer);

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
