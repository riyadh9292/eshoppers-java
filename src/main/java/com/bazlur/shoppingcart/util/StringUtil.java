package com.bazlur.shoppingcart.util;

public class StringUtil {
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public  static boolean isEmpty(String value) {
		// TODO Auto-generated method stub
		return value==null || value.length()==0;
	}

}
