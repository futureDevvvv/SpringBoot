package net.softsociety.issho.manager.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Members {

	String memb_mail;	
	String memb_name;	
	String memb_pwd;	
	String memb_ogFile;		
	String memb_savedFile;		
	String memb_work;		
	String memb_phone;		
	boolean enabled;	//계정상태. 1:사용가능, 0:사용불가능
	String rolename;	//사용자 구분. 'ROLE_USER','ROLE_ADMIN'중 하나
	
	
	
	
}
