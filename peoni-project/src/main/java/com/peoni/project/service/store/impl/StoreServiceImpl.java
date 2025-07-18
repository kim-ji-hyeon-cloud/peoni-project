package com.peoni.project.service.store.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peoni.project.dto.store.StoreDTO;
import com.peoni.project.repository.store.IStoreRepository;
import com.peoni.project.service.store.IStoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class StoreServiceImpl implements IStoreService{
	
	private final IStoreRepository storeRepository;
	
	
	@Override
	public List<StoreDTO> getAll() {
		
		return storeRepository.findAll().stream()
				.map(entity -> StoreDTO.builder()
						.storeId(entity.getStoreId())
						.storeName(entity.getStoreName())
						.storeLogo(entity.getStoreLogo())
						.build())
				.collect(Collectors.toList());
	}

}
