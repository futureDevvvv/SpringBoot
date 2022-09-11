package net.softsociety.issho.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.domain.Project;
import net.softsociety.issho.domain.ProjectMember;

@Mapper
public interface ProjectDAO {
	//프로젝트 생성
	public int insert(Project project);
	//프로젝트멤버 생성
	public int insertProjectMember(ProjectMember projectMember);

}
