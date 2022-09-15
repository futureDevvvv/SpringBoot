package net.softsociety.issho.member.service;

import java.util.ArrayList;
import java.util.Map;

import net.softsociety.issho.member.domain.Members;

public interface MemberService {
	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);


	public ArrayList<Members> searchPjMem(String prj_domain);

	public int deleteMember(Members members);

}
