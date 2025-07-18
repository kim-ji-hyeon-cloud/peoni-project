package com.peoni.project.entity.member;

import com.peoni.project.entity.comm.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(
		name = "MEMBER_SEQ_GEN",
		sequenceName = "member_seq",
		initialValue = 1,
		allocationSize = 1
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "member")
public class MemberEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "MEMBER_SEQ_GEN")
	private Long mno;
	
	@Column(name = "user_id", nullable = false, unique = true, length = 30)
	private String userId;
	
	@Column(name = "user_pw", nullable = false, length = 100)
	private String userPw;
	
	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;
	
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	
	@Builder.Default
	@Column(name = "email_verified", nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'N'")
	private char emailVerified = 'N';
	
	@Builder.Default
	@Column(name = "terms_agreed", nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'N'")
	private char termsAgreed = 'N';
	
	@Column(name = "role", nullable = false, columnDefinition = "NUMBER(4) DEFAULT 0")
	private int role = 0;
}


















