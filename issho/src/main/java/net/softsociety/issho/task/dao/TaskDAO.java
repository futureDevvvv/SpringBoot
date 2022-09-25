package net.softsociety.issho.task.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.Taskfile;
import net.softsociety.issho.task.domain.Taskstaff;

@Mapper
public interface TaskDAO {
	//* 테스크 전체 검색
	public ArrayList<Task> SelectAlltask(String domain);

	public void addNewTask(Task task);

	public void addStaffs(Taskstaff staff);

	public void addFiles(Taskfile taskfile);

	public void changeState(Map<String, String> map);

	public List<Taskstaff> projectMembers(String prj_domain);

	public ArrayList<Task> myAllocate(Map<String, String> map);

	public ArrayList<Task> myCharged(Map<String, String> map);
	

}
