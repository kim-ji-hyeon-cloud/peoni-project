package com.peoni.project.service.board;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.peoni.project.dto.board.BoardDTO;
import com.peoni.project.dto.board.BoardImageDTO;
import com.peoni.project.dto.comm.PageRequestDTO;
import com.peoni.project.dto.comm.PageResultDTO;
import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardImageEntity;
import com.peoni.project.entity.member.MemberEntity;

public interface IBoardService {

	Long register(BoardDTO boardDTO);
	
	BoardDTO getBoard(Long boardId);
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO);
	
	void modify(BoardDTO dto);
	
	void remove(Long boardId);
	
	default BoardDTO entitiesToDto(BoardEntity board, List<BoardImageEntity> imageList, Long reviewCount) {
		
		List<BoardImageDTO> imageDtoList = imageList.stream()
				.filter(img -> img != null)
				.map(img -> BoardImageDTO.builder()
						.imageId(img.getImageId())
						.uuid(img.getUuid())
						.imageName(img.getImageName())
						.path(img.getPath())
						.build())
				.collect(Collectors.toList());
		
		
		
		BoardDTO dto = BoardDTO.builder()
									.boardId(board.getBoardId())
									.title(board.getTitle())
									.content(board.getContent())
									.boardType(board.getBoardType())
									.mno(board.getWriter().getMno())
									.memberName(board.getWriter().getUserName())
									.regDate(board.getRegDate())
									.updateDate(board.getUpdateDate())
									.reviewCount(reviewCount != null ? reviewCount.intValue() : 0)
									.imageDtoList(imageDtoList)
									.build();
		
		
		return dto;
				
	}
	
	default Map<String, Object> dtoToEntity(BoardDTO dto) {
		
		Map<String, Object> entityMap = new HashMap<>();
		
		BoardEntity board = BoardEntity.builder()
											.boardId(dto.getBoardId())
											.title(dto.getTitle())
											.content(dto.getContent())
											.boardType(dto.getBoardType())
											.writer(MemberEntity.builder().mno(dto.getMno()).build())
											.build();
		
		entityMap.put("board", board);
		
		List<BoardImageDTO> imageDtoList = dto.getImageDtoList();
		if (imageDtoList != null && !imageDtoList.isEmpty()) {
			List<BoardImageEntity> imageList = imageDtoList.stream()
					.map(imageDto -> BoardImageEntity.builder()
							.imageId(imageDto.getImageId())
							.uuid(imageDto.getUuid())
							.imageName(imageDto.getImageName())
							.path(imageDto.getPath())
							.board(board)
							.build())
					.collect(Collectors.toList());
			entityMap.put("imageList", imageList);
		}
		
		return entityMap;
	}
}










