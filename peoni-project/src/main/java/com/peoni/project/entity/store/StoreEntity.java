package com.peoni.project.entity.store;

import com.peoni.project.entity.product.CategoryEntity;

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
		name = "STORE_SEQ_GEN",
		sequenceName = "store_seq",
		initialValue = 1,
		allocationSize = 1
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "store")
public class StoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "STORE_SEQ_GEN")
	private Long storeId;
	
	@Column(name = "store_name", nullable = false, length = 50)
	private String storeName;
	
	@Column(name = "store_loge", length = 200)
	private String storeLogo;
}







