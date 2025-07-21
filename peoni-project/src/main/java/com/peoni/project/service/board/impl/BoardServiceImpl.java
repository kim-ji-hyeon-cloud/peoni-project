package com.peoni.project.service.board.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.board.BoardDTO;
import com.peoni.project.dto.comm.PageRequestDTO;
import com.peoni.project.dto.comm.PageResultDTO;
import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardImageEntity;
import com.peoni.project.repository.board.IBoardImageRepository;
import com.peoni.project.repository.board.IBoardRepository;
import com.peoni.project.service.board.IBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements IBoardService{
	
	private final IBoardRepository boardRepository;
	
	private final IBoardImageRepository boardImageRepository;
	
	@Transactional
	@Override
	public Long register(BoardDTO boardDTO) {
		Map<String, Object> entityMap = dtoToEntity(boardDTO);
		
		BoardEntity board = (BoardEntity) entityMap.get("board");
		List<BoardImageEntity> imageList = (List<BoardImageEntity>) entityMap.get("imageList");
		
		boardRepository.save(board);
		
		if (imageList != null) {
			imageList.forEach(boardImageRepository::save);
		}
		return board.getBoardId();
	}

	@Override
	public BoardDTO getBoard(Long boardId) {
		List<Object[]> result = boardRepository.getBoardWithAll(boardId);
		
		if (result == null || result.isEmpty()) return null; 
		
		BoardEntity board = (BoardEntity) result.get(0)[0];
		
		List<BoardImageEntity> imageList = result.stream()
				.map(arr -> (BoardImageEntity) arr[1])
				.collect(Collectors.toList());
		
		Long revieCount = (Long) result.get(0)[2];
		
		return entitiesToDto(board, imageList, revieCount);
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {
		var pageable = requestDTO.getPageable(Sort.by("boardId").descending());
		
		var result = boardRepository.getListPage(pageable);
		
		Function<Object[], BoardDTO> fn = (arr -> entitiesToDto(
				(BoardEntity) arr[0], 
				Arrays.asList((BoardImageEntity) arr[1]), 
				(Long) arr[2]
		));		
				
		return new PageResultDTO<>(result, fn);
	}

}
