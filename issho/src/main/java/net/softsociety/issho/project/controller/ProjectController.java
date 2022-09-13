package net.softsociety.issho.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.project.domain.Projects;
import net.softsociety.issho.project.service.ProjectService;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService pjservice;

	/**
	 * 프로젝트 생성 폼 이동
	 * 
	 * @return
	 */
	@GetMapping("/newPj")
	public String newPj() {

		return "project/project_new";
	}

	/**
	 * 도메인 중복 여부 체크 ajax
	 * 
	 * @param prj_domain
	 * @return
	 */

	@ResponseBody
	@PostMapping("/domainCheck")
	public int domainCheck(String prj_domain) {

		int result = pjservice.domainCheck(prj_domain);
		
		log.debug("도메인 : {}", prj_domain);
		
		log.debug("결과 : {}", result);

		return result;
	}

	/**
	 * 프로젝트 생성 + PM 권한 부여
	 * 
	 * @param projects
	 * @param user
	 * @return 프로젝트 메인 화면 이동
	 */
	@PostMapping("/newPj")
	public String newPj(Projects projects, @AuthenticationPrincipal UserDetails user) {

		String id = user.getUsername();

		ProjectMember pjmb = new ProjectMember(projects.getPrj_domain(), id, "PM");

		// 새로운 프로젝트 생성
		pjservice.newProject(projects);

		// 프로젝트 생성한 사람에게 pm 권한 부여
		pjservice.grantPM(pjmb);

		return "project/project_new";
	}
	
	/**
	 * 
	 * @param prj_domain
	 * @return
	 */
	@GetMapping("/main/{prj_domain}")
	public String enterPj(@PathVariable String prj_domain, Model model) {
		
		Projects project = pjservice.searchOne(prj_domain);
		
		model.addAttribute("project", project);
		
		return "project/project_main";
	}
	
}
