package net.softsociety.issho.member.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

	public int idSearchOne(String memb_mail);

	
}
