/**
 * 
 */
package com.mnt.mobiletracking.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author venkateshp
 *
 */
public class Messages {
	
private List<Message> messages;

/**
 * @return the messages
 */
@XmlElement(name="message")
public List<Message> getMessages() {
	return messages;
}

/**
 * @param messages the messages to set
 */
public void setMessages(List<Message> messages) {
	this.messages = messages;
}


}
