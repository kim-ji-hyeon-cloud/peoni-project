package com.peoni.project.dto.board;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

	private Long boardId;
	private String title;
	private String content;
	private String boardType;
	
	private Long mno;
	private String memberName;
	
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	@Builder.Default
	private List<BoardImageDTO> imageDtoList = new ArrayList<>();
	
	private int reviewCount;
}
