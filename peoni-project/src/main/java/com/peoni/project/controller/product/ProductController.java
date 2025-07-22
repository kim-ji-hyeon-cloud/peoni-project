package com.peoni.project.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peoni.project.dto.comm.ProductSearchDTO;
import com.peoni.project.dto.member.MemberDTO;
import com.peoni.project.dto.product.CategoryDTO;
import com.peoni.project.dto.product.ProductDTO;
import com.peoni.project.dto.store.StoreDTO;
import com.peoni.project.service.product.ICategoryService;
import com.peoni.project.service.product.IProductService;
import com.peoni.project.service.store.IStoreService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

	private final IProductService productService;
	private final ICategoryService categoryService;
	private final IStoreService storeService;
	
	@Value("${com.spring.upload.path}")
	private String uploadPath;
	
	// 상품 목록
	@GetMapping("/list")
	public String listAll(@ModelAttribute ProductSearchDTO searchDTO, 
						  @RequestParam(name = "page", defaultValue = "0") int page, 
						  Model model) {
		
		log.info("상품 목록 조회: {}", searchDTO);
		
		Pageable pageable = PageRequest.of(page, 12);
		Page<ProductDTO> result = productService.searchProducts(searchDTO, pageable);
		
		model.addAttribute("list", result.getContent());
		model.addAttribute("pageMaker", result); // 페이징용
		model.addAttribute("param", searchDTO); // 검색조건 유지용
		
		return "product/list";
	}
	
	// 등록 페이지 이동
	@GetMapping("/register")
	public String registerForm(Model model) {
		
		List<CategoryDTO> categoryList = categoryService.getAll();
		List<StoreDTO> storeList = storeService.getAll();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("storeList", storeList);
		
		return "product/register";
	}
	
	// 상품 등록
	@PostMapping("/register")
	public String register(@ModelAttribute ProductDTO dto,
						   @RequestParam("imageFile") MultipartFile imageFile,
						   HttpSession session,
						   RedirectAttributes rttr) throws IOException {
		
		// 로그인 세션 체크
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		if (member == null) {
			return "redirect:/member/login";
		}
		dto.setMno(member.getMno());
		
		// 이미지 저장
		if (!imageFile.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid + "_" + imageFile.getOriginalFilename();
			File dest = new File(uploadPath, fileName);
			imageFile.transferTo(dest);
			dto.setImage(fileName);
		}
		
		productService.register(dto);
		rttr.addFlashAttribute("result", "등록 완료");
		return "redirect:/product/list";
	}
	
	// 상세조회
	@GetMapping("/read")
	public String read(@RequestParam("productId") Long productId, Model model, HttpSession session) {
		
		ProductDTO dto = productService.getProduct(productId);
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		model.addAttribute("product", dto);
		model.addAttribute("login", login);
		return "product/read";
	}
	
	// 수정 페이지 이동
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("productId") Long productId, Model model, HttpSession session) {
		
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		if (member == null) return "redirect:/member/login";
		
		ProductDTO dto = productService.getProduct(productId);
		
		List<CategoryDTO> categoryList = categoryService.getAll();
		List<StoreDTO> storeList = storeService.getAll();
		
		model.addAttribute("product", dto);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("storeList", storeList);
		
		return "product/modify";
	}
	
	// 상품 수정
	@PostMapping("/modify")
	public String modify(@ModelAttribute ProductDTO dto, 
						 @RequestParam("imageFile") MultipartFile imageFile, 
						 RedirectAttributes rttr) throws IOException {
		if (!imageFile.isEmpty()) {
			// 새 이미지 업로드
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid + "_" + imageFile.getOriginalFilename();
			File dest = new File(uploadPath, fileName);
			imageFile.transferTo(dest);
			dto.setImage(fileName);
		} else {
			// 새 이미지가 없으면 기존 이미지 유지
			ProductDTO old = productService.getProduct(dto.getProductId());
			dto.setImage(old.getImage());
		}
		
		productService.update(dto);
		rttr.addFlashAttribute("result", "수정 완료");
		
		return "redirect:/product/read?productId=" + dto.getProductId();
	}
	
	// 상품 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("productId") Long productId, HttpSession session, RedirectAttributes rttr) {
		
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		if (member == null) return "redirect:/member/login";
		
		productService.delete(productId);
		rttr.addFlashAttribute("result", "삭제 완료");
		return "redirect:/product/list";
	}
}













