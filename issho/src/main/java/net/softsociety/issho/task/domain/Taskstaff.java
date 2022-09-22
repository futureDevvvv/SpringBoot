package net.softsociety.issho.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taskstaff {
	
	private int task_seq;
	private String memb_mail;
	private int tsuper_perform;

}
