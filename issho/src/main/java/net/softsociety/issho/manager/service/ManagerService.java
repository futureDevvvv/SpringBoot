package net.softsociety.issho.manager.service;

import java.util.ArrayList;

import net.softsociety.issho.manager.util.PageNavigator;
import net.softsociety.issho.member.domain.Members;


public interface ManagerService {

	public ArrayList<Members> listManager(
			PageNavigator navi,String searchWord);
	
	//페이지 정보 생성
	public PageNavigator getPageNavigator(
			int pagePerGroup, int countPerPage, 
			int page,String searchWord);
	
	
}
