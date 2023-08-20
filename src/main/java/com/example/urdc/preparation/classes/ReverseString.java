package com.example.urdc.preparation.classes;

public class ReverseString {
	
public static String reverseString(String in) throws Exception {
	
	if(in==null || in.isEmpty()) {
		throw new Exception("The String value must not be null"+in);
	}

	StringBuilder out=new StringBuilder();
	
		out.append(in);
		
	
	out.reverse();
	return out.toString();
}

public static void main(String[] args) throws Exception {
	String str="";
	System.out.println(ReverseString.reverseString(str));
	
	
}
}
