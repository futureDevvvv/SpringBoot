package net.softsociety.issho.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Member implements UserDetails {
	
	private static final long serialVersionUID = 3222388532456457383L;
	
	String	memb_mail;
	String	memb_name;
	String	memb_pwd;
	String	memb_ogFile;
	String	memb_savedFile;
	String	memb_work;
	String	memb_phone;
	int		enabled;
	String	rolename;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		return this.memb_pwd;
	}
	@Override
	public String getUsername() {
		return this.memb_mail;
	}	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		if(this.enabled == 0) return false;
		else return true;
	}
}
