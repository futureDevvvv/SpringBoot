package net.softsociety.issho.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {//지우기바람
	String prj_domain;
	String prj_name;
	String prj_date;
	int prj_enabled;
}
