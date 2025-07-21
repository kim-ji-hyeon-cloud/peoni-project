package com.peoni.project.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.entity.board.BoardEntity;
import com.peoni.project.entity.board.BoardImageEntity;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.repository.board.IBoardImageRepository;
import com.peoni.project.repository.board.IBoardRepository;
import com.peoni.project.repository.member.IMemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class BoardRepositoryTests {

	@Autowired
    private IBoardRepository boardRepository;

    @Autowired
    private IBoardImageRepository boardImageRepository;
    
    @Autowired
    private IMemberRepository memberRepository;

//    @Commit
//    @Transactional
//    @Test
//    void testInsertMovies() {
//
//        List<MemberEntity> memberList = memberRepository.findAll(); // 미리 전체 회원 로딩
//        if (memberList.isEmpty()) {
//            throw new RuntimeException("회원 데이터가 없습니다. 먼저 testInsertMembers()를 실행하세요.");
//        }
//
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            MemberEntity randomWriter = memberList.get((int)(Math.random() * memberList.size())); // 랜덤 작성자
//
//            BoardEntity board = BoardEntity.builder()
//                    .title("Board..." + i)
//                    .content("게시글 내용입니다 " + i)
//                    .boardType("NOTICE")
//                    .writer(randomWriter)  // ✅ 실제 존재하는 회원을 작성자로 설정
//                    .build();
//
//            boardRepository.save(board);
//
//            int count = (int)(Math.random() * 5) + 1;
//
//            for (int j = 0; j < count; j++) {
//                BoardImageEntity boardImage = BoardImageEntity.builder()
//                        .uuid(UUID.randomUUID().toString())
//                        .board(board)
//                        .imageName("test" + j + ".jpg")
//                        .path("2025/07/21")
//                        .build();
//
//                boardImageRepository.save(boardImage);
//            }
//        });
//    }
    
    
    
    
    //게시글 + 이미지 저장 테스트
//    @Commit
//    @Transactional
//    @Test
//    public void testInsertBoards() {
//        IntStream.rangeClosed(1, 20).forEach(i -> {
//            BoardEntity board = BoardEntity.builder()
//                    .title("게시글 제목 " + i)
//                    .content("게시글 내용입니다 " + i)
//                    .boardType("NOTICE")
//                    .writer(MemberEntity.builder().mno(1L).build())  // 테스트용 회원 번호
//                    .build();
//
//            boardRepository.save(board);
//
//            int count = (int) (Math.random() * 3) + 1;
//
//            for (int j = 0; j < count; j++) {
//                BoardImageEntity image = BoardImageEntity.builder()
//                        .uuid(UUID.randomUUID().toString())
//                        .imageName("sample" + j + ".jpg")
//                        .path("2025/07/21")
//                        .board(board)
//                        .build();
//
//                boardImageRepository.save(image);
//            }
//        });
//    }
    
    // 게시글 목록 페이징 테스트
//    @Test
//    @Transactional
//    public void testGetListPage() {
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("boardId").descending());
//
//        Page<Object[]> result = boardRepository.getListPage(pageRequest);
//
//        for (Object[] arr : result.getContent()) {
//            log.info("게시글 = {}", arr[0]);
//            log.info("대표 이미지 = {}", arr[1]);
//            log.info("댓글 수 = {}", arr[2]);
//        }
//    }

 // 특정 게시글 상세 조회 테스트
    @Test
    @Transactional
    public void testGetBoardWithAll() {
        Long targetBoardId = 50L; // 존재하는 게시글 ID

        List<Object[]> result = boardRepository.getBoardWithAll(targetBoardId);

        for (Object[] arr : result) {
            log.info("게시글 = {}", arr[0]);
            log.info("이미지 = {}", arr[1]);
            log.info("댓글 수 = {}", arr[2]);
        }
    }
}
