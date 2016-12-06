package master;

/**
 * DSA Project - Prevents item stock from going below zero.
 * @author Meriel Stein
 * @version 12.02.2016
 */

public class ItemException extends RuntimeException{

	public ItemException(String s){
		super(s);
	}
}
