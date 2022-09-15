package net.softsociety.issho.member.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.issho.member.dao.MemberDAO;
import net.softsociety.issho.member.domain.Members;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	public ArrayList<Members> searchPjMem(String prj_domain) {
		// TODO Auto-generated method stub

		ArrayList<Members> pjMemList = memDao.searchPjMem(prj_domain);
		
		return pjMemList;
	
	}

	

}
