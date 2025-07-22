package com.peoni.project.controller.board;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peoni.project.dto.board.BoardDTO;
import com.peoni.project.dto.board.BoardImageDTO;
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
	
	@Value("${com.spring.upload.path}")
	private String uploadPath;
	
	// 게시글 등록 폼
	@GetMapping("/register")
	public void register() {
	}
	
	// 게시글 등록 처리
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, 
						   @RequestParam("files") MultipartFile[] files, 
						   RedirectAttributes rttr) {
		
		log.info("BoardDTO: {}", boardDTO);
		
		List<BoardImageDTO> imageList = new ArrayList<>();
		
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					String uuid = UUID.randomUUID().toString();
					String fileName = uuid + "_" + file.getOriginalFilename();
					String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
					
					File saveFolder = new File(uploadPath + File.separator + "board", todayPath);
					if (!saveFolder.exists()) {
						saveFolder.mkdirs();
					}
					
					File saveFile = new File(saveFolder, fileName);
					try {
						file.transferTo(saveFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					imageList.add(BoardImageDTO.builder()
							.uuid(uuid)
							.imageName(file.getOriginalFilename())
							.path(todayPath)
							.build());
				}
			}
		}
		
		boardDTO.setImageDtoList(imageList);
		
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
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("boardId") Long boardId, Model model) {
		
		BoardDTO boardDTO = boardService.getBoard(boardId);
		model.addAttribute("dto", boardDTO);
		
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO boardDTO, 
	                     @RequestParam("files") MultipartFile[] files, 
	                     RedirectAttributes rttr) {
	    
	    List<BoardImageDTO> imageList = new ArrayList<>();

	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                String uuid = UUID.randomUUID().toString();
	                String fileName = uuid + "_" + file.getOriginalFilename();
	                String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

	                File saveFolder = new File(uploadPath + File.separator + "board", todayPath);
	                if (!saveFolder.exists()) {
	                    saveFolder.mkdirs();
	                }

	                File saveFile = new File(saveFolder, fileName);
	                try {
	                    file.transferTo(saveFile);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }

	                imageList.add(BoardImageDTO.builder()
	                        .uuid(uuid)
	                        .imageName(file.getOriginalFilename())
	                        .path(todayPath)
	                        .build());
	            }
	        }
	    }

	    boardDTO.setImageDtoList(imageList);

	    boardService.modify(boardDTO);
	    rttr.addAttribute("boardId", boardDTO.getBoardId());

	    return "redirect:/board/read";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("boardId") Long boardId, RedirectAttributes rttr) {
		
		boardService.remove(boardId);
		rttr.addFlashAttribute("msg", "게시글이 삭제되었습니다");
		
		return "redirect:/board/list";
	}
}















