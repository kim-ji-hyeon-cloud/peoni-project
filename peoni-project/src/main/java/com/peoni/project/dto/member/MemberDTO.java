package com.peoni.project.dto.member;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	private Long mno;
	private String userId;
	private String userPw;
	private String userName;
	private String email;
	private char emailVerified;
	private char termsAgreed;
	private int role;
	
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
