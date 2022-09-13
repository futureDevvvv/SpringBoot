package net.softsociety.issho.controller.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.manager.domain.Members;
import net.softsociety.issho.manager.service.MailSenderService;
import net.softsociety.issho.manager.service.MembersService;
import net.softsociety.issho.manager.util.PageNavigator;

@Slf4j
@Controller
@RequestMapping("manager")
public class ManagerController {
	
	@Autowired
	MembersService service;
	
	@Autowired
	MailSenderService mailSenderService;
	
	//게시판 목록의 페이지당 글 수
	@Value("${user.manager.members.page}")
	int countPerPage;
	
	//게시판 목록의 페이지 이동 링크 수
	@Value("${user.manager.members.group}")
	int pagePerGroup;
	
	//첨부파일 저장할 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	
	@GetMapping("/project")
	public String project(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listMembers(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerProjects";
	}
	
	@GetMapping("/invitation")
	public String invitation(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listMembers(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerInvitation";
	}
	
	
	@GetMapping("/member")
	public String member(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listMembers(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerMember";
	}
	
	@GetMapping("/works")
	public String works(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listMembers(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerWorks";
	}
	
	@GetMapping("mailSender")
	public String mailSender() {
		return "managerView/mailSender";
	}
	
	
	@PostMapping("mailSender")
	@ResponseBody
	public String mailSender(String email,String domain) throws Exception{
		
		mailSenderService.mailSend(email, domain);
		
		log.debug(email,domain);
		
		return "전송 되었습니다.";
	}
}
