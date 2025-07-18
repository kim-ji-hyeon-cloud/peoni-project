package com.peoni.project.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peoni.project.entity.store.StoreEntity;

public interface IStoreRepository extends JpaRepository<StoreEntity, Long>{

	
}
