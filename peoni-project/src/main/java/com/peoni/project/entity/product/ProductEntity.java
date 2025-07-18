package com.peoni.project.entity.product;

import org.hibernate.annotations.Check;

import com.peoni.project.entity.comm.BaseEntity;
import com.peoni.project.entity.member.MemberEntity;
import com.peoni.project.entity.store.StoreEntity;

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
		name = "PRODUCT_SEQ_GEN",
		sequenceName = "product_seq",
		initialValue = 1,
		allocationSize = 1
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Check(constraints = "is_visible IN ('Y', 'N')")
@Table(name = "product")
public class ProductEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "PRODUCT_SEQ_GEN")
	private Long productId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private CategoryEntity categoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private StoreEntity storeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mno", nullable = false)
	@ToString.Exclude
	private MemberEntity writer;
	
	@Column(name = "product_name", nullable = false, length = 100)
	private String productName;
	
	@Column(name = "content", nullable = false, length = 1000)
	private String content;
	
	@Column(name = "image_path", length = 200)
	private String imagePath;
	
	@Column(name = "price", nullable = false)
	private Long price;
	
	@Column(name = "view_count", nullable = false, columnDefinition = "NUMBER DEFAULT 0")
	private int viewCount = 0;
	
	@Column(name = "is_visible", nullable = false ,length = 1, columnDefinition = "CHAR(1) DEFAULT 'Y'")
	private char isVisible = 'Y';
	
	@Column(name = "event_flag", length = 30)
	private String eventFlag;
	
	@Column(name = "image", length = 225)
	private String image;
}










