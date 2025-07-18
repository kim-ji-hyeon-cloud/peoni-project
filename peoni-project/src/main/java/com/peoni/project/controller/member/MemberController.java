package com.peoni.project.controller.member;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peoni.project.dto.member.MemberDTO;
import com.peoni.project.service.member.IMemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

	private final IMemberService memberService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
		return "member/login";
	}
	
	@PostMapping("/loginPost")
	public String login(@ModelAttribute MemberDTO mDto, HttpSession session, Model model) {
		log.info("로그인 요청 {}", mDto);
		
		MemberDTO loginMember = memberService.login(mDto.getUserId(), mDto.getUserPw());
		
		if (loginMember == null) {
			return "redirect:/member/login";
		}
		
		session.setAttribute("login", loginMember);
		model.addAttribute("memInfo", loginMember);
		return "redirect:/main";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "member/register";
	}
	
	@PostMapping(value = "/register", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<String> ajaxRegister(@RequestBody MemberDTO memberDTO) {
		try {
			memberService.register(memberDTO);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			log.error("회원가입 실패", e);
			return ResponseEntity.internalServerError().body("fail");
		}
	}
	
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestBody Map<String, String> data) {
		String userId = data.get("userid");
		boolean exists = memberService.checkUserId(userId);
		return exists ? "duplicate" : "available";
	}
	
	@PostMapping("/findIdPost")
	@ResponseBody
	public String findId(@RequestBody Map<String, String> data) {
		String userName = data.get("username");
		String email = data.get("email");
		
		String userId = memberService.findUserId(userName, email);
		return (userId != null) ? userId : "fail";
	}
	
	@PostMapping("/findPwPost")
	@ResponseBody
	public String findPw(@RequestBody Map<String, String> data) {
		String userId = data.get("userid");
		String email = data.get("email");
		
		boolean verified = memberService.verifyUserEmail(userId, email);
		return verified ? "verified" : "fail";
	}
} 














