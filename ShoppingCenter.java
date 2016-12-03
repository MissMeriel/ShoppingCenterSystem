package master;

/**
 * DSA Project ShoppingCenter
 * 
 * @author Meriel, Jon Spratt
 * @version 11.29.2016
 */

public class ShoppingCenter {

	/**
	 * A collection to store the regular checkout lines in the shopping center
	 */
	protected ListRAB<CheckoutLine> lines;
	/**
	 * The express line for this shopping center
	 */
	protected ExpressLine eLine;
	/**
	 * The inventory for the shopping center
	 */
	protected AscendingOrderList<Item, String> inventory;
	/**
	 * The customers currently in the shopping center
	 */
	protected AscendingOrderList<Customer, String> customers;

	/**
	 * default constructor
	 */
	public ShoppingCenter() {
		lines = new ListRAB<CheckoutLine>();
		customers = new AscendingOrderList<Customer, String>();
		inventory = new AscendingOrderList<Item, String>();
	}

	/**
	 * Constructor for a shopping center that already has an inventory
	 * 
	 * @param inventory
	 *            the inventory to set for the shopping center
	 */
	public ShoppingCenter(AscendingOrderList<Item, String> inventory) {
		lines = new ListRAB<CheckoutLine>();
		this.inventory = inventory;
		customers = new AscendingOrderList<Customer, String>();
	}

	/**
	 * Standard Setter - for the collection of checkout lines in the shopping center
	 * @return ListRAB<CheckoutLine> collection of checkout lines in the shopping
	 */
	public ListRAB<CheckoutLine> getCheckoutLines(){
		return lines;
	}
	
	/**
	 * Standard Mutator - for the collection of checkout lines in the shopping
	 * center
	 * 
	 * @param lines
	 *            the collection of checkout lines to set
	 */
	public void setCheckoutLines(ListRAB<CheckoutLine> lines) { // DO WE NEED
																// THIS METHOD?
		this.lines = lines;
	}

	/**
	 * Standard Accessor - for the inventory of the shopping center
	 * 
	 * @return returns the inventory of the shopping center
	 */
	public AscendingOrderList<Item, String> getInventory() {
		return inventory;
	}

	/**
	 * Standard Mutator - for the inventory of the shopping center
	 * 
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(AscendingOrderList<Item, String> inventory) {
		this.inventory = inventory;
	}

	/**
	 * Standard Accessor - for the collection of customers in the shopping
	 * center
	 * 
	 * @return returns the collection of customers
	 */
	public AscendingOrderList<Customer, String> getCustomers() {
		return customers;
	}

	/**
	 * Standard Mutator - for the collection of customers in the shopping center
	 * 
	 * @param customers
	 *            the collection of customers to set
	 */
	public void setCustomers(AscendingOrderList<Customer, String> customers) { // DO
																				// WE
																				// NEED
																				// THIS
																				// METHOD?
		this.customers = customers;
	}

	/**
	 * Adds customer to the end of the AOSL of customers shopping
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		if (customers.isEmpty() || customers.search(customer.getName()) < 0) {
			customers.add(customer);
		} else {
			throw new ListIndexOutOfBoundsException("Customer "
					+ customer.getName()
					+ " is already in the Shopping Center!");
		}
	}

	public Customer getCustomer(String cust_name) {
		try {
			System.out.println("in SC getCustomer, customers.search(cust_name)-1: "+(customers.search(cust_name)-1));
			return customers.get(customers.search(cust_name)-1);
		} catch (ListIndexOutOfBoundsException e) {
			throw new ListIndexOutOfBoundsException("Customer " + cust_name
					+ " not found.");
		}
	}

	public int adjustStock(String item_name, int i) {
		try {
			Item item = inventory.get(inventory.search(item_name)-1);
			item.adjustCurrentStock(i);
			return item.getCurrentStock();
		} catch (ListIndexOutOfBoundsException e) {
			throw new ListIndexOutOfBoundsException("");
		}
	}

	/**
	 * 
	 * @param i
	 *            index of inventory item
	 * @return Item at index i
	 */
	public Item getInventoryItem(int i) {
		return inventory.get(i);
	}

	/**
	 * Throws ListIndexOutOfBoundsException if item is not present in Shopping
	 * Center inventory.
	 * 
	 * @return item if extant in inventory
	 */
	public Item getInventoryItem(String item) {
		try {
			return inventory.get(inventory.search(item)-1);
		} catch (ListIndexOutOfBoundsException e) {
			throw new ListIndexOutOfBoundsException("Item " + item
					+ " is not in the shopping center.");
		}
	}

	/**
	 * 
	 * @return number of unique items in inventory
	 */
	public int getInventorySize() {
		return inventory.size();
	}

	/**
	 * print contents of checkout lines
	 */
	public void printCheckoutLines() {
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i).toString());
		}
	}

	/**
	 * print customers currently shopping.
	 */
	public void printCustomers() {
		System.out.println("The following " + customers.size()
				+ " customers are in the Shopping Center:");
		System.out.print(customers.toString());
	}

	/**
	 * increment the time shopping of all customers by one minute.
	 */
	public void incrementTime() {
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);
			c.incrementShoppingTime();
		}
	}

	/**
	 * When the customer finishes shopping (s)he gets into one of the three
	 * checkout lines. If the customer has <=5 items, (s)he can use the express
	 * checkout line. Otherwise (s)he chooses the shortest regular line. If the
	 * express line is twice as long as a regular line a customer with <=5 items
	 * will choose the shortest regular line for checkout instead.
	 * 
	 * @param customer
	 */
	public CheckoutLine checkoutCustomer(Customer customer) {
		CheckoutLine lowLine = new CheckoutLine();
		CheckoutLine highLine = new CheckoutLine();
		ExpressLine expressLine = new ExpressLine(null);
		for (int i = 0; i < 3; i++) {
			if (lines.get(i) instanceof ExpressLine) {
				expressLine = (ExpressLine) lines.get(i);
			} else {
				if (lines.get(i).getLength() > highLine.getLength()) {
					highLine = lines.get(i);
				} else {
					lowLine = lines.get(i);
				}
			}
		}
		if (customer.getCart() < 6
				&& expressLine.getLength() < 2 * lowLine.getLength()) {
			expressLine.addCustomer(customer);
			return expressLine;
		} else {
			lowLine.addCustomer(customer);
			return lowLine;
		}
	}

}
