package master;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DSA Project Driver
 * 
 * @author Meriel, Jon Spratt
 * @version 12.1.2016
 */

public class Driver {

	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));

	public static void main(String[] args) {

		// Instance variables to be used during application runtime
		// CHANGED SCOPE OF lines, inventory -- OTHERWISE CLOGGING EXE STACK
		ShoppingCenter center = new ShoppingCenter();

		Customer cust = new Customer();
		Customer high_time_cust = cust;
		Item item = null;
		String item_name = "N/A";
		String cust_name = "N/A";
		String input = "";

		// Primitives to be used during application runtime
		boolean done = false;
		int menuSelection = -1;
		int restockThreshold = 0;
		int amount = 0;
		int lineNumber = 0;
		try {
			System.out.println("Welcome to the Shopping Center System!\n");
			System.out.println("Please specify your stock.");
			System.out.print("How many items do you have? ");
			amount = Integer.parseInt(br.readLine().trim());
			System.out.println(amount);
			System.out.print("Please specify restocking amount: ");
			restockThreshold = Integer.parseInt(br.readLine().trim());
			System.out.println(restockThreshold);

			AscendingOrderList<Item, String> inventory = new AscendingOrderList<Item, String>();
			for (int i = 0; i < amount; i++) {
				System.out.print(">>Enter item name : ");
				String name = br.readLine().trim();
				System.out.println(name);
				System.out.print(">>How many " + name + "s? ");
				int numItems = Integer.parseInt(br.readLine().trim());
				System.out.println(numItems);
				System.out.println(numItems + " " + name
						+ "(s) have been placed in stock.");
				item = new Item(name, restockThreshold, numItems);
				inventory.add(item);
			}
			center.setInventory(inventory);

			ListRAB<CheckoutLine> lines = new ListRAB<CheckoutLine>();
			lines.add(0, new CheckoutLine("checkout line #1"));
			lines.add(1, new CheckoutLine("checkout line #2"));
			lines.add(2, new ExpressLine("express checkout line"));
			// HANDLE WHO CHECKS OUT FIRST DIFFERENTLY? INSTEAD OF REORDERING?
			// DR H MAINTAINS ABOVE ORDER.
			System.out
					.print("Please select the checkout line that should check out customers first (regular1/regular2/express): ");
			input = br.readLine().trim();
			System.out.println(input);
			if (input.equalsIgnoreCase("regular2")) {
				lines.remove(1);
				lines.add(0, new CheckoutLine("checkout line #2"));
			} else if (input.equalsIgnoreCase("express")) {
				lines.remove(2);
				lines.add(0, new ExpressLine("express checkout line"));
			}
			center.setCheckoutLines(lines);

			// Application runtime loop
			while (!done) {
				displayMenu();

				try {
					System.out.print(">>Make your menu selection now: ");
					menuSelection = Integer.parseInt(br.readLine().trim());
					System.out.println(menuSelection);

					switch (menuSelection) {
					case 1: // Customer enters Shopping Center.
						// MUST TRY FOR A NAME THAT ISN'T ALREADY IN SHOPPING
						// CENTER UNTIL THEY GET ONE
						boolean successful = false;
						while (!successful) {
							System.out.print("Enter customer name: ");
							cust_name = br.readLine().trim();
							System.out.println(cust_name);
							// add if customer is not in ShoppingCenter already
							try {
								center.addCustomer(new Customer(cust_name));
								System.out.println("Customer " + cust_name
										+ " is now in the Shopping Center.");
								successful = true;
							} catch (ListIndexOutOfBoundsException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					case 2: // Customer picks an item and places it the shopping
							// cart.
						// HANDLE ITEMS THAT DON'T EXIST
						if (center.getCustomers().isEmpty()) {
							System.out
									.println("No one is in the Shopping Center!");
							break;
						}
						System.out.print(">>Enter customer name : ");
						cust_name = br.readLine().trim();
						System.out.println(cust_name);
						try {
							// Find customer by name
							cust = center.getCustomer(cust_name);
							System.out.print(">>What item does " + cust_name
									+ " want? ");
							item_name = br.readLine().trim();
							System.out.println(item_name);
							// Reduce center's inventory by one for that item.
							center.adjustStock(item_name, -1);
							// Add item to their cart.
							cust.addItemToCart();

							System.out.println("Customer " + cust.getName()
									+ " now has " + cust.getCart()
									+ " item(s) in their shopping cart.");

						} catch (ItemException e) {
							System.out.println(e.getMessage());
						} catch (ListIndexOutOfBoundsException e) {
							System.out.println(e.getMessage());
						}
						// Increment shopping time for all customers
						center.incrementTime();
						break;
					case 3: // Customer removes an item from the shopping cart.
						if (center.getCustomers().isEmpty()) {
							System.out.println("No one is in the Shopping Center!");
							break;
						} else {
							successful = false;
							while(!successful){
								System.out.print(">>Enter customer name : ");
								cust_name = br.readLine().trim();
								System.out.println(cust_name);
								try {
									cust = center.getCustomer(cust_name);
									cust.removeItemFromCart();
									System.out.println("Customer " + cust.getName()
											+ " now has " + cust.getCart()
											+ " item(s) in their shopping cart.");
									successful = true;
								} catch (CartException e) { // Cart is already empty
									System.out.println(e.getMessage());
								} catch (ListIndexOutOfBoundsException e) { // Customer
																			// not
																			// found
									System.out.println(e.getMessage());
								}
							}
						}
						// Increment shopping time for all customers
						center.incrementTime();
						break;
					case 4: // Customer is done shopping.
						AscendingOrderList<Customer, String> customers = center
								.getCustomers();
						if (customers.isEmpty()) {
							System.out
									.println("No customers in the Shopping Center!");
							break;
						}
						high_time_cust = customers.get(0);
						for (int i = 1; i < customers.size(); i++) {
							cust = customers.get(i);
							if (cust.getShoppingTime() > high_time_cust
									.getShoppingTime()) {
								high_time_cust = cust;
							} else {
								high_time_cust = cust;
							}
						}
						center.removeCustomer(high_time_cust);
						// If the customer who has been in the Shopping Center
						// the longest has no items in the cart, then s/he can
						// either leave or return to shopping without standing
						// in line to check out. If the customer returns to
						// shopping, the customer's shopping time is reset.
						if (high_time_cust.getCart() == 0) {
							System.out
									.print("Should customer "
											+ high_time_cust.getName()
											+ " leave the Shopping Center or keep on shopping? Leave?(Y/N): ");
							input = br.readLine().trim().toLowerCase();
							if (input.startsWith("y")) {
								System.out
										.println("Customer "
												+ high_time_cust.getName()
												+ " is now leaving the Shopping Center.");
								break;
							} else {
								high_time_cust.resetShoppingTime();
								center.addCustomer(high_time_cust);
								System.out
										.println("Customer "
												+ high_time_cust.getName()
												+ " with 0 items returned to shopping.");
								break;
							}
						}

						// Determine which line to queue high_time_customer in
						// and queue them
						CheckoutLine tempLine = center
								.checkoutCustomer(high_time_cust);
						System.out.println("After "
								+ high_time_cust.getShoppingTime()
								+ " minutes shopping Customer "
								+ high_time_cust.getName() + " has entered "
								+ tempLine.getName() + ".");
						break;
					case 5: // Customer checks out.
						// Customers who are at the front of the lines can check
						// out and leave or can decide to return and shop some
						// more. Customers check out in a fair manner: all
						// three checkout lines take turns in checking out
						// customers. If there is no customer in the line whose
						// turn
						// it is to check out a customer, then the next checkout
						// line that has customers in line will check out the
						// first customer in line.
						// NEEDS TO HANDLE CASE WHEN SOME BUT NOT ALL CHECKOUT
						// LINES ARE
						// EMPTY
						if (center.linesEmpty()) {
							System.out.println("No customers in any line.");
							break;
						} else {
							lines = center.getCheckoutLines();
							CheckoutLine l = lines
									.get(lineNumber = lineNumber % 3);
							while (l.getLength() == 0) {
								l = lines.get(lineNumber = ++lineNumber % 3);
							}
							Customer customer = l.serveCustomer();
							System.out
									.println("Should customer "
											+ customer.getName()
											+ " check out or keep on shopping? Checkout?(Y/N): ");
							input = br.readLine().trim().toLowerCase();
							if (input.startsWith("y")) {
								System.out
										.println("Customer "
												+ customer.getName()
												+ " is now leaving the Shopping Center.");
							} else {// If the customer decides to return to
									// shopping, the time spent in the Shopping
									// Center is
									// reset.

								customer.resetShoppingTime();
								center.addCustomer(customer);
								System.out.println("Customer "
										+ customer.getName() + " with "
										+ customer.getCart()
										+ " items returned to shopping.");
							}
						}
						break;
					case 6: // Print info about customers who are shopping.
						center.printCustomers();
						break;
					case 7: // Print info about customers in checkout lines.
						center.printCheckoutLines();
						break;
					case 8: // Print info about items at or below re-stocking
							// level.
						// HANDLE CASE WHEN NO ITEMS ARE AT RESTOCKING LEVEL?
						// NOTHING HELPFUL IN SAMPLE OUTPUT
						center.printRestockableItems();
						break;

					case 9: // Reorder an item.
						// Restock a specified item by item name and
						// restocking amount.
						try {
							System.out
									.print(">>Enter item name to be re-ordered: ");
							input = br.readLine().trim();
							System.out.println(input);
							center.getInventoryItem(input);
							System.out.print(">>Enter restocking amount: ");
							amount = Integer.parseInt(br.readLine().trim());
							System.out.println(amount);
							amount = center.adjustStock(input, amount);
							System.out.println("Stock now has " + amount + " "
									+ input + "s.");
						} catch (ListIndexOutOfBoundsException e) {
							System.out.println(input + " is not in stock!");
						}
						break;
					case 10: // Close the Shopping Center.
						System.out
								.println("The Shopping Center is closing...come back tomorrow.");
						done = true;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Re-check your input and try again.");
				} catch (ListIndexOutOfBoundsException e) {
					System.out.println(e.getMessage());
				}// end try/catch
			} // end while(!done) loop
		} catch (IOException e) {
			System.out.println("Re-check your input and try again.");
		}
	} // end main

	private static void displayMenu() {
		System.out
				.println("Select from the following menu:"
						+ "\n\t1. Customer enters Shopping Center."
						+ "\n\t2. Customer picks an item and places it the shopping cart."
						+ "\n\t3. Customer removes an item from the shopping cart."
						+ "\n\t4. Customer is done shopping."
						+ "\n\t5. Customer checks out."
						+ "\n\t6. Print info about customers who are shopping."
						+ "\n\t7. Print info about customers in checkout lines."
						+ "\n\t8. Print info about items at or below re-stocking level."
						+ "\n\t9. Reorder an item."
						+ "\n\t10. Close the Shopping Center.");
	} // end displayMenu

} // end class Driver
