package master;

/**
 * DSA Project Normal checkout line
 * 
 * @author Meriel
 * @version 11.23.2016
 */

public class CheckoutLine {

	protected Queue<Customer> line;
	protected String name;

	/**
	 * default constructor
	 */
	public CheckoutLine() {
		line = new Queue<Customer>();
		name = "N/A (regular)";
	}

	public CheckoutLine(String name) {
		line = new Queue<Customer>();
		this.name = name;
	}

	/**
	 * Adds customer to the end of the checkout queue.
	 * 
	 * @param c
	 *            customer lining up at the back of the checkout queue.
	 */
	public void addCustomer(Customer c) {
		line.enqueue(c);
	}

	/**
	 * The next customer in the checkout line is removed from the line in order
	 * to be served.
	 * 
	 * @return the next customer in line to be served.
	 */
	public Customer serveCustomer() {
		return line.dequeue();
	}

	public int getLength() {
		return line.getLength();
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	// toString() needs to follow format:
	// Customer Eevee has 7 items in the shopping basket.
	// Customer Woobat has 6 items in the shopping basket.
	public String toString() {
		if (line.isEmpty()) {
			return "There are no customers in " + name + "!";
		} else {
			return "The following " + line.getLength() + " customers are in "
					+ name + ":\n" + line.toString();
		}
	}

}
