package net.softsociety.issho.project.service;

import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.project.domain.Projects;

public interface ProjectService {

	int domainCheck(String prj_domain);

	void newProject(Projects projects);

	void grantPM(ProjectMember pjmb);

}
