package net.softsociety.issho.manager.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.manager.dao.ManagerDAO;
import net.softsociety.issho.manager.domain.DriveFile;
import net.softsociety.issho.manager.domain.InvitationMember;

import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.util.PageNavigator;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDAO membersDAO;
	
	
	
	@Override
	public ArrayList<Members> listManager(String prj_domain,PageNavigator navi, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		map.put("prj_domain", prj_domain);
		log.debug("서비스단 도메인:" + prj_domain);
		RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
		ArrayList<Members> list = membersDAO.listMembers(map, rb);
		
		return list;
	}

	@Override
	public PageNavigator getPageNavigator(
			int pagePerGroup, int countPerPage, int page,
			String searchWord) {
		
		//전체 글 개수	
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		int total = membersDAO.count(map);
		
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
		return navi;
	}

	@Override
	public Members getMemberInfo(String domain) {
		
		Members members = membersDAO.getMemberInfo(domain);
		
		return members;
	}

	@Override
	public void insertAttendant(InvitationMember invitation) {
		membersDAO.insertAttendant(invitation);
	}

	@Override
	public int invitationIdSearchOne(InvitationMember invitationMember) {
		int result = membersDAO.invitationIdSearchOne(invitationMember);
		return result;
	}

	@Override
	public ArrayList<DriveFile> listDriveFile(PageNavigator navi, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord);
		RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
		ArrayList<DriveFile> list = membersDAO.listDriveFile(map, rb);
		
		return list;
	}

	@Override
	public int insertDrive(DriveFile driveFile) {
		return membersDAO.insertDrive(driveFile);
	}

	@Override
	public DriveFile readDriveFile(int driveFile_seq) {
		DriveFile driveFile = membersDAO.readDriveFile(driveFile_seq);
		return driveFile;
	}

	@Override
	public Members listManager(String domain,String email) {
		HashMap<String, String> map = new HashMap<>();
		map.put("domain", domain);
		map.put("email", email);
		Members members = membersDAO.listMembers2(map);
		return members;
	}

	
	
	

}
