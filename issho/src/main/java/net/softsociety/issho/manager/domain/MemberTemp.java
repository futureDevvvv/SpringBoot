package net.softsociety.issho.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTemp {

	String prj_domain;
	String memb_mail;
	int performance = 0;
}
