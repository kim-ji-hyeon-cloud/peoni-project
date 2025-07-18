package com.peoni.project.repository.product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peoni.project.entity.product.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, Long>, ProductSearchRepository{
	
	// 조회수 순으로 상위 10개 인기 상품
	@Query("SELECT p FROM ProductEntity p WHERE p.isVisible = 'Y' ORDER BY p.viewCount DESC")
	List<ProductEntity> findPopularProducts(Pageable pageable);
	
	// 최신 등록 순으로 상위 10개 신상품
	@Query("SELECT p FROM ProductEntity p WHERE p.isVisible = 'Y' ORDER BY p.regDate DESC")
	List<ProductEntity> findNewProducts(Pageable pageable);
	
	// 조회수 증가
	@Modifying
	@Query("UPDATE ProductEntity p SET p.viewCount = p.viewCount + 1 WHERE p.productId = :productId")
	void incrementViewCount(@Param("productId") Long productId);
	
	// 상품 상세 조회
	@Query("SELECT p FROM ProductEntity p "
		     + "JOIN FETCH p.categoryId "
		     + "JOIN FETCH p.storeId "
		     + "WHERE p.productId = :productId")
		ProductEntity findProductWithDetails(@Param("productId") Long productId);
	
	// 전체 상품 목록
	@Query("SELECT p FROM ProductEntity p WHERE p.isVisible = 'Y' ORDER BY p.productId DESC")
	List<ProductEntity> findAllProducts(Pageable pageable);
}
















