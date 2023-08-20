package com.example.urdc.preparation.classes;

public class RotateString {
public void rotateString(String str ,int t) {
	String ans= str.substring(t)+str.substring(0,t);
	System.out.println(ans);
	
	
}
public static void main(String[] args) {
	RotateString string =new RotateString();
	String str="nagsai";
int	t=4;
string.rotateString(str, t);

	
}
}
