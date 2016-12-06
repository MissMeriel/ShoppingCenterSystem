package master1;

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
	 * Standard Setter - for the collection of checkout lines in the shopping
	 * center
	 * 
	 * @return returns the collection of checkout lines in the shopping
	 */
	public ListRAB<CheckoutLine> getCheckoutLines() {
		return lines;
	}

	/**
	 * Standard Mutator - for the collection of checkout lines in the shopping
	 * center
	 * 
	 * @param lines
	 *            the collection of checkout lines to set
	 */
	public void setCheckoutLines(ListRAB<CheckoutLine> lines) {
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
	public void setCustomers(AscendingOrderList<Customer, String> customers) {
		this.customers = customers;
	}

	/**
	 * Adds customer to the end of the AOSL of customers shopping
	 * 
	 * @throws ListIndexOutOfBoundsException
	 *             if customer is already in the Shopping Center
	 * @param customer
	 *            the customer to add to the shopping center
	 */
	public void addCustomer(Customer customer) {
		if (linesContain(customer)) {
			throw new ListIndexOutOfBoundsException("Customer "
					+ customer.getName() + " is in a checkout line!");
		} else if (customers.search(customer.getName()) >= 0) {
			throw new ListIndexOutOfBoundsException("Customer "
					+ customer.getName()
					+ " is already in the Shopping Center!");
		} else {
			customers.add(customer);
		}
	}

	/**
	 * Finds a customer shopping in the ShoppingCenter
	 * 
	 * @param cust_name
	 *            String key associated with a Customer
	 * @return Customer associated with param cust_name in ShoppingCenter's
	 *         shopping customers
	 * @throws ListIndexOutOfBoundsException
	 *             if customer isn't shopping in shopping center
	 */
	public Customer getCustomer(String cust_name) {
		try {
			return customers.get(customers.search(cust_name));
		} catch (ListIndexOutOfBoundsException e) {
			throw new ListIndexOutOfBoundsException("Customer " + cust_name
					+ " not found.");
		}
	}

	/**
	 * Removes a Customer shopping in the ShoppingCenter
	 * 
	 * @param customer
	 */
	public void removeCustomer(Customer customer) {
		customers.remove(customers.search(customer.getName()));
	}

	/**
	 * Adjust stock up or down for a particular Item in inventory
	 * 
	 * @param item_name
	 *            String key associated with item to be adjusted
	 * @param i
	 *            amount to adjust stock by
	 * @return int representing newly adjusted stock of Item
	 * @throws ListIndexOutOfBoundsException
	 *             if there is no Item in the ShoppingCenter inventory
	 *             associated with String item_name
	 */
	public int adjustStock(String item_name, int i) {
		try {
			Item item = inventory.get(inventory.search(item_name));
			item.adjustCurrentStock(i);
			return item.getCurrentStock();
		} catch (ListIndexOutOfBoundsException e) {
			throw new ItemException("No " + item_name + " in stock.");
		}
	}

	/**
	 * Accessor for an item in the inventory specified by index
	 * @param i
	 *            index of inventory item
	 * @return Item at index i
	 */
	public Item getInventoryItem(int i) {
		return inventory.get(i);
	}

	/**
	 * Accessor for an item in the inventory specified by name
	 * 
	 * @throws ListIndexOutOfBoundsException
	 *             if item is not present in Shopping Center inventory.
	 * @return item if extant in inventory
	 * @param item
	 *            the item's name for retrieval from inventory
	 */
	public Item getInventoryItem(String item) {
		try {
			return inventory.get(inventory.search(item));
		} catch (ListIndexOutOfBoundsException e) {
			throw new ListIndexOutOfBoundsException("Item " + item
					+ " is not in the shopping center.");
		}
	}

	/**
	 * Standard Accessor - for the size of the inventory
	 * 
	 * @return number of unique items in inventory
	 */
	public int getInventorySize() {
		return inventory.size();
	}

	/**
	 * Determine whether or not the Shopping Center's checkout lines have a
	 * specific customer in waiting in them
	 * 
	 * @param customer
	 *            the customer to check the checkout lines for
	 * @return returns true if the customer was found in a checkout line, false
	 *         otherwise
	 */
	public boolean linesContain(Customer customer) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).contains(customer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Print contents of checkout lines
	 */
	public void printCheckoutLines() {
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i).toString());
		}
	}

	/**
	 * Print customers currently shopping.
	 */
	public void printCustomers() {
		if (customers.isEmpty()) {
			System.out.println("No customers are in the Shopping Center!");
		} else {
			System.out.println("The following " + customers.size()
					+ " customers are in the Shopping Center:");
			System.out.print(customers.toString());
		}
	}

	/**
	 * Print items at or below re-stocking threshold
	 */
	public void printRestockableItems() {
		System.out.println("Items at restocking level:");
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (item.getCurrentStock() <= item.getRestockThreshold()) {
				System.out.println("\t" + item.getName() + " with "
						+ item.getCurrentStock() + " items.");
			}
		}
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
	 * Determine whether or not the checkout lines are empty
	 * 
	 * @return returns true is all of the checkout lines are empty, false
	 *         otherwise
	 */
	public boolean linesEmpty() {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).getLength() > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * When the customer finishes shopping (s)he gets into one of the three
	 * checkout lines. If the customer has less than or equal to 4 items, (s)he
	 * can use the express checkout line. Otherwise (s)he chooses the shortest
	 * regular line. If the express line is twice as long as a regular line a
	 * customer with less than or equal to 4 items will choose the shortest
	 * regular line for checkout instead.
	 * 
	 * @param customer
	 *            the customer to checkout
	 * @return returns an updated checkout line after the customer has been
	 *         served
	 */
	public CheckoutLine checkoutCustomer(Customer customer) {
		CheckoutLine lowLine = new CheckoutLine();
		CheckoutLine highLine = new CheckoutLine();
		ExpressLine expressLine = new ExpressLine(null);
		for (int i = 0; i < lines.size(); i++) {
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
		if (customer.getCart() < 5
				&& expressLine.getLength() < 2 * lowLine.getLength()) {
			expressLine.addCustomer(customer);
			return expressLine;
		} else {
			lowLine.addCustomer(customer);
			return lowLine;
		}
	}
}
