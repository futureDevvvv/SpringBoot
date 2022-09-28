package net.softsociety.issho.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.softsociety.issho.notice.domain.Comment;
import net.softsociety.issho.notice.domain.CommentDetail;
import net.softsociety.issho.notice.domain.Notice;
import net.softsociety.issho.notice.domain.NoticeDetail;
import net.softsociety.issho.task.domain.Bookmark;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.TaskFile;
import net.softsociety.issho.task.domain.TaskStaff;
import net.softsociety.issho.util.PageNavigator;

public interface TaskService {
	
	// 신승훈 * 태스크 전체 검색
	public ArrayList<Task> SelectAlltask(PageNavigator navi, String domain, String searchWord);
	// 신승훈 * 태스크 상세보기
	public Task selectTaskByTaskSeq(int taskSeq);
	// 신승훈 * 태스크 조건 조회
	public List<Task> selectTaskAll(PageNavigator navi, Map<String, String> orderList);
	// 신승훈 * 태스크 네비
	public int countAllBoard(Map<String, String> map);
	// 신승훈 * 즐겨찾기 추가
	public void insertBookmark(Bookmark bookmark) throws Exception;
	// 신승훈 * 즐겨찾기 삭제
	public void deleteBookmark(Bookmark bookmark);
	// 신승훈 * 테스크 스태프 리스트 조회
	public ArrayList<TaskStaff> selectStaff(int parseInt);
	// 신승훈 * 수행도 변경 ajax
	public TaskStaff updatePerform(TaskStaff taskStaff);
	// 신승훈 * 진행상태 변경 ajax
	public Task stateChange(int task_seq, String usrid);
	
	public List<TaskFile> selectTaskFile(String taskSeq);

	


}
