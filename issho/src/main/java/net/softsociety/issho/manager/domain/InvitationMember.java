package net.softsociety.issho.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationMember {
	
	private String membInv_seq;
	private String prj_domain;
	private String membInv_recipient;
	private int membInv_accept;
}
