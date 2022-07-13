package net.softsociety.spring2.vo;

import lombok.Data;

@Data
public class Person {

	String name;
	String telecom;
	String phonenum;

	public Person(String name, String telecom, String phonenum) {
		this.name = name;
		this.telecom = telecom;
		this.phonenum = phonenum;
	}
}
