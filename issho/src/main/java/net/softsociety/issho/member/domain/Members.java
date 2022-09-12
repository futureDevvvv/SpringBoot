package net.softsociety.issho.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Members {
	
	private String memb_mail;
	private String memb_name;
	private String memb_pwd;
	private String memb_ogfile;
	private String memb_savedfile;
	private String memb_work;
	private String memb_phone;
	private int enabled;
	private String rolename;
}
