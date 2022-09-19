package net.softsociety.issho.manager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

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
import net.softsociety.issho.manager.domain.InvitationMember;
import net.softsociety.issho.manager.service.MailSenderService;
import net.softsociety.issho.manager.service.ManagerService;
import net.softsociety.issho.manager.util.PageNavigator;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Controller
@RequestMapping("manager")
public class ManagerController {
	
	/*
	 * 매니저 서비스
	 */
	@Autowired
	ManagerService service;
	
	@Autowired
	MemberService memberService;
	
	/*
	 * 메일 전송 서비스
	 */
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
	
	
	/**
	 * 프로젝트 페이지
	 * 
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("/project")
	public String project(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(navi, searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerProjects";
	}
	
	
	/**
	 * 초대 페이지
	 * 
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("/invitation")
	public String invitation(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerInvitation";
	}
	
	/**
	 * 구성원관리 페이지
	 * 
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("/member")
	public String member(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerMember";
	}
	
	/**
	 * 멤버 탈퇴(삭제)
	 * 
	 * @param members
	 * @return
	 */
	@GetMapping("/deleteMember")
	public String deleteMember(String email) {
		
		log.debug("이메일 확인:"+email);
		Members members = new Members();
		
		members.setMemb_mail(email);
		
		int result =  memberService.deleteMember(members);
		
		return "redirect:/manager/member";
	}
	
	
	/**
	 * 업무관리 페이지
	 * 
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("/works")
	public String works(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup,countPerPage,page,searchWord);
		
		//현재 페이지 글 정보
		//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(navi,searchWord);
		log.debug("list 결과: {}",list);
		
		//리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		model.addAttribute("searchWord",searchWord);
		
		return "managerView/managerWorks";
	}
	
	
	/**
	 * 초대 메일전송폼 이동 
	 * 
	 * @return
	 */
	@GetMapping("mailSender")
	public String mailSender() {
		return "managerView/mailSender";
	}
	
	
	/**
	 * 초대 메일 전송
	 * 
	 * @param email
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	@PostMapping("mailSender2")
	public String mailSender(InvitationMember invitation) throws Exception{
		
		mailSenderService.mailSend(invitation.getMembInv_recipient(), invitation.getPrj_domain());
		log.debug("메일샌더 실행");
		/*
		log.debug("이메일"+ memb_mail); 
		log.debug("도메인"+prj_domain);
		
		invitation.setPrj_domain(prj_domain);
		invitation.setMembInv_recipient(memb_mail);
		*/
		
		service.insertAttendant(invitation);
		
		log.debug("메일초대 도메인"+ invitation.getPrj_domain());
		log.debug("메일초대인 메일"+ invitation.getMembInv_recipient());
		
		return "redirect:/manager/invitation";
	}
	
	/**
	 * 초대 이메일 중복체크
	 * 
	 * @param memb_mail
	 * @return 결과값
	 */
	@ResponseBody
	@PostMapping("/mailIdCheck")
	public int idCheck(InvitationMember invitationMember,String membInv_recipient, String prj_domain) {

		log.debug("이메일 : {}", membInv_recipient);
		log.debug("도메인 : {}", prj_domain);
		
		invitationMember.setMembInv_recipient(membInv_recipient);
		invitationMember.setPrj_domain(prj_domain);
		int result = service.invitationIdSearchOne(invitationMember);
		
		log.debug("결과 : {}", result);

		return result;
	}

	/**
	 * 구성원관리 해당멤버 클릭시 멤버 정보 폼으로 이동
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@GetMapping("ShowMemberInfo")
	public String memberInfo(String email,Model model) {
		
		log.debug("멤버정보 이메일 : " + email);
		
		Members members = new Members();
		
		members.setMemb_mail(email);
		
		members = service.getMemberInfo(email);
		
		String profileImg = "http://localhost:9990/issho/savedImg/" + members.getMemb_savedfile();
		
		model.addAttribute("members",members);
		model.addAttribute("profileImg",profileImg);
		
		log.debug("멤버 정보: " + members);
		
		
		
		return "/managerView/memberInfo";
		
		
	}
	/**
	 * 관리자 업무페이지에서 엑셀 다운로드
	 * 
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("excelDownload")
	public void excelDownload(HttpServletResponse response) throws IOException {
		Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        
     // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("이름");
        cell = row.createCell(2);
        cell.setCellValue("제목");

        // Body
        for (int i=0; i<3; i++) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(i+"_name");
            cell = row.createCell(2);
            cell.setCellValue(i+"_title");
        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
       
	
}
