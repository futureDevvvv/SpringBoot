package net.softsociety.issho.plans.service;

import java.util.ArrayList;
import java.util.List;

import net.softsociety.issho.plans.domain.Attendant;
import net.softsociety.issho.plans.domain.Plans;

public interface PlansService {
	
	// * 일정 저장
	public int savePlan(Plans plans, String memberMail, List<String> attendees);
	// * 저장된 일정 불러오기
	public ArrayList<Plans> selectPlansByMemMail(String username);
	// * 일정 수정
	public int updatePlan(Plans plans, List<String> delete_att, List<String> insert_att);
	// * 현재 일정의 참석자 전부 셀렉
	public List<Attendant> selectattendants(int plan_seq);
	// * 일정 삭제
	public int deletePlan(Plans plans);


}
