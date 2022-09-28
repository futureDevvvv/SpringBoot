package net.softsociety.issho.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskReply {
	private int 	treply_seq;
	private int 	task_seq;
	private String 	treply_writer;
	private String 	treply_content;
	private String 	treply_ogFile;
	private String 	treply_saveFile;
	private String	treply_inputdate;
}
