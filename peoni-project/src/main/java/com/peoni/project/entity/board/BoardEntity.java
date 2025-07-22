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
import lombok.ToString;

@Entity
@SequenceGenerator(
		name = "BOARD_SEQ_GEN",
		sequenceName = "board_seq",
		initialValue = 1,
		allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "board")
public class BoardEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_SEQ_GEN")
	private Long boardId;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "content", nullable = false, length = 2000)
	private String content;
	
	@Column(name = "board_type", nullable = false)
	private String boardType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mno", nullable = false)
	private MemberEntity writer;
	
	public void changeTitle(String title) {
	    this.title = title;
	}

	public void changeContent(String content) {
	    this.content = content;
	}

	public void changeBoardType(String boardType) {
	    this.boardType = boardType;
	}
}















