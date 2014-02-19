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
public class Calls {
	
	private List<Call> calls;
	@XmlElement(name="call")
	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	

}
