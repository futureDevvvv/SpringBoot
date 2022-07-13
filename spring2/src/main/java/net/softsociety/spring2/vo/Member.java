package net.softsociety.spring2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@NoArgsConstructor
@Getter
@Setter
@ToString*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	String id;
	String pw;
	String name;
	String address;
	
	public void Test() {
		System.out.println("추가 정의 메소드");
	}
}
