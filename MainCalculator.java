package HW10_calculator;

import java.util.Scanner;

public class MainCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your matemathical expression here:");
		String inputExpression = input.nextLine();
		Calculator c = new Calculator(inputExpression);
		System.out.println("The result is: " + c.calculate());
		input.close();
		
	}

}
