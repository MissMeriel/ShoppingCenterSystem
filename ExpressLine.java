package master1;

/**
 * DSA Project Express checkout line for customers with 5 items or fewer
 * @author Meriel Stein
 * @version 11.23.2016
 */

public class ExpressLine extends CheckoutLine {

	/**
	 * Constructor for an ExpressLine with a name
	 * @param name the name to set for an express line
	 */
	public ExpressLine(String name){
		super(name);
	}
	
	/**
	 * Adds customer to the end of the checkout queue if customer has 4 items or
	 * less in their shopping cart.
	 * 
	 * @param c customer lining up at the back of the checkout queue.
	 * @throws CheckoutLineException when a customer has more than the maximum items allowed in express line
	 */
	@Override
	public void addCustomer(Customer c) throws CheckoutLineException {
		if (c.getCart() <= 4) {
			line.enqueue(c);
		} else {
			throw new CheckoutLineException("Customer has more than 4 items in shopping cart");
		}
	}
}
