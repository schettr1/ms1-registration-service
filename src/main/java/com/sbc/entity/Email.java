package com.sbc.entity;

import java.io.Serializable;

public class Email implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String efrom;
	private String eto;
	private String emessage;
	
	public Email() {
	}
	
	public Email(String efrom, String eto, String emessage) {
		this.efrom = efrom;
		this.eto = eto;
		this.emessage = emessage;
	}

	public String getEfrom() {
		return efrom;
	}

	public void setEfrom(String efrom) {
		this.efrom = efrom;
	}

	public String getEto() {
		return eto;
	}

	public void setEto(String eto) {
		this.eto = eto;
	}

	public String getEmessage() {
		return emessage;
	}

	public void setEmessage(String emessage) {
		this.emessage = emessage;
	}
	
}
