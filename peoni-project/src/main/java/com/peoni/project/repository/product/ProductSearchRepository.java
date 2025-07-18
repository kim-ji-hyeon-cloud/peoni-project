package com.peoni.project.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.peoni.project.dto.comm.ProductSearchDTO;
import com.peoni.project.entity.product.ProductEntity;

public interface ProductSearchRepository {

	Page<ProductEntity> searchProducts(ProductSearchDTO dto, Pageable pageable);
	
}
