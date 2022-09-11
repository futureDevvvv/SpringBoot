package net.softsociety.issho.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	String	memb_mail;
	String	memb_name;
	String	memb_pwd;
	String	memb_ogFile;
	String	memb_savedFile;
	String	memb_work;
	String	memb_phone;
	int		enabled;
	String	rolename;
}
