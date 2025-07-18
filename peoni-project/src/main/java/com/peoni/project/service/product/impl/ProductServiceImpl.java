package com.peoni.project.service.product.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.comm.ProductSearchDTO;
import com.peoni.project.dto.product.ProductDTO;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.entity.product.CategoryEntity;
import com.peoni.project.entity.product.ProductEntity;
import com.peoni.project.entity.store.StoreEntity;
import com.peoni.project.repository.member.IMemberRepository;
import com.peoni.project.repository.product.IProductRepository;
import com.peoni.project.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

	private final IProductRepository productRepository;
	private final IMemberRepository memberRepository;
	
	@Override
	public List<ProductDTO> getAllProducts() {
		
		List<ProductEntity> entities = productRepository.findAllProducts(PageRequest.of(0, 20));
		return entities.stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductDTO getProduct(Long productId) {
		// 조회수 증가
		productRepository.incrementViewCount(productId);
		
		ProductEntity entity = productRepository.findProductWithDetails(productId);
		return entityToDto(entity);
	}

	@Override
	public List<ProductDTO> getNewProducts() {
		List<ProductEntity> entities = productRepository.findNewProducts(PageRequest.of(0, 10));
		return entities.stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getHotProducts() {
		
		List<ProductEntity> entities = productRepository.findPopularProducts(PageRequest.of(0, 10));
		return entities.stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void register(ProductDTO dto) {
		
		ProductEntity entity = dtoToEntity(dto);
		productRepository.save(entity);
		
	}

	@Override
	public void update(ProductDTO dto) {
		
		ProductEntity entity = productRepository.findById(dto.getProductId())
								.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품"));
							
		entity.setProductName(dto.getProductName());
		entity.setContent(dto.getContent());
		entity.setPrice(dto.getPrice());
		entity.setIsVisible(dto.getIsVisible());
		entity.setEventFlag(dto.getEventflag());
		entity.setImagePath(dto.getImagePath());
		entity.setImage(dto.getImage());
		entity.setCategoryId(CategoryEntity.builder().categoryId(dto.getCategoryId()).build());
		entity.setStoreId(StoreEntity.builder().storeId(dto.getStoreId()).build());
		
		MemberEntity member = memberRepository.findById(dto.getMno())
				.orElseThrow(() -> new IllegalArgumentException("작성자 정보가 잘못되었습니다."));
		entity.setWriter(member);
		
		productRepository.save(entity);
	}

	@Override
	public void delete(Long productId) {

		productRepository.deleteById(productId);
		
	}

	@Override
	public Page<ProductDTO> searchProducts(ProductSearchDTO dto, Pageable pageable) {
		
		// typeArr 기본값 처리
		if ((dto.getTypeArr() == null || dto.getTypeArr().length == 0) &&
		        dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
		        dto.setTypeArr(new String[] {"P", "C"}); // 상품명, 내용 모두 포함
		}
		
		return productRepository.searchProducts(dto, pageable)
				.map(this::entityToDto);
	}

	
}
