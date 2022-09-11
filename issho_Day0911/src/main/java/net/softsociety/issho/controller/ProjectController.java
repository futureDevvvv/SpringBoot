package net.softsociety.issho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.domain.Member;
import net.softsociety.issho.domain.Project;
import net.softsociety.issho.service.ProjectService;

@Slf4j
@Controller
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	/**
	 * @brief	프로젝트 생성 화면 
	 * @param	project : 객체의 필드,member : 객체의 필드, photoFile : 업로드할 사진 파일
	 * @return	로그인 폼
	 */
	@PostMapping("register")
	public String register(Project project, Member member, MultipartFile photoFile, Model model) {
		log.debug("----- POST : /project/register");
		log.debug("----- PARAM : project {}", project);
		log.debug("----- PARAM : member {}", member);
		
		//DB 멤버(관리자) 및 프로젝트 등록 
		int result = projectService.createProjectAndMember(project, member);
		
		log.debug("----- MODEL : project {}", project);
		model.addAttribute("project", project);
		
		log.debug("----- 호출 : /memberView/login");
		return "/memberView/login";
	}

	
	/**
	 * @brief	프로젝트 생성 화면 전달
	 * @param	도메인명
	 * @return	로그인 폼
	 */
	@PostMapping("newProject")
	public String newProject(String prj_domain, Model model) {
		log.debug("----- POST : /project/newProject");
		log.debug("----- PARAM : prj_domain {}", prj_domain);
		//CODE HERE
		
		model.addAttribute("prj_domain", prj_domain);
		
		log.debug("----- 호출 : /projectView/newProject");
		return "/projectView/newProject";
	}
	
	/**
	 * 메인화면
	 */
	
	@PostMapping("mainHome")
	public String mainHome(Member member, Model model) {
		log.debug("----- POST : /project/mainHome");
		log.debug("----- PARAM : member {}", member);
		//CODE HERE
		model.addAttribute("member", member);

		log.debug("----- 호출 : /projectView/mainHome");
		return "/projectView/mainHome";
	}
}
