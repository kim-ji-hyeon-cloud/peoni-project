package com.peoni.project.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.product.ProductDTO;
import com.peoni.project.service.product.IProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ProductServiceTests {

	@Autowired
	private IProductService productService;
	
//	@Test
//	void testRegister() {
//		ProductDTO dto = ProductDTO.builder()
//				.productName("서비스 테스트 상품3")
//				.content("서비스 테스트 상품3입니다.")
//				.price(2500L)
//				.isVisible('Y')
//				.categoryId(1L)
//				.storeId(1L)
//				.mno(1L)
//				.build();
//		
//		productService.register(dto);
//		
//		log.info("등록 완료: {}", dto);
//	}

//	@Test
//	void testGetProductDetail() {
//		Long productId = 1L; 
//
//	    ProductDTO dto = productService.getProduct(productId);
//
//	    log.info("상품 ID: {}", dto.getProductId());
//	    log.info("상품명: {}", dto.getProductName());
//	    log.info("내용: {}", dto.getContent());
//	    log.info("가격: {}", dto.getPrice());
//	    log.info("카테고리: {} - {}", dto.getCategoryId(), dto.getCategoryName());
//	    log.info("편의점: {} - {}", dto.getStoreId(), dto.getStoreName());
//	    log.info("작성자: {} - {}", dto.getMno(), dto.getWriterName());
//	    log.info("공개 여부: {}", dto.getIsVisible());
//	    log.info("조회수: {}", dto.getViewCount());
//	}
	
//	@Test
//	@Transactional
//	void testGetAllProducts() {
//		
//		List<ProductDTO> products = productService.getAllProducts();
//		
//		log.info("==== 전체 상품 수: {} ====", products.size());
//		for (ProductDTO dto : products) {
//			log.info("상품정보: {}", dto);
//		}
//		
//	}
	
//	@Test
//	@Transactional
//	void testGetNewProducts() {
//		
//		List<ProductDTO> products = productService.getNewProducts();
//		
//		log.info("==== 신상품 수: {} ====", products.size());
//		for (ProductDTO dto : products) {
//			log.info("신상품정보: {}", dto);
//		}
//	}
	
//	@Test
//	@Transactional
//	void testGetHotProducts() {
//		List<ProductDTO> products = productService.getHotProducts();
//		
//		log.info("==== 핫이슈상품 수: {} ====", products.size());
//		for (ProductDTO dto : products) {
//			log.info("핫이슈상품정보: {}", dto);
//		}
//	}
	
//	@Test
//	void testUpdateProduct() {
//		
//		ProductDTO dto = ProductDTO.builder()
//				.productId(4L)
//				.productName("수정된 상품명")
//				.content("수정된 상품입니다")
//				.price(9000L)
//				.isVisible('Y')
//				.categoryId(1L)
//				.storeId(1L)
//				.mno(1L)
//				.build();
//		
//		productService.update(dto);
//		
//		log.info("상품 수정 완료");
//	}
	
//	@Test
//	@Transactional
//	@Commit
//	void testDeleteProducts() {
//		
//		Long productId = 1L;
//		
//		productService.delete(productId);
//		
//		log.info("상품{} 삭제 완료", productId);
//	}
}






