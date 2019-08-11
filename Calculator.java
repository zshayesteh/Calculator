package HW10_calculator;

/**
 * 
 * this is the calculator class that can calculate any complicated expressions.
 * @author zahra shayesteh
 * @version v1.0
 *
 */
public class Calculator {
	private String input;
	private Stack<String> stack;
	private Stack<String> stk;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Calculator(String input) {
		setInput(input);
	}

	/**
	 * <h1>this is the main method of my calculator class that works properly with any type of numbers</h1>
	 * <br>
	 * for example Calculator(((10/2)+14)*2-3)=35
	 * @author zahra shayesteh
	 * @since v1.0
	 * @return String
	 * 
	 */
	
	public String calculate() {// main method of the class that return the final
		// result of an expression
		String expression = getInput();
		if (validateExpression()) {
			String postfixExp = makePostfixExpression(expression);
			return String.valueOf(parsePostfixExpression(postfixExp));
		} else
			return "input expression is not valid!";
	}

	public boolean validateExpression() {// if input expression contains
											// anything except digits,operators
											// & sin/cos or it contains unequal
											// pair of parentheses it will not
											// accepted
		String alphabet = ".0123456789()-+*/ ";
		String input = getInput();
		input = input.replace("cos", "");
		input = input.replace("sin", "");
		String[] inputA = input.split("");
		for (int i = 0; i < inputA.length; i++)
			if (!alphabet.contains(inputA[i].toLowerCase()))
				return false;
		int counterOpenBracete = 0;
		int counterCloseBracete = 0;
		for (int i = 0; i < inputA.length; i++)
			if (inputA[i].equals("("))
				counterOpenBracete++;
			else if (inputA[i].equals(")"))
				counterCloseBracete++;
		if (counterOpenBracete != counterCloseBracete)
			return false;
		return true;
	}

	/**
	 * 
	 * 
	 */
	public String makePostfixExpression(String expression) {// this method will
															// make the postfix
															// expression of
															// input expression
		stack = new Stack<String>();
		expression = expression.replace(" ", "");
		expression = " (" + expression + ") ";
		stack.push("(");
		String postfixExpression = "";
		String tempPrev;
		String tempNext;
		String ex[] = expression.split("");
		int i = 2;
		while (i < ex.length - 1) {
			tempPrev = ex[i - 1];
			tempNext = ex[i + 1];
			if ((identifyChar(ex[i]) == 5 | identifyChar(ex[i]) == 6) & (identifyChar(tempPrev) == 1
					| identifyChar(tempPrev) == 3 | identifyChar(tempPrev) == 4 | identifyChar(tempPrev) == 5
					| identifyChar(tempPrev) == 6 | identifyChar(tempPrev) == 7 | identifyChar(tempPrev) == 8)) {// -/+
				// sign
				postfixExpression = postfixExpression + " " + ex[i];
				i = i + 1;
			} else if (identifyChar(ex[i]) == 0)// digits
			{
				if (identifyChar(tempNext) == 0)
					postfixExpression = postfixExpression + (ex[i]);
				else
					postfixExpression = postfixExpression + (ex[i]) + " ";
				i = i + 1;
			} else if (identifyChar(ex[i]) == 3 | identifyChar(ex[i]) == 4)// sin/cos
			{
				stack.push(ex[i] + ex[i + 1] + ex[i + 2]);
				i = i + 3;
			} else if (identifyChar(ex[i]) == 5 | identifyChar(ex[i]) == 6 | identifyChar(ex[i]) == 7
					| identifyChar(ex[i]) == 8) { // plus/minus/multiply/division
				// operators
				while (priority(stack.getElementByPosition(stack.getTopOfStack())) >= priority(ex[i])) {
					if (!stack.getElementByPosition(stack.getTopOfStack()).equals("(")) {
						postfixExpression = postfixExpression + " " + stack.pop() + " ";
					}
				}
				stack.push(ex[i]);
				i = i + 1;
			} else if (identifyChar(ex[i]) == 1) // open parenthese
			{
				stack.push("(");
				i = i + 1;
			} else if (identifyChar(ex[i]) == 2)// close parentheses
			{
				while (!stack.getElementByPosition(stack.getTopOfStack()).equals("(")) {
					postfixExpression = postfixExpression + " " + stack.pop() + " ";
				}
				stack.pop();
				i = i + 1;
			} else
				i = i + 1;
		}
		return postfixExpression;
	}

