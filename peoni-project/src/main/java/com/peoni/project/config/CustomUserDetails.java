package com.peoni.project.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.peoni.project.dto.member.MemberDTO;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.entity.member.MemberRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{
	
	private final MemberEntity member;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<MemberRole> roles = member.getRoleSet();
		
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		
		return member.getUserPw();
	}

	@Override
	public String getUsername() {
		
		return member.getUserId();
	}

	@Override
    public boolean isAccountNonExpired() {
		
        return true; // 필요 시 상태값을 저장하고 false 처리 가능
    }

    @Override
    public boolean isAccountNonLocked() {
    	
        return true; // 계정 잠금 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	
        return true; // 자격 증명 만료 여부
    }

    @Override
    public boolean isEnabled() {
    	
        return true; // 활성화 여부
    }

    public MemberEntity getMember() {
    	
        return member;
    }
    
    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .mno(member.getMno())
                .userId(member.getUserId())
                .userPw(member.getUserPw())
                .userName(member.getUserName())
                .email(member.getEmail())
                // 필요하면 roleSet도 추가 가능
                .build();
    }
}











