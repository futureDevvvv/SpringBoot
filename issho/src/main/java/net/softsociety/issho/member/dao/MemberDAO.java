package net.softsociety.issho.member.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.member.domain.Members;

@Mapper
public interface MemberDAO {

	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);

	public int deleteMember(Members members);

	



	
}
