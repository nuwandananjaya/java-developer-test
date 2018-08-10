/**
 * 
 */
package com.vq.jwt.nuwan.api.util;

import java.security.Security;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author Nuwan Dissanayaka
 *
 */
public class BouncyCastleMD5HashGenerator {
	
	private static BouncyCastleMD5HashGenerator instance = new BouncyCastleMD5HashGenerator();
	
	private BouncyCastleMD5HashGenerator(){
		
	}
	
	public static BouncyCastleMD5HashGenerator getInstance() {
		
		if(null == instance) {
			synchronized (BouncyCastleMD5HashGenerator.class) {
				instance = new BouncyCastleMD5HashGenerator();
			}
		}
		
		return instance;
	}
	
	public String md5HashGenerator(Object value) {
		Security.addProvider(new BouncyCastleProvider());
		byte input[] = value.toString().getBytes();
		
		MD5Digest md5 = new MD5Digest();
		md5.update(input, 0, input.length);
	
		
		byte[] digest = new byte[md5.getDigestSize()];
		md5.doFinal(digest, 0);
	
		return new String(Hex.encode(digest));
	}
}
