/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

import static java.lang.String.valueOf;
import java.util.logging.Logger;

/**
 *
 * @author SHELDON
 */
public class Node implements Evaluable {

	private boolean isNumber;
	private int value;
	private char operator;
	private Node left;
	private Node right;

	/** Construct number node
     * @param value */
	public Node(int value) {
		this.isNumber = true;
		this.value = value;
	}

	/** Construc operator node
     * @param symbol */
	public Node(char symbol) {
		this.isNumber = false;
		this.operator = symbol;
	}

    /**
     *
     * @return
     */
    public boolean hasLeft() {
		if (isNumber) {
			throw new IllegalStateException("Numbers dont have children");
		}
		return left != null;
	}

    /**
     *
     * @param left
     */
    public void setLeft(Node left) {
		if (isNumber) {
			throw new IllegalStateException("Numbers dont have children");
		}
		this.left = left;
	}

    /**
     *
     * @param right
     */
    public void setRight(Node right) {
		if (isNumber) {
			throw new IllegalStateException("Numbers dont have children");
		}
		this.right = right;
	}

	@Override
	public int evaluate() {
		if (isNumber) {
			return value;
		} else {
			switch (operator) {
				case '+':
					return left.evaluate() + right.evaluate();
				case '-':
					return left.evaluate() - right.evaluate();
				case '*':
					return left.evaluate() * right.evaluate();
				case '/':
					return left.evaluate() / right.evaluate();
				default:
					throw new IllegalStateException("Unknown operator: " + operator);
			}
		}
	}

	@Override
	public String representation() {
		if (isNumber) {
			return valueOf(value);
		} else {
			return "(" + left.representation() + " " + operator + " " + right.representation() + ")";
		}
	}
    private static final Logger LOG = Logger.getLogger(Node.class.getName());

}
