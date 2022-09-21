package net.softsociety.issho.member.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.issho.member.dao.MemberDAO;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.util.PageNavigator;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ArrayList<Members> listMembers(Members members){
		
		ArrayList<Members> member = memDao.listMembers(members); 
		
		return member;
	}

	@Override
	public int idSearchOne(String memb_mail) {

		int result = memDao.idSearchOne(memb_mail);
		
		return result;
	}

	@Override
	public void memberJoin(Members members) {

		String encodedPassword = passwordEncoder.encode(members.getMemb_pwd());
		
		members.setMemb_pwd(encodedPassword);
		
		memDao.memberJoin(members);
		
	}

	@Override
	public int deleteMember(Members members) {
		
		int result = memDao.deleteMember(members);
		
		return result;
	}

	@Override
	public PageNavigator getNoticePageNavi(int pagePerGroup, int countPerPage, int page, String type,
			String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
