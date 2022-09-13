package net.softsociety.issho.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
