package com.example.urdc.preparation.classes;


public class Patterns {
	public void rightPattern(int value) {
		for (int i = 0; i < value; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(" *");
			}
			System.out.println();
		}
	}
	public void reversePattern(int value) {
		for (int i = 0; i < value; i++) {
			for (int j = i; j <value; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
	}
	
	public void pyramid(int value) {
		for (int i = 0; i < value; i++) {
			for (int j = value; j >i; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < (i*2)-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for (int i = value-1; i >0; i--) {
			for (int j = value; j >i; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < (i*2)-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
public static void main(String[] args) {
		
	Patterns pat=new Patterns();
	int value = 8;
	pat.rightPattern(value);
	pat.reversePattern(value);
	pat.pyramid(value);
}
}