package net.softsociety.issho.task.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

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
	

}
