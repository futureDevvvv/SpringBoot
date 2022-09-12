package net.softsociety.issho.member.service;

import net.softsociety.issho.member.domain.Members;

public interface MemberService {
	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);;

}
