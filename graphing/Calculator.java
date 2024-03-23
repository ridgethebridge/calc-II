
/*
 * fix some of the exponent workings
 * doesnt work fully with parenthesis
 * add functionality for negative numbers, fix the spacing method
 *
 *
 *write a simple program that recognzies negative numbers
 *
 *
 */

import java.util.Stack;
import java.util.Scanner;
public class Calculator {

	//calculates string expressions and returns double value
public static double calculate(String string) {

	Stack<Character> opStack = new Stack<Character>();
	Stack<Double> numStack = new Stack<Double>();
	Stack<Double> eStack = new Stack<Double>();

	String temp[] = space(string).split("\\s+");

	for(String s: temp) {
	
		char c = s.charAt(0);
		switch(c) {
			case '+':
				while(!opStack.isEmpty() && opStack.peek() !='(') {

					compute(opStack,numStack);
				}
					opStack.push(c);
				continue;
			case '-':
				if(s.length() > 1) {
					numStack.push(Double.parseDouble(s));
					continue;
					
				}
				else {
				while(!opStack.isEmpty() && opStack.peek() !='(') {
					compute(opStack,numStack);
				}
				
					opStack.push(c);
				
				continue;
				}
			case '*':
				while(!opStack.isEmpty() && (opStack.peek() == '*' || opStack.peek() == '/' || opStack.peek() == '^') && opStack.peek() !='(') {
					compute(opStack,numStack);
				}
					opStack.push(c);
				continue;
			case '/':
				while(!opStack.isEmpty() && (opStack.peek() == '*' || opStack.peek() == '/' || opStack.peek() == '^') && opStack.peek() !='(') {
					compute(opStack,numStack);
				}
					opStack.push(c);
				continue;

			case '(':
				opStack.push(c);
				continue;

			case ')':
				while(!opStack.isEmpty() && opStack.peek() != '(') {
					compute(opStack,numStack);
				}
				opStack.pop();

				
				continue;
			case '^':
				opStack.push(c);
				continue;
		}

	
		

		// 5 + 9^(2+2) - 6 * 4
		// 5 + 9^4 - 6 * 4

		numStack.push(Double.parseDouble(s));
	}

				// do stuff for numbers
		
	while(!opStack.isEmpty())
		compute(opStack,numStack);

		return numStack.pop();

}

// helper method for doing quick computations
private static void compute(Stack<Character> op, Stack<Double> val) {

	char sign = op.pop();
	double d1 = val.pop();
	double d2 = val.pop();
	switch(sign) {
		 	case '+':
			val.push(d2+d1);	
				break;
			case '-':
				val.push(d2-d1);
				break;
			case '*':
				val.push(d2*d1);
				break;
			case '/':
				val.push(d2/d1);
				
				break;
			case '^':
				val.push(Math.pow(d2,d1));
						break;
	}
}


//method for spacing out expression, helps as the delimeter is whitespade

private static String space(String e) {

	String result = "";
	// gets rid of double negs
	String temp = e.replaceAll("\\s+","").replaceAll("--","\\+");

	// only pluses now
	while(temp.indexOf("++") !=-1) 
		temp = temp.replaceAll("\\+\\+","\\+");


	// -5 - -5
	for(int i = 0; i < temp.length(); i++) {
		char c = temp.charAt(i);
		switch(c) {
			case '-':
				if(i == 0 || (temp.charAt(i-1) == '+' || temp.charAt(i-1) == '*' || temp.charAt(i-1) == '/' || temp.charAt(i-1) == '^')) {
					result+=c+"";
				continue;
				}

			case '+':
			case '*':
			case '/':
			case '(':
			case ')':
			case '^':
				result += " " + c + " ";
				continue;
	}
	result += c +"";
}
System.out.println(result);
	return result;
}

// computes expression with variable x and input
public static double calculate(String string, double input) {
	
return calculate(string.replaceAll("x",input+""));

}

// main!!!!!
public static void main(String args[]) {
		while(true) {
	try {
		Scanner scan = new Scanner(System.in);
		String e = scan.nextLine();
	System.out.print(calculate(e));
	break;
	}

	catch(StringIndexOutOfBoundsException e) {
		System.out.println("type something in!");
		continue;
	}

	catch(NumberFormatException n) {
		System.out.println("must type in a valid expression with numbers");
		continue;
	}
		}
	
}
}
