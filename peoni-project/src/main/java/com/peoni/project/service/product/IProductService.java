package com.peoni.project.service.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.peoni.project.dto.comm.ProductSearchDTO;
import com.peoni.project.dto.product.ProductDTO;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.entity.product.CategoryEntity;
import com.peoni.project.entity.product.ProductEntity;
import com.peoni.project.entity.store.StoreEntity;

public interface IProductService {

	
	List<ProductDTO> getAllProducts();
	
	ProductDTO getProduct(Long productId);
	
	List<ProductDTO> getNewProducts();
	
	List<ProductDTO> getHotProducts();
	
	void register(ProductDTO dto);
	
	void update(ProductDTO dto);
	
	void delete(Long productId);
	
	Page<ProductDTO> searchProducts(ProductSearchDTO dto, Pageable pageable);
	
	default ProductDTO entityToDto(ProductEntity entity) {
		ProductDTO dto = ProductDTO.builder()
										.productId(entity.getProductId())
										.productName(entity.getProductName())
										.content(entity.getContent())
										.price(entity.getPrice())
										.viewCount(entity.getViewCount())
										.isVisible(entity.getIsVisible())
										.imagePath(entity.getImagePath())
										.image(entity.getImage())
										.eventflag(entity.getEventFlag())
										.regDate(entity.getRegDate())
										.updateDate(entity.getUpdateDate())
										.categoryId(entity.getCategoryId().getCategoryId())
										.categoryName(entity.getCategoryId().getCategoryName())
										.storeId(entity.getStoreId().getStoreId())
										.storeName(entity.getStoreId().getStoreName())
										.mno(entity.getWriter().getMno())
										.writerName(entity.getWriter().getUserName())
										.build();
		return dto;
	}
	
	default ProductEntity dtoToEntity(ProductDTO dto) {
		
		
		ProductEntity entity = ProductEntity.builder()
												.productId(dto.getProductId())
												.productName(dto.getProductName())
												.content(dto.getContent())
												.price(dto.getPrice())
												.viewCount(dto.getViewCount())
												.isVisible(dto.getIsVisible())
												.imagePath(dto.getImagePath())
												.image(dto.getImage())
												.eventFlag(dto.getEventflag())
												.categoryId(CategoryEntity.builder().categoryId(dto.getCategoryId()).build())
												.storeId(StoreEntity.builder().storeId(dto.getStoreId()).build())
												.writer(MemberEntity.builder().mno(dto.getMno()).build())
												.build();
		return entity;
												
	}
}











