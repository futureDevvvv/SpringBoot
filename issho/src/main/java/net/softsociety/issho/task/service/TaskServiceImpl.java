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
import net.softsociety.issho.manager.util.PageNavigator;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.task.dao.TaskDAO;
import net.softsociety.issho.task.domain.Task;
import net.softsociety.issho.task.domain.Taskfile;
import net.softsociety.issho.task.domain.Taskstaff;

@Slf4j
@Transactional
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;

	// * 테스크 전체 검색
	public ArrayList<Task> SelectAlltask(String domain) {
		log.debug("SelectAlltask 실행");
		ArrayList<Task> result = taskDAO.SelectAlltask(domain);
		log.debug("result : {}", result);
		return result;
	}

	@Override
	public void addNewTask(Task task) {

		taskDAO.addNewTask(task);

	}

	@Override
	public void addStaffs(Taskstaff staff) {

		taskDAO.addStaffs(staff);

	}

	@Override
	public void addFiles(Taskfile taskfile) {
		taskDAO.addFiles(taskfile);
	}

	@Override
	public List<Task> myAllocate(Map<String, String> map) {
		// TODO Auto-generated method stub

		ArrayList<Task> list = taskDAO.myAllocate(map);
		
		return list;
	}

	@Override
	public List<Task> myCharged(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList<Task> list = taskDAO.myCharged(map);
		
		return list;
	}

	@Override
	public void changeState(Map<String, String> map) {
		// TODO Auto-generated method stub
		taskDAO.changeState(map);
	}

	@Override
	public List<Taskstaff> projectMembers(String prj_domain) {
		// TODO Auto-generated method stub
		List<Taskstaff> list = taskDAO.projectMembers(prj_domain);
		
		return list;
	}
	//관리자페이지 태스크 리스트
	@Override
	public ArrayList<Task> SelectAlltaskMG(String prj_domain, PageNavigator navi, String searchWord) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		map.put("prj_domain", prj_domain);
		RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
		ArrayList<Task> list = taskDAO.SelectAlltaskMG(map, rb);
		
		return list;
	}
	
	
}
