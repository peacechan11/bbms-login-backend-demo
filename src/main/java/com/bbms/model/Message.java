package com.bbms.model;

import lombok.Data;

@Data
public class Message {
private String msg;

public Message(String msg) {
	super();
	this.msg = msg;
}

}
