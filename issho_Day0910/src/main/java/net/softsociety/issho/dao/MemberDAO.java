package net.softsociety.issho.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.domain.Member;

@Mapper
public interface MemberDAO {
	public int insert(Member member);
}
