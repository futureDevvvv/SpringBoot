package net.softsociety.issho.manager.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.manager.domain.DriveFile;
import net.softsociety.issho.manager.domain.InvitationMember;
import net.softsociety.issho.manager.domain.MemberTemp;
import net.softsociety.issho.manager.domain.TaskCnt;
import net.softsociety.issho.manager.domain.TaskCntDone;
import net.softsociety.issho.manager.domain.TaskState;
import net.softsociety.issho.manager.domain.WorkEmail;
import net.softsociety.issho.manager.service.MailSenderService;
import net.softsociety.issho.manager.service.ManagerService;
import net.softsociety.issho.member.dao.MemberDAO;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.project.domain.Projects;
import net.softsociety.issho.project.service.ProjectService;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.service.TaskService;
import net.softsociety.issho.util.FileService;
import net.softsociety.issho.util.PageNavigator;

@Slf4j
@Controller
@RequestMapping("**/manager")
public class ManagerController {

	/*
	 * 매니저 서비스
	 */
	@Autowired
	ManagerService service;

	@Autowired
	MemberService memberService;
	
	@Autowired
	ProjectService pjService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	MemberDAO memDao;

	/*
	 * 메일 전송 서비스
	 */
	@Autowired
	MailSenderService mailSenderService;

	// 게시판 목록의 페이지당 글 수
	@Value("${user.manager.members.page}")
	int countPerPage;

	// 게시판 목록의 페이지 이동 링크 수
	@Value("${user.manager.members.group}")
	int pagePerGroup;

	// 첨부파일 저장할 경로
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
	public String project(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") int page, String searchWord) {
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		// 페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, searchWord);

		// 현재 페이지 글 정보
		// DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Projects> list = pjService.listProjects(navi, searchWord);
		log.debug("list 결과: {}", list);
		
		ArrayList<Task> taskList = taskService.SelectAlltaskMG(prj_domain, navi, searchWord);
		
		for(int i = 0; i < taskList.size(); i++) {
			if(taskList.get(i).getTask_rank().equals("0")) {
				taskList.get(i).setTask_rank("낮음");
			} else if(taskList.get(i).getTask_rank().equals("1")) {
				taskList.get(i).setTask_rank("보통");
			} else {
				taskList.get(i).setTask_rank("높음");
			}
		}
		
		for(int i = 0; i < taskList.size(); i++) {
			if(taskList.get(i).getTask_state().equals("0")) {
				taskList.get(i).setTask_state("진행전");
			} else if(taskList.get(i).getTask_state().equals("1")) {
				taskList.get(i).setTask_state("진행중");
			} else if(taskList.get(i).getTask_state().equals("2")) {
				taskList.get(i).setTask_state("완료");
			} else {
				taskList.get(i).setTask_state("보류");
			}
		}
		
		log.debug("taskList 결과: {}", taskList);
		model.addAttribute("taskLists",taskList);
		
		
		// 리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi", navi);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		
		
		
		return "managerView/managerProjects";
	}

	
	@ResponseBody
	@PostMapping("/ProjectInfo")
	public Map<String, Object> ProjectInfo( String domain, Model model) {
		
		
		
		log.debug("프로젝트 상세 도메인 : " + domain);

		Projects projects = new Projects();

		projects.setPrj_domain(domain);
		
		projects = pjService.getProjectsInfo(domain);
		
		List<Task> taskList = taskService.SelectAlltask1(domain);
		
		log.debug("태스크 상세 정보 : {}", taskList);
		
	
		
		model.addAttribute("taskListInfo",taskList);
		model.addAttribute("projects",projects);
		log.debug("프로젝트 정보: " + projects);
		log.debug("태스크 정보 수정 후 : {}", taskList);
		

		Map<String, Object> result = new HashMap<>();
		result.put("projects", projects);
		result.put("taskList", taskList);
		
		

		return result;

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
	public String invitation(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			String searchWord
			,String domain) {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		// 페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, searchWord);

		// 현재 페이지 글 정보
		// DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listInvitation(prj_domain,navi, searchWord);
		log.debug("list 결과: {}", list);
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getMembInv_accept() == null) {
				list.get(i).setMembInv_accept("초대중");
			} else {
				list.get(i).setMembInv_accept("초대완료");
			}
		}

		// 리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi", navi);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);

