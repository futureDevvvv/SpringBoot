package net.softsociety.issho.member.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.member.domain.Members;

@Mapper
public interface MemberDAO {
	public ArrayList<Members> listMembers(Members members);

	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);

	public int deleteMember(Members members);


	



	
}
