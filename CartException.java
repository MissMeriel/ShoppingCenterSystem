package project_v3;

/** 
 * Runtime exception for trying to remove from an empty cart
 * @author Jon
 *
 */
public class CartException extends RuntimeException	{
	public CartException(String s) {
		super(s);
	}
}
