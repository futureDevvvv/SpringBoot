package net.softsociety.issho.plans.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.plans.domain.Attendant;
import net.softsociety.issho.plans.domain.Plans;
import net.softsociety.issho.plans.service.PlansService;
import net.softsociety.issho.util.ArrayOverlapDelete;



@Slf4j
@RequestMapping("plans")
@Controller
public class PlansHomeController {
	
	@Autowired
	PlansService plansService;
	
	@Autowired
	MemberService memberService;
	
	/**
	 * 일정 서비스 홈 화면
	 * 저장된 일정 불러오기 
	 * 참석자 불러오기
	 * 도메인 부분 나중에 수정 **
	 */
	@GetMapping("plan")
	public String plan(Plans plans, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("getPlan() called");
	
		ArrayList<Plans> attendantList = plansService.selectPlansByMemMail(user.getUsername());
		log.debug("attendantList : {}", attendantList);
		model.addAttribute("attendantlist", attendantList);
		
		ArrayList<Members> memberlist = memberService.searchPjMem("scit112");
		log.debug("attendees : {}", memberlist);
		model.addAttribute("attendees", memberlist);
		
		return "/plansView/plans_home";
	}
	
	/**
	 * 일정 저장 
	 */
	@PostMapping("savePlan")
	public String savePlan(
			@ModelAttribute Plans plans, @RequestParam(name = "attendees") List<String> attendees
			, @AuthenticationPrincipal UserDetails user
			) {
		log.debug("postSavePlan() called");
		// datetime-local의 T제거
		plans.setPlan_startDate(plans.getPlan_startDate().replace("T", " "));
		plans.setPlan_endDate(plans.getPlan_endDate().replace("T", " "));
		plans.setMemb_mail(user.getUsername());
		
		log.debug("plans : {}", plans);
		log.debug("attendees : {}", attendees);
		
		int result = plansService.savePlan(plans, user.getUsername(), attendees);
		return "redirect:/plans/plan";
	}
	
	/**
	 * 일정 수정
	 */
	@PostMapping("updatePlan")
	public String updatePlan(
			@ModelAttribute Plans plans, @RequestParam(name = "attendees", required = false) List<String> attendees
			, @AuthenticationPrincipal UserDetails user
			) {
		log.debug("updatePlan() called");
		plans.setPlan_startDate(plans.getPlan_startDate().replace("T", " "));
		plans.setPlan_endDate(plans.getPlan_endDate().replace("T", " "));
		plans.setMemb_mail(user.getUsername());
		log.debug("plans : {}", plans);
		log.debug("attendees : {}", attendees);
		
		if(attendees == null)
			attendees = new ArrayList();

	//	1. 현재 일정의 참석자 전부 셀렉
		List<Attendant> old_att = plansService.selectattendants(plans.getPlan_seq());
		log.debug("old_att : {}", old_att);
	
	//  2.업데이트로 받은 참석자 리스트랑 비교	
		List<String> old_attStr = new ArrayList();
		
		for(int i=old_att.size()-1; i>=0; i--) {
			if(old_att.get(i).getMemb_mail().equals(user.getUsername()))
				continue;
			old_attStr.add(old_att.get(i).getMemb_mail());
		}
		
		log.debug("삭제할 녀석들 : {}", ArrayOverlapDelete.arrayDelete(old_attStr, attendees));
		log.debug("추가할 녀석들 : {}", ArrayOverlapDelete.arrayDelete(attendees, old_attStr));
		
		// AT 삭제 AT 추가(기존) P 업데이트
		// DELETE ArrayOverlapDelete.arrayDelete(old_attStr, attendees) 삭제할녀석들이랑 plan_seq 전달
		List<String> delete_att = ArrayOverlapDelete.arrayDelete(old_attStr, attendees);
		List<String> insert_att = ArrayOverlapDelete.arrayDelete(attendees, old_attStr);
		
		int result = plansService.updatePlan(plans, delete_att, insert_att);
		return "redirect:/plans/plan";
	}
	
	/**
	 * 일정 삭제 
	 */
	@PostMapping("deletePlan")
	public String deletePlan(@ModelAttribute Plans plans
			, @AuthenticationPrincipal UserDetails user) {
		log.debug("deletePlan() called");
		
		int result = plansService.deletePlan(plans);
		
		return "redirect:/plans/plan";
	}
}

