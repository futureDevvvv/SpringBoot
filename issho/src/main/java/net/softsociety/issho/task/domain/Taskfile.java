package net.softsociety.issho.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taskfile {
	
	private int tfile_seq;
	private int task_seq;
	private String tfile_ogfile;
	private String tfile_savefile;

}
