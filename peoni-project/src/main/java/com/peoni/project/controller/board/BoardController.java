package com.peoni.project.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peoni.project.dto.board.BoardDTO;
import com.peoni.project.dto.comm.PageRequestDTO;
import com.peoni.project.service.board.IBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	private final IBoardService boardService;
	
	// 게시글 등록 폼
	@GetMapping("/register")
	public void register() {
	}
	
	// 게시글 등록 처리
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, RedirectAttributes rttr) {
		log.info("BoardDTO: {}", boardDTO);
		
		Long boardId = boardService.register(boardDTO);
		rttr.addFlashAttribute("msg", boardId);
		
		return "redirect:/board/list";
	}
	
	// 게시글 목록 조회
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		
		log.info("PageRequestDTO: {}", pageRequestDTO);
		
		model.addAttribute("result", boardService.getList(pageRequestDTO));
	}
	
	@GetMapping("/read")
	public void read(@RequestParam("boardId") Long boardId,
					 @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
					 Model model) {
		
		log.info("BoardId: {}", boardId);
		
		BoardDTO boardDTO = boardService.getBoard(boardId);
		model.addAttribute("dto", boardDTO);
	}
}















