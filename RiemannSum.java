
import java.util.Stack;
import java.util.Scanner;
public class RiemannSum {

	public static double sum(String expression,int start, int end, int n) {
	double result = 0.0;
		double deltaX = (end-start)/(double)n;
		for(int i= 1; i <= n; i++) {

	result += solve(expression,((double)start+(deltaX*i)));
		}

		result *= deltaX;





		return result;
	}

	// solving some expression given an input for x
	public static double solve(String expression, double input) {

		// x^2-5*x+3 input 2
	double result = 0.0;
		String e[] = spaced(expression).split("[\\s]+");

		
		Stack<Character> opStack = new Stack<Character>();
		Stack<Double> numStack = new Stack<Double>();
		

		for(String l: e) {

			// condition for exponentiation
	if(l.indexOf("^") != -1) {
			if(l.charAt(0) == 'x') {
			result = 1.0;
			for(int i = 0; i <Integer.parseInt(l.substring(l.indexOf("^")+1).trim()); i++) {
			result *= input;
			}
		}

		else {
			result = 1.0;
			double temp = Double.parseDouble(l.substring(0,l.indexOf("^")));
			for(int i = 0; i <Integer.parseInt(l.substring(l.indexOf("^")+1)); i++) {

			result *= temp;
			}
			
		}
			numStack.push(result);
	}


	// condition for operator
	else if(l.charAt(0) == '+' || l.charAt(0) == '-') {

		while(!opStack.empty()) {
			evaluate(numStack,opStack);
		}
			opStack.push(l.charAt(0));
	}

	else if(l.charAt(0) == '*' || l.charAt(0) == '/') {
		if(!opStack.empty()) {
		while(opStack.peek() == '*' || opStack.peek() == '/') {
			evaluate(numStack,opStack);
		}
		}
		opStack.push(l.charAt(0));
	}
	else if(l.charAt(0) == 'x')
		numStack.push((double)input);
		// condition for number
	else {
		numStack.push(Double.parseDouble(l));
	}
		}
		while(!opStack.empty())
			evaluate(numStack, opStack);
	
		


		return numStack.pop();
	}
	

	public static void evaluate(Stack<Double> n, Stack<Character> o) {
	double temp = 0.0;
	char c = o.pop();
		switch(c) {
			case '+':
				temp = n.pop()+n.pop();
				break;
			case '-':
				double t1 = n.pop();
				temp = n.pop()-t1;
				break;
			case '/':
				double t2 = n.pop();
				temp = n.pop()/t2;
				break;
			case '*':
				temp = n.pop()*n.pop();
				break;
		}


		n.push(temp);
	}

	// to put whitespace between operators
	public static String spaced(String s) {
		String a = "";
		for(int i = 0; i < s.length(); i++) {
			switch(s.charAt(i)) {
				case '+':
				case '-':
				case '*':
				case '/':
						
					a+= " " + s.charAt(i) + " ";
						break;
				default:
					a+=s.charAt(i) + "";
			}
			}

	return a;
	}
	
	

	public static void main(String args[]) {
		Scanner a = new Scanner(System.in);
		System.out.print("type in an expression: ");
		
		String e = a.nextLine();

		System.out.println();
		System.out.print("type in the start point: ");

		int b = a.nextInt();
		System.out.print("type in the endpoint: ");
		int c = a.nextInt();
		System.out.println();
		System.out.print("type in the number of rectangles: ");
		int d = a.nextInt();
		System.out.print("the area under this curve is: ");
	System.out.println((sum(e, b, c, d)));
	}



}
