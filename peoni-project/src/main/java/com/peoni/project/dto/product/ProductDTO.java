package com.peoni.project.dto.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long productId;
	private String productName;
	private String content;
	private Long price;
	private int viewCount;
	private char isVisible;
	private String imagePath;
	private String image;
	private String eventflag;
	
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	private Long categoryId;
	private String categoryName;
	
	private Long storeId;
	private String storeName;
	
	private Long mno;
	private String writerName;
}
















