package project_v1;

/**
 * DSA Project ShoppingCenter
 * @author Meriel
 * @version 11.23.2016
 */

public class ShoppingCenter {
	
	protected ListRAB<CheckoutLine> lines;
	protected ExpressLine eLine;
	protected ListRAB<Customer> customersShopping;
	protected ListRAB<Item> inventory;
	
	/**
	 * default constructor
	 */
	public ShoppingCenter(){
		lines = new ListRAB<CheckoutLine>();
		customersShopping = new ListRAB<Customer>();
		inventory = new ListRAB<Item>();
	}
	
	public ShoppingCenter(ListRAB<Item> inventory){
		lines = new ListRAB<CheckoutLine>();
		customersShopping = new ListRAB<Customer>();
		this.inventory = inventory;
	}

	public void setCheckoutLines(ListRAB<CheckoutLine> lines){
		this.lines = lines;
	}
	
	public ListRAB<Customer> getCustomersShopping() {
		return customersShopping;
	}

	public void setCustomersShopping(ListRAB<Customer> customersShopping) {
		this.customersShopping = customersShopping;
	}

	public ListRAB<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ListRAB<Item> inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * print contents of checkout lines
	 */
	public void printCheckoutLines(){
		
	}
	
}
