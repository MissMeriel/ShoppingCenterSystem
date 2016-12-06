package master1;

/**
 * DSA Project CheckoutLine exception is a RuntimeException as compiler cannot
 * check for it ahead of time
 * 
 * @author Meriel Stein
 * @version 11.23.2016
 */

@SuppressWarnings("serial")
public class CheckoutLineException extends RuntimeException {
	/**
	 * Constructor for CheckoutLineException
	 * @param s the error message for this exception
	 */
	public CheckoutLineException(String s) {
		super(s);
	}
}
