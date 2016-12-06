package master;

/**
 * DSA Project - Prevents item stock from going below zero.
 * @author Meriel Stein
 * @version 12.02.2016
 */

@SuppressWarnings("serial")
public class ItemException extends RuntimeException{

	/**
	 * Constructor for ItemException
	 * @param s the error message for this exception
	 */
	public ItemException(String s){
		super(s);
	}
}
