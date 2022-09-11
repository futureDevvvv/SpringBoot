package net.softsociety.issho.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMember {
	String	prj_domain;
	String	memb_mail;
	String	pjmb_right;

}
