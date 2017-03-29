package com.wcc.uitl;

/**
 *  工具不够用的时候，我们自己学会自己造工具，然后就可以直接将这个
 * @author WCC
 *
 */
public class MyFunction {
	
	public static boolean myfunction(String[] strings, String  str){
		if (strings == null || str.length() == 0) {
			return false;
		}
		for(String s : strings){
			if (s.equals(str)) {
				return true;
			}
		}
		return false;
	}

}
