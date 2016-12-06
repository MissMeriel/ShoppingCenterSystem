package master1;

/**
 * Exception for catching a bad indexes given to ADT methods
 * @author Jon Spratt
 *
 */
@SuppressWarnings("serial")
public class ListIndexOutOfBoundsException extends IndexOutOfBoundsException
{
	/**
	 * Constructor for ListIndexOutOfBoundsException
	 * @param s the error message for this exception
	 */
	public ListIndexOutOfBoundsException(String s) {
		super(s);
	}  // end constructor
}  // end ListIndexOutOfBoundsException
