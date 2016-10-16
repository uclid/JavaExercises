package turing;


/**
 * A class for experimenting with expression trees.  This class includes 
 * a nested abstract class and several subclasses that represent nodes in
 * an expression tree.  It also includes several methods that work with these
 * classes.
 */
public class Expressions {
	
	/**
	 * The main routine tests some of the things that are defined in this class.
	 */
	public static void main(String[] args) {
		System.out.println("Testing expression creation and evaluation...\n");
		ExpNode e1 = new BinOpNode('+', new VariableNode(), new ConstNode(3));
		ExpNode e2 = new BinOpNode('*', new ConstNode(2), new VariableNode());
		ExpNode e3 = new BinOpNode('-', e1, e2);
		ExpNode e4 = new BinOpNode('/', e1, new ConstNode(-3));
		System.out.println("For x = 3:");
		System.out.println("   " + e1 + " = " + e1.value(3));
		System.out.println("   " + e2 + " = " + e2.value(3));
		System.out.println("   " + e3 + " = " + e3.value(3));
		System.out.println("   " + e4 + " = " + e4.value(3));
		System.out.println("\nTesting copying...");
		System.out.println("   copy of " + e1 + " gives " + copy(e1));
		System.out.println("   copy of " + e2 + " gives " + copy(e2));
		System.out.println("   copy of " + e3 + " gives " + copy(e3));
		System.out.println("   copy of " + e4 + " gives " + copy(e4));
		ExpNode e3copy = copy(e3);  // make a copy of e3, where e3.left is e1
		((BinOpNode)e1).left = new ConstNode(17);  // make a modification to e1
		System.out.println("   modified e3: " + e3 + "; copy should be unmodified: " + e3copy);
		System.out.println("\nChecking test data...");
		double[][] dt = makeTestData();
		for (int i = 0; i < dt.length; i++) {
			System.out.println("   x = " + dt[i][0] + "; y = " + dt[i][1]);
		}
	}
	
	/**
	 * Given an ExpNode that is the root of an expression tree, this method
	 * makes a full copy of the tree.  The tree that is returned is constructed
	 * entirely of freshly made nodes.  (That is, there are no pointers back
	 * into the old copy.)
	 */
	static ExpNode copy(ExpNode root) {
		if (root instanceof ConstNode)
			return new ConstNode(((ConstNode)root).number);
		else if (root instanceof VariableNode)
			return new VariableNode();
		else {
			BinOpNode node = (BinOpNode)root;
			// Note that left and right operand trees have to be COPIED, 
			// not just referenced.
			return new BinOpNode(node.op, copy(node.left), copy(node.right));
		}
	}
	
	/**
	 * Returns an n-by-2 array containing sample input/output pairs. If the
	 * return value is called data, then data[i][0] is the i-th input (or x)
	 * value and data[i][1] is the corresponding output (or y) value.
	 * (This method is currently unused, except to test it.)
	 */
	static double[][] makeTestData() {
		double[][] data = new double[21][2];
		double xmax = 5;
		double xmin = -5;
		double dx = (xmax - xmin) / (data.length - 1);
		for (int i = 0; i < data.length; i++) {
			double x = xmin + dx * i;
			double y = 2.5*x*x*x - x*x/3 + 3*x;
			data[i][0] = x;
			data[i][1] = y;
		}
		return data;
	}
	
	
	//------------------- Definitions of Expression node classes ---------
	
	/**
	 * An abstract class that represents a general node in an expression
	 * tree.  Every such node must be able to compute its own value at
	 * a given input value, x.  Also, nodes should override the standard
	 * toString() method to return a fully parameterized string representation
	 * of the expression.
	 */
	static abstract class ExpNode {
		abstract double value(double x);
		// toString method should also be defined in each subclass
	}
	
	/**
	 * A node in an expression tree that represents a constant numerical value.
	 */
	static class ConstNode extends ExpNode {
		double number;  // the number in this node.
		ConstNode(double number) {
			this.number = number;
		}
		double value(double x) {
			return number;
		}
		public String toString() {
			if (number < 0)
				return "(" + number + ")"; // add parentheses around negative number
			else
				return "" + number;  // just convert the number to a string
		}
	}
	
	/**
	 * A node in an expression tree that represents the variable x.
	 */
	static class VariableNode extends ExpNode {
		VariableNode() {
		}
		double value(double x) {
			return x;
		}
		public String toString() {
			return "x";
		}
	}
	
	/**
	 * A node in an expression tree that represents one of the
	 * binary operators +, -, *, or /.
	 */
	static class BinOpNode extends ExpNode {
		char op;  // the operator, which must be '+', '-', '*', or '/'
		ExpNode left, right;  // the expression trees for the left and right operands.
		BinOpNode(char op, ExpNode left, ExpNode right) {
			if (op != '+' && op != '-' && op != '*' && op != '/')
				throw new IllegalArgumentException("'" + op + "' is not a legal operator.");
			this.op = op;
			this.left = left;
			this.right = right;
		}
		double value(double x) {
			double a = left.value(x);  // value of the left operand expression tree
			double b = right.value(x); // value of the right operand expression tree
			switch (op) {
			case '+': return a + b;
			case '-': return a - b;
			case '*': return a * b;
			default:  return a / b;
			}
		}
		public String toString() {
			return "(" + left.toString() + op + right.toString() + ")";
		}
	}

}
