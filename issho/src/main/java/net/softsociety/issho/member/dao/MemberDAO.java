package net.softsociety.issho.member.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.member.domain.Members;

@Mapper
public interface MemberDAO {

	public int idSearchOne(String memb_mail);

	public void memberJoin(Members members);


	public ArrayList<Members> searchPjMem(String prj_domain);

	public int deleteMember(Members members);

	
}
