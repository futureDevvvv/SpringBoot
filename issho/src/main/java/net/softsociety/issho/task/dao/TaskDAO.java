package net.softsociety.issho.task.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.issho.task.domain.Bookmark;

import net.softsociety.issho.manager.util.PageNavigator;
import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.task.domain.GanttTask;

import net.softsociety.issho.task.domain.Task;

import net.softsociety.issho.task.domain.Taskfile;
import net.softsociety.issho.task.domain.Taskstaff;


@Mapper
public interface TaskDAO {


	// 신승훈 * 태스크 네비게이터
	public int countTask(Map<String, String> map);
	// 신승훈 * 태스크 상세보기 (조회)
	public Task selectTaskByTaskSeq(int taskSeq);
	// 신승훈 * 테스크 조건 조회
	public ArrayList<Task> selectTaskAll(Map<String, String> map, RowBounds rowBounds);
	// 신승훈 * 즐겨찾기 추가
	public void insertBookmark(Bookmark bookmark);
	// 신승훈 * 즐겨찾기 삭제
	public void deleteBookmark(Bookmark bookmark);
	// 신승훈 * 즐겨찾기 유무 확인
	public Bookmark selectBookmark(Bookmark bookmark);
	// 신승훈 * 테스크 스태프 리스트 조회
	public ArrayList<Taskstaff> selectStaff(int taskSeq);
	// 신승훈 * 수행도 변경
	public int updatePerform(Taskstaff taskStaff);
	// 신승훈 * 진행상태 변경
	public int stateChange(Task task);
	// 신승훈 * 상세보기 첨부파일 확인
	public List<Taskfile> selectTaskFile(String taskSeq);
	
	

	//* 테스크 전체 검색

	public void addNewTask(Task task);

	public void addStaffs(Taskstaff staff);

	public void addFiles(Taskfile taskfile);

	public void changeState(Map<String, String> map);

	public List<Taskstaff> projectMembers(String prj_domain);

	public ArrayList<Task> myAllocate(Map<String, String> map);

	public ArrayList<Task> myCharged(Map<String, String> map);

	public ArrayList<Task> SelectAlltaskMG(HashMap<String, String> map, RowBounds rb);

	public void changeDate(GanttTask task);
	
	public List<Task> SelectAllTask(String prj_domain);



}