		return "managerView/managerInvitation";
	}

	/**
	 * 구성원관리 페이지
	 * 
	 * @param user
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("/member")
	public String member(HttpServletRequest request,@AuthenticationPrincipal UserDetails user,
			Model model, @RequestParam(name = "page", defaultValue = "1") int page, String searchWord,
			String domain) {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		String id = user.getUsername();
		Members member = memDao.getUserById(id);
		ArrayList<Members> pjMemList = memberService.searchPjMem(prj_domain);
		// 페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, searchWord);

		// 현재 페이지 글 정보
		// DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(prj_domain, navi, searchWord);
		log.debug("list 결과: {}", list);
		
		// 리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi", navi);
		model.addAttribute("member", member);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		

		return "managerView/managerMember";
	}
	
	/**
	 * 구성원관리 해당멤버 클릭시 멤버 정보 폼으로 이동
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@ResponseBody
	@PostMapping("ShowMemberInfo")
	public Map<String, Object> memberInfo(HttpServletRequest request,String domain,String memEmail, Model model) {
		
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		Members members = memDao.getUserById(memEmail);
		
		log.debug("멤버정보 이메일 : " + memEmail); 

		/* members.setMemb_mail(memEmail); */
		
		log.debug("멤버정보 이메일 2 : " + memEmail); 

		
		log.debug("멤버 정보: " + members);
		
		
		Members members2 = service.listManager(prj_domain,memEmail);
		log.debug("멤버상세 리스트 결과: {}", members2);
		
		String profileImg = 
				"http://localhost:9990/issho/savedImg/" + members.getMemb_savedfile();
		
		log.debug("profileImg :"+profileImg);
		
		model.addAttribute("members", members);
		model.addAttribute("profileImg", profileImg);

		Map<String, Object> result = new HashMap<>();
		result.put("members", members);
		result.put("members2", members2);
		result.put("profileImg", profileImg);
		

		return result;

	}

	/**
	 * 멤버 탈퇴(삭제)
	 * 
	 * @param members
	 * @return
	 */
	@GetMapping("/deleteMember")
	public String deleteMember(String email) {

		log.debug("이메일 확인:" + email);
		Members members = new Members();

		members.setMemb_mail(email);

		int result = memberService.deleteMember(members);

		return "redirect:./manager/member";
	}
	
	/**
	 * 권한변경
	 * @param email
	 * @return
	 */
	@GetMapping("/editMembRight")
	public String editMembRight(String email) {
		
		log.debug("권한변경 이메일 확인:" + email);
		Members members = new Members();
		
		members.setMemb_mail(email);
		
		int result = service.editMembRight(members);
		
		return "redirect:./member";
	}
	@GetMapping("/editPMRight")
	public String editPMRight(String email) {
		
		log.debug("권한변경 이메일 확인:" + email);
		Members members = new Members();
		
		members.setMemb_mail(email);
		
		int result = service.editPMRight(members);
		
		return "redirect:./member";
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
	public String works(HttpServletRequest request,String domain,Model model, @RequestParam(name = "page", defaultValue = "1") int page, String searchWord) {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		// 페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, searchWord);

		// 현재 페이지 글 정보
		// DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(prj_domain,navi, searchWord);
		
		ArrayList<MemberTemp> workList = service.listWork(prj_domain,navi, searchWord);
		
		
		log.debug("업무 worklist 결과: {}", workList);
		
		
		
		// 리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi", navi);
		model.addAttribute("workList", workList);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);

		return "managerView/managerWorks";
	}
	
	@ResponseBody
	@PostMapping("/workInfo")
	public Map<String, Object> workInfo(String domain,String memEmail, Model model) {

		
		Members members = memDao.getUserById(memEmail);
		
		log.debug("멤버정보 이메일 : " + memEmail); 

		log.debug("멤버 정보: " + members);
		
		TaskCnt taskCnt = service.taskCnt(memEmail);
		TaskCntDone taskCntDone = service.taskCntDone(memEmail);
		TaskState taskState = service.taskState(memEmail);
		
		log.debug("태스크 할당량 : " + taskCnt);
		log.debug("태스크 달성량 : " + taskCntDone);
		log.debug("태스크 달성량 : " + taskState);
		
		
		model.addAttribute("members", members);
		model.addAttribute("taskCnt", taskCnt);
		model.addAttribute("taskCntDone", taskCntDone);
		model.addAttribute("taskState", taskState);

		Map<String, Object> result = new HashMap<>();
		result.put("members", members);
		result.put("taskCnt", taskCnt);
		result.put("taskCntDone", taskCntDone);
		result.put("taskState", taskState);
		

		return result;

	}

	/**
	 * 초대 메일전송폼 이동
	 * 
	 * @return
	 */
	@GetMapping("/mailSender")
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
	@PostMapping("/mailSender2")
	public String mailSender(HttpServletRequest request,InvitationMember invitation) throws Exception {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
	     
	     log.debug("메일 초대 도메인 ",prj_domain);
	     
		mailSenderService.mailSend(invitation.getMembInv_recipient(), prj_domain);
		log.debug("메일샌더 실행");
		/*
		 * log.debug("이메일"+ memb_mail); log.debug("도메인"+prj_domain);
		 * 
		 * invitation.setPrj_domain(prj_domain);
		 * invitation.setMembInv_recipient(memb_mail);
		 */
		invitation.setPrj_domain(prj_domain);
		
		service.insertAttendant(invitation);

		log.debug("메일초대 도메인" + invitation.getPrj_domain());
		log.debug("메일초대인 메일" + invitation.getMembInv_recipient());

		return "redirect:./manager/invitation";
	}

	/**
	 * 초대 이메일 중복체크
	 * 
	 * @param memb_mail
	 * @return 결과값
	 */
	@ResponseBody
	@PostMapping("mailIdCheck")
	public int idCheck(HttpServletRequest request,InvitationMember invitationMember, String membInv_recipient) {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		log.debug("이메일 : {}", membInv_recipient);
		log.debug("도메인 : {}", prj_domain);

		invitationMember.setMembInv_recipient(membInv_recipient);
		invitationMember.setPrj_domain(prj_domain);
		int result = service.invitationIdSearchOne(invitationMember);

		log.debug("결과 : {}", result);

		return result;
	}

	

	/**
	 * 관리자 업무페이지에서 엑셀 다운로드
	 * 
	 * @param response
	 * @throws IOException
	 */
	@PostMapping("excelDownload")
	public void excelDownload(HttpServletRequest request, HttpServletResponse response, Members members,
			@RequestParam(value="email") String email)
			throws IOException {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		// @RequestParam(value="chkbox[]") List<String> chkbox

		
		log.debug("체크박스 받은값: {}", email);
		
		String[] emails = email.split(",");
		
		log.debug(emails[0]);
		
		//DB에 들려서 조인해서 데이터 불러오기
		
		
		
		ArrayList<WorkEmail> workEmail = new ArrayList<>();
		
		for (int i = 0; i < emails.length; i++) {
			workEmail.get(i).setMemb_mail(emails[i]);
			log.debug("업무관리 엑셀 이메일:" ,workEmail.get(i).getMemb_mail());
		}
		
		
	
		
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("업무리스트");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;

		// Header
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("이메일");
		cell = row.createCell(1);
		cell.setCellValue("이름");
		cell = row.createCell(2);
		cell.setCellValue("작업진척도");
		cell = row.createCell(3);
		cell.setCellValue("업무능률");
	

		// Body
		for (int i = 0; i < emails.length; i++) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue(emails[i]);
			cell = row.createCell(1);
			cell.setCellValue(emails[i]);
			cell = row.createCell(2);
			cell.setCellValue("작업진척도");
			cell = row.createCell(3);
			cell.setCellValue("업무능률");

			
		}

		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
		response.setHeader("Content-Disposition", "attachment;filename="+prj_domain+"_woksList.xlsx");

		// Excel File Output
		wb.write(response.getOutputStream());
		wb.close();
	}
	
	
	/**
	 * 드라이브 폼으로 이동
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("drive")
	public String drive(Model model
			,@RequestParam(name="page",defaultValue = "1") int page
			,String searchWord) {
		//페이지 정보 생성
				PageNavigator navi = service.getPageNavigator(
						pagePerGroup,countPerPage,page,searchWord);
				
				//현재 페이지 글 정보
				//DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
				ArrayList<DriveFile> list = service.listDriveFile(navi,searchWord);
				log.debug("list 결과: {}",list);
				
				//리스트를 모델에 저장하고 HTML에서 출력
				model.addAttribute("navi",navi);
				model.addAttribute("list",list);
				model.addAttribute("searchWord",searchWord);

		return "managerView/drive";
	}
	
	
	/**
	 * 드라이브 파일 저장
	 * @param driveFile
	 * @param upload
	 * @return
	 */
	@PostMapping("/drive")
	public String drive(DriveFile driveFile, @RequestParam MultipartFile upload) {

		if(upload != null && !upload.isEmpty()) {

				log.debug("uploadPath : {}", uploadPath);
				String uploadDrive = uploadPath + "/drive";
				String savedfile = FileService.saveFile(upload, uploadDrive);
				driveFile.setDriveFile_ogFile(upload.getOriginalFilename());
				driveFile.setDriveFile_saveFile(savedfile);
				
		}	
		int result = service.insertDrive(driveFile);
		
		return "redirect:./manager/drive";
	}
	
	/**
	 * 주소록 폼 이동
	 * 
	 * @author 김지윤
	 * @param model
	 * @param page
	 * @param searchWord
	 * @return
	 */
	@GetMapping("/addressBook")
	public String addressBook(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			String searchWord,String domain) {
		
		String calledValue = request.getServletPath();
	     String[] splitedUrl = calledValue.split("/");
	     String prj_domain = splitedUrl[1];
		
		// 페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, searchWord);

		// 현재 페이지 글 정보
		// DB에서 게시판의 모든 글을 조회.ArrayList 타입으로 리턴받음.
		ArrayList<Members> list = service.listManager(prj_domain,navi, searchWord);
		log.debug("list 결과: {}", list);

		// 리스트를 모델에 저장하고 HTML에서 출력
		model.addAttribute("navi", navi);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);

		return "managerView/addressBook";
	}
	

	
	@GetMapping("/chart")
	public String chart() {
		return "managerView/chart";
	}
	
	

	/*
	 * 첨부파일 다운로드
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String fileDownload(int driveFileNum, Model model, 
								HttpServletResponse response) {
		
		log.debug("드라이브: " + driveFileNum);
		
		 DriveFile driveFile = service.readDriveFile(driveFileNum);
		
		//원래의 파일명
		String originalfile = new String(driveFile.getDriveFile_ogFile());
		try {
			response.setHeader("Content-Disposition", 
					" attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/drive/" + driveFile.getDriveFile_saveFile();
		
		
		log.debug("fullPath: " + fullPath);
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}	
	
	
}
