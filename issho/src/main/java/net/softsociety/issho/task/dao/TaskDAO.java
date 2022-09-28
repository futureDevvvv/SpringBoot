package net.softsociety.issho.task.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.issho.task.domain.Bookmark;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.TaskFile;
import net.softsociety.issho.task.domain.TaskStaff;

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
	public ArrayList<TaskStaff> selectStaff(int taskSeq);
	// 신승훈 * 수행도 변경
	public int updatePerform(TaskStaff taskStaff);
	// 신승훈 * 진행상태 변경
	public int stateChange(Task task);
	
	public List<TaskFile> selectTaskFile(String taskSeq);
	
	
	
	

	


}
