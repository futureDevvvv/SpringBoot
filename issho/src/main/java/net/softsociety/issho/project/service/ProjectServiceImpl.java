package net.softsociety.issho.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.issho.manager.util.PageNavigator;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.project.dao.ProjectDAO;
import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.project.domain.Projects;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectDAO pjDao;
	
	@Override
	public int domainCheck(String prj_domain) {
		
		int result = pjDao.domainCheck(prj_domain);
		
		return result;
	}

	@Override
	public void newProject(Projects projects) {
	
		pjDao.newProject(projects);
		
	}

	@Override
	public void grantPM(ProjectMember pjmb) {
		
		pjDao.grantPM(pjmb);
		
	}

	@Override
	public Projects searchOne(String prj_domain) {

		Projects project = pjDao.searchOne(prj_domain);
		
		return project;
	}
	
	@Override
	public ArrayList<Projects> listProjects(PageNavigator navi, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
		ArrayList<Projects> list = pjDao.listProjects(map, rb);
		
		return list;
	}

	@Override
	public Projects getProjectsInfo(String domain) {
		
		Projects projects = pjDao.getProjectsInfo(domain);
		
		return projects;
	}
	
	
}
