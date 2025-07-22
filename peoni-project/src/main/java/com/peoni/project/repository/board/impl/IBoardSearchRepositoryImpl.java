package com.peoni.project.repository.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.comm.PageRequestDTO;
import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardImageEntity;
import com.peoni.project.entity.board.QBoardEntity;
import com.peoni.project.entity.board.QBoardImageEntity;
import com.peoni.project.entity.board.QBoardReviewEntity;
import com.peoni.project.entity.member.QMemberEntity;
import com.peoni.project.repository.board.IBoardSearchRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@Transactional(readOnly = true)
public class IBoardSearchRepositoryImpl implements IBoardSearchRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Page<Object[]> searchPageWithFilter(PageRequestDTO requestDTO, Pageable pageable) {

        QBoardEntity board = QBoardEntity.boardEntity;
        QBoardImageEntity image = QBoardImageEntity.boardImageEntity;
        QBoardReviewEntity review = QBoardReviewEntity.boardReviewEntity;
        QMemberEntity member = QMemberEntity.memberEntity;

        JPQLQuery<BoardEntity> query = queryFactory.selectFrom(board);
        query.leftJoin(board.writer, member);
        query.leftJoin(image).on(image.board.eq(board),
                image.imageId.eq(
                    JPAExpressions.select(image.imageId.max())
                                  .from(image)
                                  .where(image.board.eq(board))
                ));
        query.leftJoin(review).on(review.board.eq(board));

        query.groupBy(board, image);

        // 🔸 조건 처리
        BooleanBuilder builder = new BooleanBuilder();

        if (requestDTO.getBoardType() != null && !requestDTO.getBoardType().isEmpty()) {
            builder.and(board.boardType.eq(requestDTO.getBoardType()));
        }

        if (requestDTO.getKeyword() != null && !requestDTO.getKeyword().isEmpty()) {
            String type = requestDTO.getType();  // 예: "T", "C", "TC"
            if (type != null && !type.isBlank()) {
                BooleanBuilder keywordBuilder = new BooleanBuilder();

                if (type.contains("T")) {
                    keywordBuilder.or(board.title.contains(requestDTO.getKeyword()));
                }
                if (type.contains("C")) {
                    keywordBuilder.or(board.content.contains(requestDTO.getKeyword()));
                }

                builder.and(keywordBuilder);
            }
        }

        query.where(builder);
        query.orderBy(board.boardId.desc());
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        JPQLQuery<Tuple> resultQuery = query.select(board, image, review.count());

        List<Tuple> resultList = resultQuery.fetch();

        List<Object[]> mappedResult = resultList.stream()
                .map(tuple -> new Object[]{
                        tuple.get(0, BoardEntity.class),
                        tuple.get(1, BoardImageEntity.class),
                        tuple.get(2, Long.class)
                })
                .toList();

        long total = resultQuery.fetchCount();

        return new PageImpl<>(mappedResult, pageable, total);
    }
}