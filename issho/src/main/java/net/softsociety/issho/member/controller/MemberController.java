package net.softsociety.issho.member.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.softsociety.issho.member.domain.Members;
import net.softsociety.issho.member.service.MemberService;
import net.softsociety.issho.notice.domain.NoticeDetail;
import net.softsociety.issho.util.FileService;
import net.softsociety.issho.util.PageNavigator;

@lombok.extern.slf4j.Slf4j
/**
 * @brief 멤버 관련 컨트롤러 : 회원가입
 * @author 윤영혜
 *
 */

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memService;

	/**
	 * 
	 * 로그인 폼 이동
	 */
	@GetMapping("/join")
	public String join() {

		return "member/member_join";
	}

	/**
	 * 입력값 받아서 회원가입 로직 수행
	 * 
	 * @return
	 */
	@PostMapping("/join")
	public String join(Members members, @RequestParam MultipartFile upload) {

		log.debug("전달받은 객체 : {}", members);

		if (upload != null && !upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, "c:/upload");
			members.setMemb_ogfile(upload.getOriginalFilename());
			members.setMemb_savedfile(savedfile);
		}

		log.debug("업로드 처리후 : {}", members);

		memService.memberJoin(members);

		return "redirect:/";
	}

	/**
	 * 아이디 중복체크
	 * 
	 * @param memb_mail
	 * @return 결과값
	 */
	@ResponseBody
	@PostMapping("/idCheck")
	public int idCheck(String memb_mail) {

		log.debug("이메일 : {}", memb_mail);

		int result = memService.idSearchOne(memb_mail);

		log.debug("결과 : {}", result);

		return result;
	}

	/**
	 * 로그인 폼 이동
	 * 
	 * @return
	 */
	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/member_login";
	}
}
