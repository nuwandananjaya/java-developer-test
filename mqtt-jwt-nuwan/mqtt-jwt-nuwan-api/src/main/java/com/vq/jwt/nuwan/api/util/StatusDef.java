/**
 * 
 */
package com.vq.jwt.nuwan.api.util;

/**
 * @author Nuwan Dissanayaka
 *
 */
public enum StatusDef {
	
	AUTHORIZED("Authorized"), 
	UNAUTHORIZED("Unauthorized"), 
	SERVER_ERROR("Server error");
	
	private String decscription;

    private StatusDef(String decscription) {
        this.decscription = decscription;
    }

    public String getDecscription() {
        return decscription;
    }


}
