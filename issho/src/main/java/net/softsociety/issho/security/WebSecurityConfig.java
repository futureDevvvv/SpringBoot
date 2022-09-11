package net.softsociety.issho.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @brief Security 설정
 * @author 윤영혜
**/
@Configuration
public class WebSecurityConfig {
	
    @Autowired
    private DataSource dataSource;

    //설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers(
        		"/",
                "/favicon.ico",
                "/img/**",
                "/css/**",
                "/js/**",
                "/vendor/**",
//                "/project/newProject",
                "/member/login"
               ).permitAll()
//        .antMatchers("").hasAuthority("PM")
//        .antMatchers("").hasAnyAuthority("member", "PM")
        .anyRequest().authenticated()
        .and()
        
        .formLogin()
        .loginPage("/member/loginForm")
        .defaultSuccessUrl("/project/mainHome")
        .loginProcessingUrl("/member/loginaction").permitAll()
        .usernameParameter("memb_mail")
        .passwordParameter("memb_pwd")
        .and()
        
        .logout()
        .logoutUrl("/member/logout")
        .logoutSuccessUrl("/").permitAll()
        .and()
        .cors()
        .and()
        .httpBasic();

        return http.build();
    }

    //인증을 위한 쿼리
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        // 인증 (로그인)
        .usersByUsernameQuery(
        		"select memb_mail username, memb_pwd password, enabled " +
                "from members " +
                "where memb_mail = ?")
        // 권한
        .authoritiesByUsernameQuery(
        		"select memb_mail username, rolename role_name " +
                "from members " +
                "where memb_mail = ?");
    }

    // 단방향 비밀번호 암호화
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
    	//[SHHONG] 테스트를 위해 평문 저장 암호 사용
    	return NoOpPasswordEncoder.getInstance();
    	
    	//[SHHONG] 테스트 완료 후 일정 시점에서 활성화 필요
    	//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
 