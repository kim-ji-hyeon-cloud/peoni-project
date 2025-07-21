package com.peoni.project.entity.board;

import com.peoni.project.entity.comm.BaseEntity;
import com.peoni.project.entity.member.MemberEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
		name = "BOARD_REVIEW_SEQ_GEN",
		sequenceName = "board_review_seq",
		initialValue = 1,
		allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"board, writer"})
@Table(name = "board_review")
public class BoardReviewEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_REVIEW_SEQ_GEN")
	private Long reviewId;
	
	@Column(name = "content", nullable = false, length = 1000)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mno", nullable = false)
	private MemberEntity writer;
}













