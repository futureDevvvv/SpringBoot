package net.softsociety.issho.manager.service;

import java.util.ArrayList;

import net.softsociety.issho.manager.domain.Members;
import net.softsociety.issho.manager.util.PageNavigator;


public interface MembersService {

	public ArrayList<Members> listMembers(
			PageNavigator navi,String searchWord);
	
	//페이지 정보 생성
	public PageNavigator getPageNavigator(
			int pagePerGroup, int countPerPage, 
			int page,String searchWord);
	
	
}
