package net.softsociety.issho.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.member.dao.MemberDAO;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.task.domain.Bookmark;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.TaskFile;
import net.softsociety.issho.task.domain.TaskStaff;
import net.softsociety.issho.task.service.TaskService;
import net.softsociety.issho.util.PageNavigator;

@Controller
@Slf4j
@RequestMapping("task")
public class TaskController {
	
	// 신승훈 * 게시판 목록의 페이지당 글 수
	@Value("${user.task.page}")
	int countPerPage;
	
	// 신승훈 * 게시판 목록의 페이지 이동 링크 수
	@Value("${user.task.group}")
	int pagePerGroup;
	
	// 신승훈 * 게시판 첨부파일 업로드 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Autowired
	MemberService memservice;
	
	@Autowired
	TaskService taskservice;
	
	@Autowired
	MemberDAO memDao;
	
	@GetMapping("/taskList")
	public String taskList(@AuthenticationPrincipal UserDetails user, 
			@RequestParam(name = "page", defaultValue = "1") int page, String searchWord,
			Model model, String domain) {
		log.debug("Controller [taskList] Start");
		
		String id = user.getUsername();
		// 신승훈 * prj_domain 설정, 나중에 변경해줘야함
		String prj_domain = "scit112";
		
		//회원 정보 조회
		Members member = memDao.getUserById(id);
		ArrayList<Members> pjMemList = memservice.searchPjMem("scit112");
		
		model.addAttribute("member", member);
		model.addAttribute("list", pjMemList);
		
		Map<String, String> map = new HashMap<>();
		map.put("prj_domain", prj_domain);
		if(searchWord != null)
			map.put("searchWord", searchWord);
		
		// 신승훈 * 태스크 네비
		int totalCount = taskservice.countAllBoard(map);
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, totalCount);
		log.debug("navi : {}", navi);
		model.addAttribute("navi", navi);
		
		// 신승훈 * 메인 화면 첫 진입시 테스크 전체 검색
		List<Task> tasklist = taskservice.SelectAlltask(navi, prj_domain, searchWord);
		log.debug("Controller [taskList] tasklist : {}", tasklist);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("page", page);
		
		log.debug("Controller [taskList] End");
		
		return "taskView/task_List2";
	}
	
	@PostMapping("/newProject")
	public void newProject(@AuthenticationPrincipal UserDetails user, Task task, String staffs) {
	
		String id = user.getUsername();
		
		task.setTask_sender(id);
	}
	
	// 신승훈 * 테스크 상세보기
	@ResponseBody
	@PostMapping("/showTaskModal")
	public Map<String, Object> showTaskModal(String taskSeq) {
		log.debug("Controller [showTaskModal] Start");
		
		Task showTask = taskservice.selectTaskByTaskSeq(Integer.parseInt(taskSeq));
		log.debug("Controller [showTaskModal] showTask : {}", showTask);
		
		ArrayList<TaskStaff> showTaskstaff = taskservice.selectStaff(Integer.parseInt(taskSeq));
		log.debug("Controller [showTaskModal] showTaskstaff : {}", showTaskstaff);
		
		// 첨부파일 가져오기
		List<TaskFile> taskFileList = taskservice.selectTaskFile(taskSeq);
		
		Map<String, Object> result = new HashMap<>();
        result.put("showTask", showTask);
        result.put("showStaff", showTaskstaff);
        
        log.debug("Controller [showTaskModal] End");
        
        return result; 
	}
	
	// 신승훈 * 테스크 조건 조회
	@ResponseBody
	@PostMapping("/selectTask")
	public Map<String, Object> selectTask(@RequestParam Map<String, String> param,
				@AuthenticationPrincipal UserDetails user){
		log.debug("Controller [selectTask] Start");
		log.debug("Controller [selectTask] param : {}", param);
		String prj_domain = "scit112";
		
		String searchWord = "";
		if(!param.get("searchWord").equals("")) {
			searchWord = param.get("searchWord");
			log.debug("searchWord가 있음! : {}", searchWord);
		}
		
		int page = Integer.parseInt(param.get("page"));
		log.debug("page : {}", page);
		
		Map<String, String> orderList = new HashMap<>();
		orderList.put("prj_domain", prj_domain);
		//Map을 사용하여 task_sender=task_sender, task_staff=task_staff 가있으면 아이디로 값 집어넣음
		if(param.containsKey("task_sender")) {
			orderList.put("task_sender", user.getUsername());
		}
		if (param.containsKey("task_staff")) {
			orderList.put("task_staff", user.getUsername());
		}
		if (param.containsKey("search_type")) {
			if(param.get("search_type").equals("bookmark"))
				orderList.put("bookmark", user.getUsername());
			else
			orderList.put(param.get("search_type"), param.get("search_type"));
		}
		if(!searchWord.equals(""))
			orderList.put("searchWord", searchWord);
		orderList.put("prj_domain", prj_domain);
		log.debug("Controller [selectTask] orderList : {}", orderList);
		
		// 신승훈 * 네비
		int totalCount = taskservice.countAllBoard(orderList);
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, totalCount);
		log.debug("navi : {}", navi);
		
		List<Task> taskList = taskservice.selectTaskAll(navi, orderList);
		log.debug("Controller [selectTask] taskList : {}", taskList);
		Map<String, Object> resultObj = new HashMap<>();
		resultObj.put("taskList", taskList);
		resultObj.put("page", page);
		resultObj.put("navi", navi);
		log.debug("Controller [selectTask] End");
		
		return resultObj;
	}
	
	// 신승훈 * 즐겨찾기
	@ResponseBody
	@PostMapping("/bookmark")
	public boolean bookmark(@RequestParam String taskSeq,
				@AuthenticationPrincipal UserDetails user) {
		log.debug("Controller [bookmark] Start");
		log.debug("taskSeq : {}", taskSeq);
		
	    Bookmark bookmark = new Bookmark();
	    bookmark.setTask_seq(Integer.parseInt(taskSeq));
	    bookmark.setMemb_mail(user.getUsername());
	  
	    try { 
	    	// 신승훈 * 즐겨찾기 추가
		    taskservice.insertBookmark(bookmark);
		    log.debug("Controller [bookmark] End");
		    return true;
	    } catch(Exception e) {
	    	log.debug(e.getMessage());
	    	// 신승훈 * 즐겨찾기 삭제
		    taskservice.deleteBookmark(bookmark); 
		    log.debug("Controller [bookmark] End");
		    return false; 
	    }
	}
	
	// 신승훈 * 수행도 변경 ajax
	@ResponseBody
	@PostMapping("/performChange")
	public TaskStaff performChange(@ModelAttribute TaskStaff taskStaff,
				@AuthenticationPrincipal UserDetails user) {
		log.debug("Controller [performChange] Start");
		log.debug("taskStaff : {}", taskStaff);
		
		taskStaff.setMemb_mail(user.getUsername());
		
		taskStaff = taskservice.updatePerform(taskStaff);
		
		log.debug("Controller [performChange] End");
		return taskStaff;
	}
	
	// 신승훈 * 진행상태 변경 ajax
	@ResponseBody
	@PostMapping("/stateChange")
	public Task stateChange(@RequestParam int task_seq,
			@AuthenticationPrincipal UserDetails user) throws Exception {
		log.debug("Controller [stateChange] Start");
		
		Task task = taskservice.stateChange(task_seq, user.getUsername());
		if(task == null)
			throw new Exception("Controller [stateChange] 에러");
		
		log.debug("Controller [stateChange] End");
		return task;
	}
}
