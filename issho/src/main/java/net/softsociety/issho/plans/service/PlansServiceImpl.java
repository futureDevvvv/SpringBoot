package net.softsociety.issho.plans.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.plans.dao.PlansDAO;
import net.softsociety.issho.plans.domain.Attendant;
import net.softsociety.issho.plans.domain.Plans;

@Transactional
@Service
@Slf4j
public class PlansServiceImpl implements PlansService{

	@Autowired
	private PlansDAO plansDAO;
	
	// * 일정 저장
	@Override
	public int savePlan(Plans plans, String memberMail, List<String> attendees) {
		int result = plansDAO.savePlan(plans);
		
		if(result != 1)
			return 0;
		
		result = plansDAO.insertAttendant(new Attendant(plans.getPlan_seq(), memberMail, 1));
		if(result != 1)
			return 0;
		
		for(String attendeeMail : attendees) {
			plansDAO.insertAttendant(new Attendant(plans.getPlan_seq(), attendeeMail, 0));
		}
		
		return result;
	}
	
	// * 저장된 일정 불러오기
	@Override
	public ArrayList<Plans> selectPlansByMemMail(String username) {
		log.debug("selectPlans() called");
		return plansDAO.selectPlansByMemMail(username);
	}
	
	// * 일정 수정
	@Override
	public int updatePlan(Plans plans, List<String> deleteMembList, List<String> insertMembList) {
		int result = plansDAO.updatePlan(plans);
		
		if(result != 1)
			return 0;
		
		// 수정시 삭제할 참석자
		for(String deleteMember : deleteMembList) {
			plansDAO.deleteAttendant(new Attendant(plans.getPlan_seq(), deleteMember, 0));
		}
		
		for(String insertMember : insertMembList) {
			plansDAO.insertAttendant(new Attendant(plans.getPlan_seq(), insertMember, 0));
		}
		
		return result;
	}
	
	// * 현재 일정의 참석자 전부 셀렉
	@Override
	public List<Attendant> selectattendants(int plan_seq) {
		log.debug("selectattendants() called");
		List<Attendant> result = plansDAO.selectattendants(plan_seq);
		return result;
	}

	// * 일정 삭제
	@Override
	public int deletePlan(Plans plans) {
		int result = plansDAO.deletePlan(plans);
		return result;
	}
	
	
}
