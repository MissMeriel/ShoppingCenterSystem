package master;

/**
 * This class is used to represent a customer shopping in a Shopping Center
 * 
 * @author Jon Spratt, Meriel Stein
 * @version v1_11.20.2016
 */
public class Customer extends KeyedItem<String>{
	/**
	 * The name of this customer
	 */
	private String name;
	/**
	 * Number of items in this customer's shopping cart
	 */
	private int cart;
	/**
	 * Minutes this customer has spent shopping
	 */
	private int shoppingTime;
	/**
	 * true if this Customer is currently in a CheckoutLine
	 */
	private boolean inCheckout;

	/**
	 * Default constructor for an anonymous customer
	 */
	public Customer() {
		super("N/A");
		name = "N/A";
		cart = 0;
		shoppingTime = 0;
		inCheckout = false;
	}

	/**
	 * Constructor for a named customer
	 * @param name customer's name
	 */
	public Customer(String name) {
		super(name);
		this.name = name;
		cart = 0;
		shoppingTime = 0;
		inCheckout = false;
	}

	/**
	 * Standard Accessor - for the name of this customer
	 * 
	 * @return customer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Standard Accessor - for the shopping cart of this customer
	 * 
	 * @return number of items in this customer's shopping cart
	 */
	public int getCart() {
		return cart;
	}

	/**
	 * Standard Accessor - for the amount of time this customer has spent
	 * shopping
	 * 
	 * @return minutes this customer spent shopping
	 */
	public int getShoppingTime() {
		return shoppingTime;
	}

	/**
	 * Reset time spent shopping to zero
	 */
	public void resetShoppingTime() {
		shoppingTime = 0;
	}

	/**
	 * Updates inCheckout to true
	 */
	public void enterCheckout() {
		inCheckout = true;
	}

	/**
	 * Updates inCheckout to false
	 */
	public void leaveCheckout() {
		inCheckout = false;
	}

	/**
	 * Add an item to the customer's cart
	 */
	protected void addItemToCart() {
		cart++;
	}
	
	/**
	 * Remove an item from the customer's cart
	 */
	protected void removeItemFromCart() {
		if (cart == 0) {
			throw new CartException("The cart is empty.");
		}
		else {
			cart--;
		}
	}
	
	/**
	 * Increase the amount of time the customer has been shopping by one.
	 */
	protected void incrementShoppingTime() {
		shoppingTime++;
	}
	
	/**
	 * format when inCheckout: Customer Eevee has 7 items in the shopping basket.
	 * format when !inCheckout: Customer Pansage with 2 items present for 27 minutes.
	 * may be able to resolve this difference with functional programming so we
	 * can get rid of maintenance cost of inCheckout
	 */
	public String toString() {
		if (inCheckout) {
			return "\tCustomer " + name + " has " + cart
					+ " items in the shopping basket.\n";
		} else {
			return "\tCustomer " + name + " with " + cart + " items present for "
					+ shoppingTime + " minutes.\n";
		}
	}

}
