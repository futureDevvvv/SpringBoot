package net.softsociety.issho.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {

	//chatroom
	private String chatroom_seq;
	private String chatroom_name;
	
	//chatmember
	private String chat_member;
	
	//chatmsg
	private String chatmsg_recipient;
	private String chatmsg_sendDate;
	private String chatmsg_content;
}
