package net.softsociety.issho.member.service;

import java.util.ArrayList;

import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.util.PageNavigator;

public interface MemberService {
	public ArrayList<Members> listMembers(Members members);
	
	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);

	public int deleteMember(Members members);

	public PageNavigator getNoticePageNavi(int pagePerGroup, int countPerPage, int page, String type,
			String searchWord);

}
