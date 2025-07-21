package com.peoni.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.repository.member.IMemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {
	
	@Autowired
	private IMemberRepository memberRepository;
	
//	@Test
//	void testInsertMembers() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			MemberEntity member = MemberEntity.builder()
//											.userId("user" + i)
//											.userPw("1111")
//											.userName("reviewer" + i)
//											.email("user" + i + "@naver.com")
//											.build();
//			memberRepository.save(member);
//		});
//	}
	
//	@Test
//	void testInsertMember() {
//		MemberEntity member = MemberEntity.builder()
//				.userId("user1")
//				.userPw("1234")
//				.userName("홍길동")
//				.email("user1@naver.com")
//				.build();
//		
//		memberRepository.save(member);
//	}

	
//	@Test
//	void findByUserIdTest() {
//		Optional<MemberEntity> result = memberRepository.findByUserId("user3");
//	    
//		if (result.isPresent()) {
//			MemberEntity member = result.get();
//			log.info("조회된 회원 : {}", member);
//		} else {
//			log.info("존재하지 않는 회원입니다");
//		}
//	}
	
//	@Test
//	void testFindByUserNameAndEmail() {
//		Optional<MemberEntity> result = memberRepository.findByUserNameAndEmail("홍길동", "user3@naver.com");
//		
//		if (result.isPresent()) {
//			MemberEntity member = result.get();
//			log.info("아이디 : " + member.getUserId());
//		} else {
//			log.info("해당 회원이 존재하지 않습니다");
//		}
//	}
	
//	@Test
//	void testFindByUserPw() {
//		Optional<MemberEntity> result = memberRepository.findByUserIdAndEmail("user3", "user3@naver.com");
//		
//		if (result.isPresent()) {
//			MemberEntity member = result.get();
//			log.info("비밀번호 : " + member.getUserPw());
//		} else {
//			log.info("해당 회원이 존재하지 않습니다");
//		}
//	}
}













