package com.example.urdc.preparation.classes;


public class Practice {
public void rightTriangle(int value) {
	for (int i = 0; i < value; i++) {
		for (int j = 0; j < i; j++) {
			System.out.print("*");
		}
		System.out.println();
	}
}

public void revTriangle(int value) {
	for (int i = 0; i < value; i++) {
		for (int j = i; j < value; j++) {
			System.out.print("*");
		}
		System.out.println();
	}
}
public void piramid(int value) {
	 for (int i = 1; i <= value; i++) {
		 for (int j = value; j >i ; j--) {
			System.out.print(" ");
		 }
			 for (int h = 1; h <=(i*2)-1; h++) {
					System.out.print("*");
				}
			 System.out.println(); 
		}
	}

public void leftTriangle(int value) {
	for (int i = 0; i < value; i++) {
		for (int j =2*(value-i); j >=0; j--) {
			System.out.print(" ");
		}
		for (int j = 0; j <= i; j++) {
			System.out.print("* ");
		}
		System.out.println();
	}
}
public static void main(String[] args) {
	int value=6;
		Practice practice = new Practice();
		practice.rightTriangle(value);
		
		practice.piramid(value);
		practice.revTriangle(value);
		practice.leftTriangle(value);
	}

}
