/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

import static java.lang.Integer.parseInt;
import java.util.Stack;
import java.util.logging.Logger;

/**
 *
 * @author kunal
 */
public class OnTheFlyRPNEquationBuilder implements RPNEquationBuilder {

	private final Stack<Node> stack = new Stack<>();

    /**
     *
     * @param token
     * @return
     */
    @Override
	public RPNEquationBuilder push(String token) {
		try {
			int value = parseInt(token);
			Node number = new Node(value);
			stack.push(number);
		} catch (NumberFormatException e) {
			if (token.length() == 1) {
				Node operator = new Node(token.charAt(0));
				if (stack.isEmpty()) {
					throw new IllegalStateException("Nothing left on the stack for operand");
				}
				Node right = stack.pop();
				if (stack.isEmpty()) {
					throw new IllegalStateException("Nothing left on the stack for operand");
				}
				Node left = stack.pop();
				operator.setLeft(left);
				operator.setRight(right);
				stack.push(operator);
			} else {
				throw new IllegalArgumentException("Dont understand token: " + token);
			}
		}

		return this;
	}

    /**
     *
     * @return
     */
    @Override
	public Evaluable build() {
		if (stack.size() != 1) {
			throw new IllegalStateException("More than one token left on the stack, unbalanced input.");
		}
		return stack.pop();
	}
    private static final Logger LOG = Logger.getLogger(OnTheFlyRPNEquationBuilder.class.getName());

}
