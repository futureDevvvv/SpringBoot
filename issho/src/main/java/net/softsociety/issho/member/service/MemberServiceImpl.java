package net.softsociety.issho.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.issho.member.dao.MemberDAO;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memDao;
	
	@Override
	public int idSearchOne(String memb_mail) {

		int result = memDao.idSearchOne(memb_mail);
		
		return result;
	}

}
