package net.softsociety.issho.manager.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.issho.manager.dao.MembersDAO;
import net.softsociety.issho.manager.domain.Members;
import net.softsociety.issho.manager.util.PageNavigator;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDAO membersDAO;
	
	@Override
	public ArrayList<Members> listMembers(PageNavigator navi, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
		ArrayList<Members> list = membersDAO.listMembers(map, rb);
		
		return list;
	}

	@Override
	public PageNavigator getPageNavigator(
			int pagePerGroup, int countPerPage, int page,
			String searchWord) {
		
		//전체 글 개수	
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		int total = membersDAO.count(map);
		
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
		return navi;
	}	
}
