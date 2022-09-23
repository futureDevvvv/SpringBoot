package net.softsociety.issho.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.manager.domain.InvitationMember;
import net.softsociety.issho.member.service.MemberService;

@Slf4j
public class ProjectAuthorizationInterceptor implements HandlerInterceptor {

	@Autowired
	MemberService memservice;

	// プロジェクト関連経路へのアクセス時にプロジェクトメンバーの有無を確認してアクセス可否を決定
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// インターセプターからスプリングブートセキュリティの認証情報(authentication)を取得！
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.debug("auth : {}", auth);

		// 受けてきた認証情報からidを抽出しよう
		String membInv_recipient = auth.getName();
		log.debug("회원 아이디 : {}", membInv_recipient);

		String calledValue = request.getServletPath();
		log.debug("요청path : {}", calledValue);

		String[] splitedUrl = calledValue.split("/");
		log.debug("url : {} ", splitedUrl[1]);

		String prj_domain = splitedUrl[1];
		log.debug("prj_domain : {}", prj_domain);

		InvitationMember mem = new InvitationMember(membInv_recipient, prj_domain);
		log.debug("invitation mem : {}", mem);

		// プロジェクトメンバーが該当するかどうかを確認するサービスロジックを実行
		InvitationMember getMem = memservice.enterProject(mem);
		log.debug("getMem : {}", getMem);

		if (getMem == null) {
			response.sendRedirect(request.getContextPath() + "/wrongPath");
			return false;
		}

		// ログインとプロジェクトメンバーの有無確認要件を満たす場合、要請経路を進行
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}