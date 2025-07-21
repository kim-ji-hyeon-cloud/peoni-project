package com.peoni.project.entity.board;

import com.peoni.project.entity.comm.BaseEntity;

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
		name = "BOARD_IMAGE_SEQ_GEN",
		sequenceName = "board_image-seq",
		initialValue = 1,
		allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
@Table(name = "board_image")
public class BoardImageEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_IMAGE_SEQ_GEN")
	private Long imageId;
	
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "image_name", nullable = false)
	private String imageName;
	
	@Column(name = "path", nullable = false)
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board;
}













