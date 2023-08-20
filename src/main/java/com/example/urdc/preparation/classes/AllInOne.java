package com.example.urdc.preparation.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;


public class AllInOne  {

	public void removeDuplicatesInString(String string,int value) {
		HashSet<Character> hash=new LinkedHashSet<>(value-1);
		char chr[]=string.toCharArray();
		for (char c : chr) {
			hash.add(c);
		}
		for (Character chars : hash) {
			System.out.println(chars);
		}
	}
	
	public String removeDuplicateStrings(List<String> list)
	{
		HashSet<String> hash=new LinkedHashSet<>();
		for (String string : list) {
		hash.add(string);	
		}
		for (String string : hash) {
			System.out.println(string);
			hash.add(string);
		}
		return hash.toString();		
	
	}
	
	public static int largestNumberInArray(int values[]) {
		int max=values[0];
		
		for (int i = 0; i < values.length; i++) {
			if(max<values[i]) {
				max=values[i];
				
			}
		}
		System.out.println(max);
		return max;
		
	}
	
	public void reverseString(String input) 
	{
		String inString=input.toUpperCase();
		StringBuilder build=new StringBuilder();
		build.append(inString);
		System.out.println(build.reverse());
	}
	
	public static String rotateString(String inString, int index) {
		String outString =inString.substring(index)+inString.substring(0,index).toUpperCase();
		return outString;
		
	}
	 
	public static String leftRotate(String inString, int index) {
		String outString=rotateString(inString, inString.length()-index);
		String upperCase=outString.toUpperCase();
		return upperCase;
	}
	public void stringContentPoolex() {
		String str1="ravi";
		String str2= "ravi";
		if(str1==str2) {
			System.out.println("The location of String str1  "+System.identityHashCode(str1));
			System.out.println("The location of String str2  "+System.identityHashCode(str2));
			System.out.println("the location is same");
			}
		else {
			System.out.println("The location of String str1  "+System.identityHashCode(str1));
			System.out.println("The location of String str2  "+System.identityHashCode(str2));
			System.out.println("the location is diffrent");
		}
	}
	
	public void swapNumbers(int a,int b)
	{
		System.out.println("Before Swapping");
		System.out.println(a);
		System.out.println(b);
		
		System.out.println("after swapping");
		a=a+b;
		b=a-b;
		a=a-b;
		System.out.println(a);
		System.out.println(b);
	}
	
	public static boolean findVowels(String inString) {
		boolean outString=inString.matches(".*[aeiou].*");
		System.out.println(outString);
		return outString;
		
	}
	static int inputNumber;
	static Scanner sc =new Scanner(System.in);
	public  static boolean findPrimeNum() throws Exception {
		System.out.println("Enter input value");
		inputNumber =sc.nextInt();
		for (int i = 2; i < inputNumber; i++) {
			if(inputNumber%i==0) {
				throw new Exception(inputNumber+" IS NOT A PRIME NUMBER ");
			}
		}
		System.out.println(inputNumber+" IS A PRIME NUMBER ");
		return true;
		
	}
	int runTime;
	public void fibinnoicSeries(int a ,int b,int c) {
		System.out.println("Enter the value to run the logic");
		runTime=sc.nextInt();
		for (int i = 0; i < runTime ; i++) {
			System.out.print(" " +a);
			a=b;
			b=c;
			c=a+b;
			
		}
	}
	
	public void RightStarPattern(int value) {
		for (int i = 0; i < value; i++) {
			for (int j = i ; j < value ; j++) {
			System.out.print(" *");
				
			}
			System.out.println();
		}
	}
	
