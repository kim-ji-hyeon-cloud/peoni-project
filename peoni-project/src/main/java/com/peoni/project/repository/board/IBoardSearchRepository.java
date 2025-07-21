package com.peoni.project.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.peoni.project.dto.comm.PageRequestDTO;

public interface IBoardSearchRepository {
	
	Page<Object[]> searchPageWithFilter(PageRequestDTO requestDTO, Pageable pageable);
}
