package net.softsociety.issho.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.dao.MemberDAO;
import net.softsociety.issho.domain.Member;

@Slf4j
@Transactional
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int insertMember(Member member) {
		log.debug("----- MemberServiceImpl : insertMember");
		log.debug("----- PARAM : member {}", member);
		
		int result = memberDAO.insert(member);

		return result;
	}
		
}
