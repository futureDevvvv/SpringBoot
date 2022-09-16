package net.softsociety.issho.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.issho.member.service.MemberService;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	MemberService memservice;
	
	//プロジェクト関連経路へのアクセス時にプロジェクトメンバーの有無を確認してアクセス可否を決定
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//インターセプターからスプリングブートセキュリティの認証情報(authentication)を取得！
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//受けてきた認証情報からidを抽出しよう
		String id = auth.getName();
		
		//プロジェクトドメインをどのように取得するか···=>requestに含まれたパラメータを読もう！
		String prj_domain = request.getParameter("prj_domain");
		
		//ログインできなかったらログインページに移動させよう。
		if(id == null) {
			response.sendRedirect(request.getContextPath() + "/loginForm");
			return false;
		}
		
		//プロジェクトメンバーが該当するかどうかを確認するサービスロジックを実行
		
		//ログインとプロジェクトメンバーの有無確認要件を満たす場合、要請経路を進行
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
