package net.softsociety.issho.manager.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.issho.member.domain.Members;


@Mapper
public interface ManagerDAO {
	public ArrayList<Members> listMembers(
			HashMap<String, String> map,RowBounds rb);

	public int count(HashMap<String, String> map);
}