	public int identifyChar(String s) {// identify type of each character
		String digits = ".0123456789";
		String signs = "+-";
		String parenthes = "()";
		String SC = "sc";
		String operators = "+-*/";
		String[] sa = s.split("");
		if (signs.contains(sa[0])) {
			for (int i = 0; i < sa.length - 1; i++)
				if (digits.contains(sa[i + 1]))
					return 9;// signed digits
		}
		if (digits.contains(sa[0]))
			return 0;// digit
		else if (parenthes.contains(s))
			if (s.equals("("))
				return 1;// open parentheses
			else
				return 2;// close parentheses
		else if (SC.contains(s.toLowerCase()) || s.toLowerCase().equals("cos") || s.toLowerCase().equals("sin"))
			if (s.toLowerCase().equals("c") || s.toLowerCase().equals("cos"))
				return 3;// cos
			else
				return 4;// sin
		else if (operators.contains(s))
			if (s.equals("+"))
				return 5;// plus
			else if (s.equals("-"))
				return 6;// minus
			else if (s.equals("*"))
				return 7;// multiplication
			else if (s.equals("/"))
				return 8;// division
		return -1;
	}

	public double parsePostfixExpression(String PFExpression) { // this method
																// will parse
																// the postfix
																// expression
																// and return
																// the result of
																// it
		stk = new Stack<String>();
		String[] pfe = PFExpression.split(" ");
		for (int i = 0; i < pfe.length; i++) {
			// String[] pfea = pfe[i].split("");
			if (pfe[i].equals("")) // empty
				continue;
			else if (identifyChar(pfe[i]) == 9 | identifyChar(
					pfe[i]) == 0) {/*
									 * integer digits & digits with signs will
									 * push into the stack
									 */
				stk.push(pfe[i]);
			} else if (identifyChar(pfe[i]) == 5 & !pfe[i].equals(""))// plus
			{
				double R = plus();
				stk.push(String.valueOf(R));
			} else if (identifyChar(pfe[i]) == 6 & !pfe[i].equals(""))// minus
			{
				double R = minus();
				stk.push(String.valueOf(R));
			} else if (identifyChar(pfe[i]) == 7 & !pfe[i].equals(""))// multiplication
			{
				double R = multiply();
				stk.push(String.valueOf(R));
			} else if (identifyChar(pfe[i]) == 8 & !pfe[i].equals(""))// division
			{
				double R = division();
				stk.push(String.valueOf(R));
			} else if (identifyChar(pfe[i]) == 3 & !pfe[i].equals(""))// cos
			{
				double R = cos();
				stk.push(String.valueOf(R));
			} else if (identifyChar(pfe[i]) == 4 & !pfe[i].equals(""))// sin
			{
				double R = sin();
				stk.push(String.valueOf(R));
			} else
				continue;
		}
		return Double.parseDouble(stk.pop());
	}

	public int priority(String s) {// define priorities of operators
		if (s.equals("+") | s.equals("-"))
			return 1;
		if (s.equals("*") | s.equals("/"))
			return 2;
		if (s.toLowerCase().equals("c") | s.toLowerCase().equals("s") | s.toLowerCase().equals("cos")
				| s.toLowerCase().equals("sin"))
			return 3;
		return 0;
	}

	public double plus() {// sum of 2 digits from top of stack
		double num2 = Double.parseDouble(stk.pop());
		double num1 = Double.parseDouble(stk.pop());
		return num1 + num2;
	}

	public double minus() {// minus of 2 digits from top of stack
		double num2 = Double.parseDouble(stk.pop());
		double num1 = Double.parseDouble(stk.pop());
		return num1 - num2;
	}

	public double multiply() {// multiply of 2 digits from top of stack
		double num2 = Double.parseDouble(stk.pop());
		double num1 = Double.parseDouble(stk.pop());
		return num1 * num2;
	}

	public double division() {// division of 2 digits from top of stack
		double num2 = Double.parseDouble(stk.pop());
		double num1 = Double.parseDouble(stk.pop());
		return num1 / num2;
	}

	public double sin()// sin of a digit from top of stack
	{
		double n = Double.parseDouble(stk.pop());
		return Math.sin(n);
	}

	public double cos()// cos of a digit from top of stack
	{
		double n = Double.parseDouble(stk.pop());
		return Math.cos(n);
	}
}
