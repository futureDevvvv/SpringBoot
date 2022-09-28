package net.softsociety.issho.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.task.dao.TaskDAO;
import net.softsociety.issho.task.domain.Bookmark;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.TaskFile;
import net.softsociety.issho.task.domain.TaskStaff;
import net.softsociety.issho.util.PageNavigator;

@Slf4j
@Transactional
@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskDAO taskDAO;
	
	//* 테스크 전체 검색
	public ArrayList<Task> SelectAlltask(PageNavigator navi, String domain, String searchWord) {
		log.debug("TaskServiceImpl [SelectAlltask] Start");
		
		Map<String, String> map = new HashMap();
		map.put("prj_domain", domain);
		if(searchWord != null)
			map.put("searchWord", searchWord);
		
		RowBounds rowBounds = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
		
		ArrayList<Task> result = taskDAO.selectTaskAll(map, rowBounds);	
		
		log.debug("TaskServiceImpl [SelectAlltask] result : {}", result);
		log.debug("TaskServiceImpl [SelectAlltask] End");
		return result;
	}

	// 신승훈 * 테스크 상세보기
	@Override
	public Task selectTaskByTaskSeq(int taskSeq) {
		log.debug("TaskServiceImpl [selectTaskByTaskSeq] Start");
		
		Task result = taskDAO.selectTaskByTaskSeq(taskSeq);	
		log.debug("TaskServiceImpl [selectTaskByTaskSeq] result : {}", result);
		
		log.debug("TaskServiceImpl [selectTaskByTaskSeq] End");
		return result;
	}
	
	// 신승훈 * 테스크 조건 조회
	@Override
	public List<Task> selectTaskAll(PageNavigator navi, Map<String, String> orderList) {
		log.debug("TaskServiceImpl [selectTaskAll] Start");
		
		RowBounds rowBounds = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
		
		List<Task> result = taskDAO.selectTaskAll(orderList, rowBounds);
		log.debug("TaskServiceImpl [selectTaskAll] result : {}", result);
		
		log.debug("TaskServiceImpl [selectTaskAll] End");
		return result;
	}
	
	// 신승훈 * 태스크 네비
	@Override
	public int countAllBoard(Map<String, String> map) {
		return taskDAO.countTask(map);
	}
	
	// 신승훈 * 즐겨찾기 추가
	@Override
	public void insertBookmark(Bookmark bookmark) throws Exception {
		log.debug("TaskServiceImpl [insertBookmark] Start");
		
		Bookmark temp = taskDAO.selectBookmark(bookmark);
		if(temp != null)
			throw new Exception("북마크가 이미 있음!");
		
		taskDAO.insertBookmark(bookmark);
		
		log.debug("TaskServiceImpl [insertBookmark] End");
		return;
	}
	
	// 신승훈 * 즐겨찾기 삭제
	@Override
	public void deleteBookmark(Bookmark bookmark) {
		log.debug("TaskServiceImpl [deleteBookmark] Start");
		
		taskDAO.deleteBookmark(bookmark);
		
		log.debug("TaskServiceImpl [deleteBookmark] End");
		return;	
	}
	
	// 신승훈 * 테스크 스태프 리스트 조회
	@Override
	public ArrayList<TaskStaff> selectStaff(int taskSeq) {
		log.debug("SelectStaff() called");
		return taskDAO.selectStaff(taskSeq);
	}
	
	// 신승훈 * 수행도 변경 
	@Override
	public TaskStaff updatePerform(TaskStaff taskStaff) {
		log.debug("TaskServiceImpl [updatePerform] Start");
		
		taskStaff.setTsuper_perform(taskStaff.getTsuper_perform()*25);
		
		int result = taskDAO.updatePerform(taskStaff);
		
		log.debug("TaskServiceImpl [updatePerform] End");
		if(result == 1)
			return taskStaff;
		else
			return null;
	}
	
	// 신승훈 * 진행상태 변경
	@Override
	public Task stateChange(int task_seq, String usrid) {
		log.debug("TaskServiceImpl [stateChange] Start");
		
		// 태스크 조회
		Task task = taskDAO.selectTaskByTaskSeq(task_seq);
		log.debug("TaskServiceImpl [selectTaskAll] Old task : {}", task);
		log.debug("usrid : {}", usrid);
		
		if(!task.getTask_sender().equals(usrid))
			return null;
		
		if(task.getTask_state().equals("3")) {
			task.setTask_state("0");
		} else {
			task.setTask_state(Integer.toString(Integer.parseInt(task.getTask_state())+1));
		}
		log.debug("TaskServiceImpl [stateChange] getTask_state Param : {}", task.getTask_state());
		
		// 진행상태 변경
		int result = taskDAO.stateChange(task);
		if(result != 1)
			return null;
		
		log.debug("TaskServiceImpl [stateChange] End");
		return task;
	}

	@Override
	public List<TaskFile> selectTaskFile(String taskSeq) {
		
		List<TaskFile> Result = taskDAO.selectTaskFile(taskSeq);
		
		return Result;
	}


	
	
	
	
}
