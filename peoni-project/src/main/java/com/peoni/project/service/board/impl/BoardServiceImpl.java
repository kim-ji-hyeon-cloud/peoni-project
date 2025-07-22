package com.peoni.project.service.board.impl;

import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.peoni.project.dto.board.BoardDTO;
import com.peoni.project.dto.board.BoardImageDTO;
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

	    // 🔸 Pageable 추출 (정렬 기준 포함)
	    var pageable = requestDTO.getPageable(Sort.by("boardId").descending());

	    // 🔸 QueryDSL 기반 검색 with pageable
	    var result = boardRepository.searchPageWithFilter(requestDTO, pageable);

	    Function<Object[], BoardDTO> fn = (arr -> entitiesToDto(
	            (BoardEntity) arr[0],
	            Arrays.asList((BoardImageEntity) arr[1]),
	            (Long) arr[2]
	    ));

	    return new PageResultDTO<>(result, fn);
	}

	@Transactional
	@Override
	public void modify(BoardDTO dto) {
	    Long boardId = dto.getBoardId();

	    BoardEntity board = boardRepository.findById(boardId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

	    // 제목, 내용, 타입 변경
	    board.changeTitle(dto.getTitle());
	    board.changeContent(dto.getContent());
	    board.changeBoardType(dto.getBoardType());

	    // 새로 첨부된 이미지가 있다면 추가
	    List<BoardImageDTO> imageDtoList = dto.getImageDtoList();

	    if (imageDtoList != null && !imageDtoList.isEmpty()) {
	        List<BoardImageEntity> imageList = imageDtoList.stream()
	                .map(imgDto -> BoardImageEntity.builder()
	                        .uuid(imgDto.getUuid())
	                        .imageName(imgDto.getImageName())
	                        .path(imgDto.getPath())
	                        .board(board)
	                        .build())
	                .toList();

	        boardImageRepository.saveAll(imageList);
	    }

	    boardRepository.save(board);
	}

	@Transactional
	@Override
	public void remove(Long boardId) {
	    BoardEntity board = boardRepository.findById(boardId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

	    // 연관 이미지 먼저 삭제
	    List<BoardImageEntity> imageList = boardImageRepository.findByBoard(board);
	    if (imageList != null && !imageList.isEmpty()) {
	        boardImageRepository.deleteAll(imageList);
	    }

	    // 게시글 삭제
	    boardRepository.delete(board);
	}

}
