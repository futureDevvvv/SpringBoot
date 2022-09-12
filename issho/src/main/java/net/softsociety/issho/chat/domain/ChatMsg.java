package net.softsociety.issho.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMsg {

	private String chatmsg_seq;
	private String chatroom_seq;
	private String chatmsg_recipient;
	private String chatmsg_sendDate;
	private String chatmsg_content;
}
