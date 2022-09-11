package net.softsociety.issho.service;

import net.softsociety.issho.domain.Member;

public interface MemberService {
	
	public int insertMember(Member member); 
	
	public Member selectMember(String memb_mail);
}
