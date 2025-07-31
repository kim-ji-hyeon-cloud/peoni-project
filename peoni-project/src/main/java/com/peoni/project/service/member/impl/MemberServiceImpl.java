package com.peoni.project.service.member.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoni.project.dto.member.MemberDTO;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.entity.member.MemberRole;
import com.peoni.project.repository.member.IMemberRepository;
import com.peoni.project.service.member.IMemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

	private final IMemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public Long register(MemberDTO memberDTO) {
		MemberEntity entity = dtoToEntity(memberDTO);
		entity.setUserPw(passwordEncoder.encode(entity.getUserPw()));
		entity.addMemberRole(MemberRole.USER);
		return memberRepository.save(entity).getMno();
	}

	@Override
	@Transactional
	public MemberDTO read(String userId) {
		return memberRepository.findByUserId(userId)
									.map(this::EntityToDto)
									.orElse(null);
	}

	@Override
	@Transactional
	public MemberDTO login(String userId, String userPw) {
		Optional<MemberEntity> result = memberRepository.findByUserId(userId);
		if (result.isPresent()) {
			MemberEntity entity = result.get();
			return EntityToDto(entity);
		}
		return null;
	}

	@Override
	@Transactional
	public boolean modify(MemberDTO memberDTO) {
		Optional<MemberEntity> result = memberRepository.findById(memberDTO.getMno());
		if (result.isPresent()) {
			MemberEntity entity = result.get();
			entity.setUserPw(memberDTO.getUserPw());
			entity.setUserName(memberDTO.getUserName());
			entity.setEmail(memberDTO.getEmail());
			memberRepository.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Long mno) {
		if (memberRepository.existsById(mno)) {
			memberRepository.deleteById(mno);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserId(String userId) {
		return memberRepository.existsByUserId(userId);
	}

	@Override
	public String findUserId(String userName, String email) {
		return memberRepository.findByUserNameAndEmail(userName, email)
									.map(MemberEntity::getUserId)
									.orElse(null);
	}

	@Override
	public boolean verifyUserEmail(String userId, String email) {
		return memberRepository.findByUserIdAndEmail(userId, email).isPresent();
	}

}














