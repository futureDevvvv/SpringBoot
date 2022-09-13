package net.softsociety.issho.manager.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.issho.manager.domain.Members;

@Mapper
public interface MembersDAO {
	public ArrayList<Members> listMembers(
			HashMap<String, String> map,RowBounds rb);

	public int count(HashMap<String, String> map);
}
