package net.softsociety.issho.project.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.project.domain.Projects;

@Mapper
public interface ProjectDAO {

	int domainCheck(String prj_domain);

	void newProject(Projects projects);

	void grantPM(ProjectMember pjmb);

	Projects searchOne(String prj_domain);

}
