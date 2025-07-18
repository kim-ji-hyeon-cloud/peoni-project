package com.peoni.project.repository.product.impl;

import com.peoni.project.dto.comm.ProductSearchDTO;
import com.peoni.project.entity.product.ProductEntity;
import com.peoni.project.entity.product.QProductEntity;
import com.peoni.project.repository.product.ProductSearchRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductEntity> searchProducts(ProductSearchDTO dto, Pageable pageable) {
        QProductEntity product = QProductEntity.productEntity;

        BooleanBuilder builder = new BooleanBuilder();

        // 검색 키워드
        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty() && dto.getTypeArr() != null) {
            BooleanBuilder keywordBuilder = new BooleanBuilder();
            for (String type : dto.getTypeArr()) {
                if (type.equals("P")) {
                    keywordBuilder.or(product.productName.containsIgnoreCase(dto.getKeyword()));
                } else if (type.equals("C")) {
                    keywordBuilder.or(product.content.containsIgnoreCase(dto.getKeyword()));
                }
            }
            builder.and(keywordBuilder);
        }

        // 카테고리 필터
        if (dto.getCategoryId() != null) {
            builder.and(product.categoryId.categoryId.eq(dto.getCategoryId()));
        }

        // 편의점 필터
        if (dto.getStoreId() != null) {
            builder.and(product.storeId.storeId.eq(dto.getStoreId()));
        }

        // 페이징 + 정렬
        List<ProductEntity> content = queryFactory
                .selectFrom(product)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.productId.desc())
                .fetch();

        long total = queryFactory
                .selectFrom(product)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
}
