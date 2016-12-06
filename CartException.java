package master1;

/** 
 * Runtime exception for trying to remove from an empty cart
 * @author Jon Spratt
 *
 */
@SuppressWarnings("serial")
public class CartException extends RuntimeException	{
	/**
	 * Constructor for CartException
	 * @param s the error message for this exception
	 */
	public CartException(String s) {
		super(s);
	}
}
