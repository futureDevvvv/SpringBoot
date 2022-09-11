package net.softsociety.issho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.dao.MemberDAO;
import net.softsociety.issho.dao.ProjectDAO;
import net.softsociety.issho.domain.Member;
import net.softsociety.issho.domain.Project;
import net.softsociety.issho.domain.ProjectMember;

@Slf4j
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int createProjectAndMember(Project project, Member member) {
	
		int result = 0;
		//1. DB 멤버(관리자) 생성
		result = memberDAO.insert(member);
		if(result == 0) return result;
		
		//2. DB 프로젝트 생성
		result = projectDAO.insert(project);
		if(result == 0) return result;
		
		//3. DB 프로젝트멤바 생성(PM 권한)
		ProjectMember projectMember = new ProjectMember();
		projectMember.setPrj_domain(project.getPrj_domain());
		projectMember.setMemb_mail(member.getMemb_mail());
		projectMember.setPjmb_right("PM");

		result = projectDAO.insertProjectMember(projectMember);

		return result;
	}
	
	//프로젝트멤버 생성
	@Override
	public int insertProjectMember(ProjectMember projectMember) {
		int result = projectDAO.insertProjectMember(projectMember);
		
		return result;
	}


}
