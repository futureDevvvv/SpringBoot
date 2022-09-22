package net.softsociety.issho.plans.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.plans.domain.Attendant;
import net.softsociety.issho.plans.domain.Plans;
import net.softsociety.issho.project.domain.ProjectMember;
import net.softsociety.issho.project.domain.Projects;

@Mapper
public interface PlansDAO {
	// * 일정 저장
	public int savePlan(Plans plans);
	public int insertAttendant(Attendant attendant);
	// * 저장된 일정 불러오기
	public ArrayList<Plans> selectPlansByMemMail(String memberMail);
	// * 일정 수정
	public int updatePlan(Plans plans);
	// * 현재 일정의 참석자 전부 셀렉
	public List<Attendant> selectattendants(int plan_seq);
	// * 일정 삭제
	public int deletePlan(Plans plans);
	// * 수정시 삭제할 참석자
	public void deleteAttendant(Attendant attendant);
}
