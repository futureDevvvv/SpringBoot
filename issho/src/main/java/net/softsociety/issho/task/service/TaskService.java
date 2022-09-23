package net.softsociety.issho.task.service;

import java.util.ArrayList;

import net.softsociety.issho.notice.domain.Comment;
import net.softsociety.issho.notice.domain.CommentDetail;
import net.softsociety.issho.notice.domain.Notice;
import net.softsociety.issho.notice.domain.NoticeDetail;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.Taskfile;
import net.softsociety.issho.task.domain.Taskstaff;
import net.softsociety.issho.util.PageNavigator;

public interface TaskService {
	
	//* 테스크 전체 검색
	public ArrayList<Task> SelectAlltask(String domain);

	//새로운 태스크 추가
	public void addNewTask(Task task);

	//새로운 태스크에 대한 staff 추가
	public void addStaffs(Taskstaff staff);

	//새로운 태스크에 대한 file 추가
	public void addFiles(Taskfile taskfile);


}
