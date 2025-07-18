package com.peoni.project.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peoni.project.entity.product.CategoryEntity;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long>{

}
