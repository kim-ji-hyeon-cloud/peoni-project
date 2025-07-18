package com.peoni.project.service.product.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peoni.project.dto.product.CategoryDTO;
import com.peoni.project.repository.product.ICategoryRepository;
import com.peoni.project.service.product.ICategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements ICategoryService{
	
	private final ICategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDTO> getAll() {
		return categoryRepository.findAll().stream()
				.map(entity -> CategoryDTO.builder()
						.categoryId(entity.getCategoryId())
						.categoryName(entity.getCategoryName())
						.build())
				.collect(Collectors.toList());
	}
	
}













