package net.softsociety.issho.manager.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.issho.manager.domain.DriveFile;
import net.softsociety.issho.manager.domain.InvitationMember;
import net.softsociety.issho.member.domain.Members;


@Mapper
public interface ManagerDAO {
	public ArrayList<Members> listMembers(
			HashMap<String, String> map,RowBounds rb);

	public int count(HashMap<String, String> map);

	public Members getMemberInfo(String domain);

	public void insertAttendant(InvitationMember invitation);

	public int invitationIdSearchOne(InvitationMember invitationMember);


	public ArrayList<DriveFile> listDriveFile(
			HashMap<String, String> map, RowBounds rb);

	public void updateAccept(InvitationMember invitation);

	public InvitationMember getOneObject(InvitationMember invitation);

	public int insertDrive(DriveFile driveFile);

	public DriveFile readDriveFile(int driveFile_seq);

	public Members listMembers2(HashMap<String, String> map);

}
