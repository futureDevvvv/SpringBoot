package net.softsociety.issho.task.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.member.dao.MemberDAO;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.Taskfile;
import net.softsociety.issho.task.domain.Taskstaff;
import net.softsociety.issho.task.service.TaskService;
import net.softsociety.issho.util.FileService;

@Controller
@Slf4j
@RequestMapping("**/task")
public class TaskController {

	@Autowired
	MemberService memservice;

	@Autowired
	TaskService taskservice;

	@Autowired
	MemberDAO memDao;

	// 첨부파일 저장할 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	/**
	 * 태스크 리스트 이동
	 * 
	 * @param user
	 * @param model
	 * @param domain
	 * @return
	 * 
	 * @author 윤영혜
	 */

	@GetMapping("/taskList")
	public String taskList(@AuthenticationPrincipal UserDetails user, Model model, String domain) {

		String id = user.getUsername();

		// 윤영혜 : 회원 정보 조회
		Members member = memDao.getUserById(id);
		ArrayList<Members> pjMemList = memservice.searchPjMem("scit112");

		model.addAttribute("member", member);
		model.addAttribute("list", pjMemList);

		// 신승훈 * 테스크 전체 검색 + 멤버 테이블 멤버 NAME 검색
		ArrayList<Task> tasklist = taskservice.SelectAlltask("scit41");
		log.debug("tasklist : {}", tasklist);
		model.addAttribute("tasklist", tasklist);

		return "taskView/task_List";
	}

	/**
	 * 새로운 프로젝트 추가
	 * 
	 * @param user
	 * @param task
	 * @param staffs
	 *
	 * @author 윤영혜
	 */

	@PostMapping("/addNewTask")
	public String newProject(@AuthenticationPrincipal UserDetails user, Task task, String memList2,
			@RequestParam List<MultipartFile> uploads) {
	
		task.setPrj_domain("scit112");
	
		// 담당자 받을 배열s
		String[] staffList = null;

		log.debug("task 객체 : {}", task);
		log.debug("staffs : {}", memList2);
		log.debug("uploads : {}", uploads);

		// 할당자 아이디 받아오기
		String id = user.getUsername();

		// sender를 할당자 아이디로
		task.setTask_sender(id);

		// 진행 중일 때 : 예상 시작일을 시작일로 바꿔주자
		if ((task.getTask_state()).equals("1")) {
			task.setTask_startdate(task.getExp_startdate());
			task.setExp_startdate("");
		} else if ((task.getTask_state()).equals("3")) {
			// 진행 완료일 때 : 예상 종료일을 종료일로 바꿔주자
			task.setTask_enddate(task.getExp_enddate());
			task.setExp_enddate("");
		}

		// 담당자가 없다면 할당자를 담당자로 설정해주고, 담당자가 있는 경우 split하여 담는다.
		if (memList2.length() == 0 || memList2.equals("")) {
			memList2 = id;
		} else {
			staffList = memList2.split(",");
		}

		log.debug("staffs : {}", memList2);
		
		// 태스크 추가
		taskservice.addNewTask(task);
		log.debug("저장후 task:{}", task);
		
		log.debug("memList2 길이 : {}", staffList.length);

		// 해당 태스크에 대한 담당자 추가
		for (int i = 0; i < staffList.length; i++) {
			Taskstaff staff = new Taskstaff(task.getTask_seq(), staffList[i], 0);
			log.debug("staff 객체 : {} ", staff);
			taskservice.addStaffs(staff);
		}

		log.debug("uploads : {}", uploads.size());
		
		// 파일 관련 처리
		if (uploads != null && !uploads.isEmpty()) {
			log.debug("파일 처리 진입 완료");
			for (int i = 0; i < uploads.size(); i++) {
				log.debug("upload 파일 : {}", uploads.get(i));
				if (uploads.get(i) != null && !uploads.get(i).isEmpty()) {
					log.debug("{}", uploads.get(i).getContentType());
					String savedfile = FileService.saveFile(uploads.get(i), uploadPath);
					Taskfile taskfile = new Taskfile(task.getTask_seq(), uploads.get(i).getOriginalFilename(), savedfile);
					
					log.debug("taskfile : {}", taskfile);
					taskservice.addFiles(taskfile);
				}
			}
		}
		log.debug("task 객체 처리 후 : {}", task);
		return "redirect:/task/taskList";
	}
	
	
}
