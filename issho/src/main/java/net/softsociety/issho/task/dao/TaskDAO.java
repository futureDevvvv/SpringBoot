package net.softsociety.issho.task.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.task.domain.Task;

@Mapper
public interface TaskDAO {
	//* 테스크 전체 검색
	public ArrayList<Task> SelectAlltask(String domain);
	

}
