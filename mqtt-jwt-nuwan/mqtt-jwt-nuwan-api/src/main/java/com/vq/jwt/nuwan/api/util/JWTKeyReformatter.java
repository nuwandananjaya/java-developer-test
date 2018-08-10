/**
 * 
 */
package com.vq.jwt.nuwan.api.util;

/**
 * @author Nuwan Dissanayaka
 *
 */
public class JWTKeyReformatter {
	
	private static JWTKeyReformatter instance = new JWTKeyReformatter();
	
	private final String VOWEL_FORMATTER = "[aeiou,AEIOU]";
	private final String VOWEL_REPLACER = "*";
	private final String ALPABATIC_FORMATTER = "[a-z,A-Z]";
	private final String ALPABATIC_REPLACER = "#";
	
	private JWTKeyReformatter() {
		
	}
	
	public static JWTKeyReformatter getInstance() {
		
		if(null == instance) {
			synchronized(JWTKeyReformatter.class) {
				instance = new JWTKeyReformatter();
			}
		}
		return instance;
	}
	
	/**
	 * JWT String formatter  vowels with * ,
	 * 	other alphabetic characters with # ,
	 *  even numeric values by 1+ which are positioned in odd indexes,
	 *  odd numeric values by 2- which are positioned in even indexes
	 * @param val
	 * @return String
	 */
	public String formatter(String val) {
		val = val.replaceAll(VOWEL_FORMATTER, VOWEL_REPLACER );
		val = val.replaceAll(ALPABATIC_FORMATTER, ALPABATIC_REPLACER);
		
		StringBuilder stringToChnage = new StringBuilder(val);
		 for (int index=0; index< stringToChnage.length();index++){
		        if (index % 2 != 0 && Character.isDigit(stringToChnage.charAt(index))){
		        	int stringToNeu = Integer.parseInt(String.valueOf(stringToChnage.charAt(index)));
		        	if(stringToNeu % 2 == 0) {
			        	stringToNeu++;
			        	stringToChnage.replace(index, index+1, String.valueOf(stringToNeu));
		        	}
		        }else if(index % 2 == 0 && Character.isDigit(stringToChnage.charAt(index))) {

		        	int stringToNeu = Integer.parseInt(String.valueOf(stringToChnage.charAt(index)));
		        	if(stringToNeu % 2 != 0) {
		        		stringToNeu-=2;
			        	stringToChnage.replace(index, index+1, String.valueOf(stringToNeu));
		        	}
		        }
		     }
		 return String.valueOf(stringToChnage);
	}
	
	

}
