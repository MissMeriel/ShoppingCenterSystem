package project_v3;

/**
 * DSA Project ShoppingCenter
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
	public ShoppingCenter(){
		lines = new ListRAB<CheckoutLine>();		
		customers = new AscendingOrderList<Customer, String>();
		//inventory = new ListRAB<Item>();
		inventory = new AscendingOrderList<Item, String>();
	}
	
	/**
	 * Constructor for a shopping center that already has an inventory
	 * @param inventory the inventory to set for the shopping center
	 */
	public ShoppingCenter(AscendingOrderList<Item, String> inventory){
		lines = new ListRAB<CheckoutLine>();
		this.inventory = inventory;
		customers = new AscendingOrderList<Customer, String>();
	}

	/**
	 * Standard Mutator - for the collection of checkout lines in the shopping center
	 * @param lines the collection of checkout lines to set
	 */
	public void setCheckoutLines(ListRAB<CheckoutLine> lines){		// DO WE NEED THIS METHOD?
		this.lines = lines;
	}
	
	/**
	 * Standard Accessor - for the inventory of the shopping center
	 * @return returns the inventory of the shopping center
	 */
	public AscendingOrderList<Item, String> getInventory() {
		return inventory;
	}

	/**
	 * Standard Mutator - for the inventory of the shopping center
	 * @param inventory the inventory to set
	 */
	public void setInventory(AscendingOrderList<Item, String> inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Standard Accessor - for the collection of customers in the shopping center
	 * @return returns the collection of customers
	 */
	public AscendingOrderList<Customer, String> getCustomers() {
		return customers;
	}
	
	/**
	 * Standard Mutator - for the collection of customers in the shopping center
	 * @param customers the collection of customers to set
	 */
	public void setCustomers(AscendingOrderList<Customer, String> customers) {		// DO WE NEED THIS METHOD?
		this.customers = customers;
	}
	
	/**
	 * print contents of checkout lines
	 */
	public void printCheckoutLines(){
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i).toString());
		}
	}
	
}
