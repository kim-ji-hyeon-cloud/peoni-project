package com.peoni.project.dto.comm;

import lombok.Data;

@Data
public class ProductSearchDTO {

	private String keyword;
	private String[] typeArr; // 예 : ["P", "C"]
	private Long categoryId;
	private Long storeId;
}
