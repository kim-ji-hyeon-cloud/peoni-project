package com.peoni.project.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.entity.product.CategoryEntity;
import com.peoni.project.entity.product.ProductEntity;
import com.peoni.project.entity.store.StoreEntity;
import com.peoni.project.repository.product.IProductRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ProductRepositoryTest {

	@Autowired
	private IProductRepository productRepository;
	
//	@Test
//	void testInsertDummyProduct() {
//		CategoryEntity category = CategoryEntity.builder().categoryId(1L).build();
//        StoreEntity store = StoreEntity.builder().storeId(1L).build();
//        MemberEntity member = MemberEntity.builder().mno(1L).build();
//		
//        IntStream.rangeClosed(1, 20).forEach(i -> {
//        	ProductEntity product = ProductEntity.builder()
//        			.productName("테스트상품" + i)
//        			.price(1000L)
//        			.content("설명입니다 " + i)
//        			.viewCount(i * 5)
//        			.isVisible('Y')
//        			.categoryId(category)
//        			.storeId(store)
//        			.writer(member)
//        			.build();
//        		
//        	productRepository.save(product);
//        	log.info("등록된 상품 : " + product);
//        			
//        });
//	}

//	@Transactional
//	@Test
//	void testFindViewCount() {
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		List<ProductEntity> result = productRepository.findPopularProducts(pageable);
//		log.info("인기 상품 수: {}", result.size());
//		
//		result.forEach(product -> log.info("인기상품: {}", product));
//	}
	
//	@Transactional
//	@Test
//	void testNewProducts() {
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		List<ProductEntity> result = productRepository.findNewProducts(pageable);
//		log.info("최신 상품 수: {}", result.size());
//		
//		result.forEach(product -> log.info("최신상품:{}", product));
//	}
	
//	@Transactional
//	@Test
//	void testReadProduct() {
//		Long productId = 1L;
//		ProductEntity product = productRepository.findProductWithDetails(productId);
//		log.info("상세조회 결과 : " + product);
//	}
	
	// 기본적으로 JUnit + @Transactional 조합에서는 테스트가 끝난 후 롤백	
//	@Transactional
//	@Rollback(false) 
//	@Test
//	void testViewCount() {
//		Long productId = 20L;
//		productRepository.incrementViewCount(productId);
//		log.info("조회수 증가");
//	}
	
//	@Transactional
//	@Test
//	void testAllProducts() {
//		Pageable pageable = PageRequest.of(0, 20);
//		List<ProductEntity> result = productRepository.findAllProducts(pageable);
//		
//		log.info("전체 상품 수 : {}", result.size());
//		result.forEach(product -> log.info("상품 : {}", product));
//	}
}









