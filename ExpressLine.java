package project_v1;

/**
 * DSA Project Express checkout line for customers with 5 items or fewer
 * @author Meriel
 * @version 11.23.2016
 */

public class ExpressLine extends CheckoutLine {

	public ExpressLine(String name){
		super(name);
	}
	
	/**
	 * Adds customer to the end of the checkout queue if customer has 5 items or
	 * less in their shopping cart.
	 * 
	 * @param c customer lining up at the back of the checkout queue.
	 * @throws ExpressLineException 
	 */
	@Override
	public void addCustomer(Customer c) throws CheckoutLineException {
		if (c.getCart() <= 5) {
			line.enqueue(c);
		} else {
			throw new CheckoutLineException("Customer has more than 5 items in shopping cart");
		}
	}

}
