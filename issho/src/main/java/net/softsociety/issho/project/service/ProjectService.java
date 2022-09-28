package net.softsociety.issho.project.service;

import java.util.ArrayList;

import net.softsociety.issho.manager.util.PageNavigator;
import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.project.domain.Projects;

public interface ProjectService {

	int domainCheck(String prj_domain);

	void newProject(Projects projects);

	void grantPM(ProjectMember pjmb);

	Projects searchOne(String prj_domain);
	
	//관리자 페이지 프로젝트 리스트
	ArrayList<Projects> listProjects(net.softsociety.issho.util.PageNavigator navi, String searchWord);

	Projects getProjectsInfo(String domain);

}
