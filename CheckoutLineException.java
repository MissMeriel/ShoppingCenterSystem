package project_v1;

/**
 * DSA Project CheckoutLine exception is a RuntimeException as compiler cannot
 * check for it ahead of time
 * 
 * @author Meriel
 * @version 11.23.2016
 */

public class CheckoutLineException extends RuntimeException {

	public CheckoutLineException(String s) {
		super(s);
	}
}
