package com.peoni.project.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.repository.member.IMemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{

	private final IMemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberEntity member = memberRepository.findByUserId(username)
				.orElseThrow(() -> new UsernameNotFoundException("해당 ID의 회원을 찾을 수 없습니다: " + username));
		
		return new CustomUserDetails(member);
	}

}






