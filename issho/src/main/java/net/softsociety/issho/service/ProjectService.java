package net.softsociety.issho.service;

import net.softsociety.issho.domain.Member;
import net.softsociety.issho.domain.Project;
import net.softsociety.issho.domain.ProjectMember;

public interface ProjectService {//지우기 바람
	//프로젝트 생성
	public int createProjectAndMember(Project project, Member member);
	
	//프로젝트멤버 생성
	public int insertProjectMember(ProjectMember projectMember);

}
