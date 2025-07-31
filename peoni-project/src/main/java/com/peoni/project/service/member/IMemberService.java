package com.peoni.project.service.member;

import com.peoni.project.dto.member.MemberDTO;
import com.peoni.project.entity.member.MemberEntity;

public interface IMemberService {

	Long register(MemberDTO memberDTO);
	
	MemberDTO read(String userId);
	
	MemberDTO login(String userId, String userPw);
	
	boolean modify(MemberDTO memberDTO);
	
	boolean remove(Long mno);
	
	boolean checkUserId(String userId); // 아이디 중복 체크
	
	String findUserId(String userName, String email);
	
	boolean verifyUserEmail(String userId, String email);
	
	default MemberEntity dtoToEntity(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
											.mno(dto.getMno())
											.userId(dto.getUserId())
											.userPw(dto.getUserPw())
											.userName(dto.getUserName())
											.email(dto.getEmail())
											.emailVerified(dto.getEmailVerified())
											.termsAgreed(dto.getTermsAgreed())
											.build();
		return entity;
	}
	
	default MemberDTO EntityToDto(MemberEntity entity) {
		MemberDTO dto = MemberDTO.builder()
									.mno(entity.getMno())
									.userId(entity.getUserId())
									.userPw(entity.getUserPw())
									.userName(entity.getUserName())
									.email(entity.getEmail())
									.emailVerified(entity.getEmailVerified())
									.termsAgreed(entity.getTermsAgreed())
									.regDate(entity.getRegDate())
									.updateDate(entity.getUpdateDate())
									.build();
		return dto;
	}
}














