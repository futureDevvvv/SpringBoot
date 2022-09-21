package net.softsociety.issho.notice.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.notice.domain.Comment;
import net.softsociety.issho.notice.domain.CommentDetail;
import net.softsociety.issho.notice.domain.Notice;
import net.softsociety.issho.notice.domain.NoticeDetail;
import net.softsociety.issho.notice.service.NoticeService;
import net.softsociety.issho.util.FileService;
import net.softsociety.issho.util.PageNavigator;

@Slf4j
@RequestMapping("phonebook")
@Controller
public class PhonebookController {
	
	//게시판 목록의 페이지당 글 수
	@Value("${user.manager.members.page}")
	int countPerPage;
	
	//게시판 목록의 페이지 이동 링크 수
	@Value("${user.manager.members.group}")
	int pagePerGroup;

	//게시판 첨부파일 업로드 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	@Autowired
	NoticeService noticeService;
	
	//prj_seq는 나중에 반드시 프로젝트에 공통적으로 관리하고 읽을 수 있어야 함
	String prj_domain = "scit41";
	
	@GetMapping("phonebook")
/*	
	public String phonebook(@RequestParam(name = "page", defaultValue = "1") int page,
								String type , String searchWord, Model model, 
								@AuthenticationPrincipal UserDetails user) {
*/
	public String phonebook( @AuthenticationPrincipal UserDetails user) {
			log.debug("----- 진입 GET : phonebook/phonebook");
			log.debug("-------------------- USER  : {}", user.getUsername());
//			log.debug("----- PARAM: {} | {} | {}", page, type, searchWord);
/*			
			PageNavigator navi = memService.getNoticePageNavi(pagePerGroup, countPerPage, page, type, searchWord);
			log.debug("----- PageNavigator | {}", navi);
			
			ArrayList<NoticeDetail> noticeList = memService.listNotice(navi, type, searchWord);
			log.debug("----- 검색된 noticeList : {}", noticeList);
			
			//!!!! 나중에 prj_domain는 받아와서 반드시 채워주어야 함!!!!
			model.addAttribute("prj_domain", null); 
			
			model.addAttribute("navi", navi);
			if(!noticeList.isEmpty())
				model.addAttribute("noticeList", noticeList);
			model.addAttribute("type", type);
			model.addAttribute("searchWord", searchWord);
*/			
			log.debug("----- 호출 : noticeView/phonebook");	
			return "/noticeView/phonebook";
	}
}
