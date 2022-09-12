package net.softsociety.issho.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {

	private String chatroom_seq;
	private String chatroom_name;
}
