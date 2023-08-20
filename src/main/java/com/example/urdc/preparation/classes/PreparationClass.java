package com.example.urdc.preparation.classes;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class PreparationClass {

	
	
	public void removeDuplicates(char[] str,int n) {
		HashSet<Character> set=new LinkedHashSet<>(n-1);
		for (char c : str) {
			set.add(c);
		}
		for (Character character : set) {
			System.out.println(character);
		}
		
	}
	
	
	public static void main(String[] args) {
		char[] str ="gggajhsh".toCharArray();
		int t=str.length;
		PreparationClass prepClass=new PreparationClass();
		prepClass.removeDuplicates(str, t);
	}
}
