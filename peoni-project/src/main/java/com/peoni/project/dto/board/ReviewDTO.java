package com.peoni.project.dto.board;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	private Long reviewId;
	private String content;
	private Long boardId;
	private Long mno;
	private String memberName;
	
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
