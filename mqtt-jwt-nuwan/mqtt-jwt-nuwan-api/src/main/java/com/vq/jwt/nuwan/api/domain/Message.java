/**
 * 
 */
package com.vq.jwt.nuwan.api.domain;

import java.io.Serializable;

/**
 * @author Nuwan Dissanayaka
 *
 */
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1801590645980630791L;
	
	private String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
