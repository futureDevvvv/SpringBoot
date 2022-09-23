package net.softsociety.issho.member.service;

import java.util.ArrayList;

import net.softsociety.issho.manager.domain.InvitationMember;
import net.softsociety.issho.member.domain.Members;

public interface MemberService {
	
	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);

	public ArrayList<Members> searchPjMem(String prj_domain);

	public int deleteMember(Members members);
	
	//초대여부 확인 후 수락 여부 변경해주는 메소드
	public InvitationMember enterProject(InvitationMember invitation);

}
