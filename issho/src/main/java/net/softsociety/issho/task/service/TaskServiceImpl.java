package net.softsociety.issho.task.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.task.dao.TaskDAO;
import net.softsociety.issho.task.domain.Task;

@Slf4j
@Transactional
@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskDAO taskDAO;
	
	//* 테스크 전체 검색
	public ArrayList<Task> SelectAlltask(String domain) {
		log.debug("SelectAlltask 실행");
		ArrayList<Task> result = taskDAO.SelectAlltask(domain);	
		log.debug("result : {}", result);
		return result;
	}
	
}