	public void leftPattern(int value)
	{
		for (int i = 0; i < value; i++) {
			for (int j = i; j < value; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	
	public void piramidPattern(int value) {
		for (int i = 0; i < value; i++) {
			for (int j = value; j >i; j--) {
				System.out.print(" ");
				}
			for (int j = 0; j <(i*2)-1; j++) {
				System.out.print("*");

			}
			System.out.println();
			
		}
		for (int i = value-1; i >0; i--) 
			
		 {
			for (int j = value; j >i; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < (i*2)-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
	public static String removeWhiteSpaces(String input) {
		StringBuilder out=new  StringBuilder();
		char []inStr=input.toUpperCase().toCharArray();
		for (char c : inStr) {
			if(!Character.isWhitespace(c)) {
				out.append(c);
			}
		}
		System.out.println(out.toString());
		return out.toString();
	}
	
	public static boolean compareTwoArrayValues(int array[],int array2[]){
		HashSet<Object> hash=new LinkedHashSet<>();
		hash.add(array);
		HashSet<Object> hash2=new LinkedHashSet<>();
		hash2.add(array2);
		if(hash.size()!=hash2.size()) {
			return false;
		}
		for (Object object : hash2) {
			if(!hash.contains(object)) {
				return false;
			}
		}
		return true;
	}
	
	public static int secondHighestNum(int ary[]) {
		int highNum=Integer.MIN_VALUE;
		int secondHighNum=Integer.MIN_VALUE;
		for (int i : ary)  {
			if(i>highNum) {
				secondHighNum=highNum;
				highNum=i;
			}
			else if(i>secondHighNum) {
				secondHighNum=i;
			}
		}
		System.out.println(secondHighNum);
		return secondHighNum;
	}
	
	public void sumOfNumInArray() {
		int sum = 0;
		int ary[]= {1,2,3,4,55,66,77,88};
		for (int i : ary) {
			sum+=i;
		}
		System.out.println(sum);
	}
	public void sort() {
		List<String> list=new ArrayList<String>();
		list.add("a");list.add("b");list.add("c");list.add("d");list.add("e");list.add("f");list.add("g");list.add("h");list.add("i");list.add("j");list.add("k");
		Collections.sort(list,Collections.reverseOrder());
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}
	public void compareTwoSTringArrays() {
		List<Integer> list=  new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,8,9,9133));
		List<Integer> list2= new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,8,9,1,2,3,4,5,6,8,9,44,56,666,5444,2222,3333,4444));
		list2.addAll(list);
		System.out.println(list2);
		HashSet<Integer> hash=new LinkedHashSet<>();
		HashSet<Integer> hash2=new LinkedHashSet<>();
			for (Integer integer : list2) {
				hash.add(integer);
			 }
			for (Integer integer : list) {
				hash2.add(integer);
			}
			System.out.println(hash);
			
			hash.retainAll(hash2);
			System.out.println(hash);
	}
	
	public List<Integer> listretrn() {
		List<Integer> ints = new ArrayList(Arrays.asList(3,4,2,5,6,7,4,5));
		List<Integer> odds=new ArrayList<>();
		List<Integer> evens=new ArrayList<>();
		for (int i : ints) {
		if(i%2==0) {
			evens.add(i);
			return evens;
		}
		
		if(i%2==1) {
			odds.add(i);
			return odds;
		}
		}
		for (Integer integer : odds) {
	System.out.println(integer);		
return odds;			
		}
		for (Integer integer : evens) {
			System.out.println(integer);
		}
		System.out.println(evens);
		return evens;
	}
	
	public void leftTriangle(int value) {
		for (int i = 0; i < value; i++) {
			for (int j= 2*(value-i); j >0; j--) {
		 		System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		AllInOne allInOne=new AllInOne();
//		String input=" vi k a l ";
//		int value=input.length();
		int index=2;
		String in="bajypnd";
//
//		int swaIntA=6;
//		int swapIntB=2;
//		List< String> list =new ArrayList<>();
//		list.add("harsha");
//		list.add("harsha");
//		list.add("vinod");
//		list.add("vikram");
		int values[] = {11,222,444,65,45};
		int[] inputValues= {11,4,222,3444,568765,454656,768};
		System.out.println(compareTwoArrayValues(values, inputValues));
//
		allInOne.piramidPattern(9);
//		allInOne.fibinnoicSeries(1, 2, 3);
//		allInOne.removeDuplicatesInString(input, value);
//		allInOne.removeDuplicateStrings(list);
//		allInOne.reverseString(input);
//		rotateString(in, index);
//		leftRotate(in, index);
////		allInOne.swapNumbers(swaIntA, swapIntB);
//		findVowels(in);
//		findPrimeNum();
//		removeWhiteSpaces(input);
		largestNumberInArray(values);
//		secondHighestNum(inputValues);
//		allInOne.stringContentPoolex();
//		allInOne.sumOfNumInArray();
		allInOne.compareTwoSTringArrays();
//		allInOne.listretrn();
		allInOne.leftTriangle(9);
		allInOne.sort();
		}	
}
