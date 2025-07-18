package com.peoni.project.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peoni.project.entity.member.MemberEntity;

public interface IMemberRepository extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findByUserId(String userId);
	
	boolean existsByUserId(String userId);
	
	Optional<MemberEntity> findByUserNameAndEmail(String userName, String email);
	
	Optional<MemberEntity> findByUserIdAndEmail(String userId, String email);
}
