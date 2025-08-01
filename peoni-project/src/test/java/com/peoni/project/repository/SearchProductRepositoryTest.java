package com.peoni.project.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.comm.ProductSearchDTO;
import com.peoni.project.entity.product.ProductEntity;
import com.peoni.project.repository.product.ProductSearchRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class SearchProductRepositoryTest {

	@Autowired
	private ProductSearchRepository productSearchRepository;
	
//	@Test
//	@Transactional
//    void testSearchByKeyword() {
//        ProductSearchDTO dto = new ProductSearchDTO();
//        dto.setKeyword("1");
//        dto.setTypeArr(new String[]{"P", "C"});
//
//        Pageable pageable = PageRequest.of(0, 10);
//
//        Page<ProductEntity> result = productSearchRepository.searchProducts(dto, pageable);
//        log.info("검색된 상품 수: {}", result.getTotalElements());
//
//        result.getContent().forEach(product -> 
//            log.info("검색 결과: {}", product)
//        );
//    }
	
	
//	@Test
//	@Transactional
//	void testSearchByCategory() {
//		
//		ProductSearchDTO dto = new ProductSearchDTO();
//		dto.setCategoryId(2L);
//		
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		Page<ProductEntity> result = productSearchRepository.searchProducts(dto, pageable);
//		log.info("카테고리 검색 수 : {}", result.getTotalElements());
//		
//		result.getContent().forEach(product -> log.info("검색 결과: {}", product));
//	}
}










