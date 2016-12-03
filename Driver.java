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
		int numItems = 0;
		int restockThreshold = 0;
		int amount = 0;
		int lineNumber = 0;
		try {
			System.out.println("Welcome to the Shopping Center System!\n");
			System.out.println("Please specify your stock.");
			System.out.print("How many items do you have? ");
			numItems = Integer.parseInt(br.readLine().trim());
			System.out.println(numItems);
			System.out.print("Please enter restocking amount: ");
			restockThreshold = Integer.parseInt(br.readLine().trim());
			System.out.println(restockThreshold);

			AscendingOrderList<Item, String> inventory = new AscendingOrderList<Item, String>();
			for (int i = 0; i < numItems; i++) {
				System.out.print("Enter item name : ");
				String name = br.readLine().trim();
				System.out.println(name);
				System.out.print("How many " + name + "s? ");
				amount = Integer.parseInt(br.readLine().trim());
				System.out.println(amount);
				System.out.println(amount + " " + name
						+ "(s) have been placed in stock.");
				item = new Item(name, restockThreshold, amount);
				inventory.add(item);
			}
			center.setInventory(inventory);

			ListRAB<CheckoutLine> lines = new ListRAB<CheckoutLine>();
			lines.add(0, new CheckoutLine("checkout line #1"));
			lines.add(1, new CheckoutLine("checkout line #2"));
			lines.add(2, new ExpressLine("express checkout line"));
			System.out
					.print("Please select the checkout line that should check out customers first (regular1/regular2/express): ");
			input = br.readLine().trim();
			System.out.println(input);
			if (input.equalsIgnoreCase("regular2")) {
				lines.remove(1);
				lines.add(0, new CheckoutLine("checkout line #2"));
			} else if (input.equalsIgnoreCase("express")) {
				lines.remove(3);
				lines.add(0, new ExpressLine("express checkout line"));
			}

			displayMenu();

			// Application runtime loop
			while (!done) {
				try {
					System.out.print(">>Make your menu selection now: ");
					menuSelection = Integer.parseInt(br.readLine().trim());
					System.out.println(menuSelection);

					switch (menuSelection) {
					case 1: // Customer enters Shopping Center.
						System.out.print("Enter customer name: ");
						cust_name = br.readLine().trim();
						System.out.println(cust_name);
						// if customer is not in ShoppingCenter already, add
						// them
						// NO CATCH FOR ADDING IDENTICAL ITEMS AOSL -- ADDTL.
						// FUNCTIONALITY IN SHOPPINGCENTER
						try {
							center.addCustomer(new Customer(cust_name));
						} catch (ListIndexOutOfBoundsException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2: // Customer picks an item and places it the shopping
							// cart.
						System.out.print(">>Enter customer name : ");
						cust_name = br.readLine().trim();
						System.out.println(cust_name);

						// Find customer by name, add item to their cart.
						// Reduce shopping center inventory quantity by one for
						// the item chosen
						try {
							cust = center.getCustomer(cust_name); // customers.get(customers.search(cust_name));
							System.out.print(">>What item does "
									+ cust.getName() + " want? ");
							item_name = br.readLine().trim();

							// DIFF INVENTORY FROM THE ONE IN SHOPPING CENTER
							// CLASS
							// -- resolved w/ new ShoppingCenter methods,
							// ItemException
							center.adjustStock(item_name, -1);
							cust.addItemToCart();

							System.out.println("Customer " + cust.getName()
									+ " now has " + cust.getCart()
									+ " item(s) in their shopping cart.");

							// Increment shopping time for all customers in the
							// store
							// CHANGED TO ShoppingCenter METHOD - CLEANER, LESS
							// GET/SET
							center.incrementTime();
							// MAY CHANGE INPUT i FOR AOL.get(i)
							// for (int i = 1; i <= customers.size(); i++) { //
							// Starts at one because AscendingOrderList get(i)
							// method uses i - 1
							// customers.get(i).incrementShoppingTime();
							// }
						} catch (ItemException e) {
							System.out.println(e.getMessage());
						} catch (ListIndexOutOfBoundsException e) {
							// Customer or Item was not found
							// NEEDED TO CHANGE ORIGIN OF CUSTOMER/ITEM GETS FOR
							// CORRECT ERROR MSG
							System.out.println(e.getMessage());
						}
						break;
					case 3: // Customer removes an item from the shopping cart.
						System.out.print(">>Enter customer name : ");
						cust_name = br.readLine().trim();
						System.out.println(cust_name);
						try {
							// CHANGED FOR HAS-A, REPEATED CODE, INCONSISTENCY
							// BTW DRIVER DATAFIELD & SHOPPINGCENTER DATAFIELD
							cust = center.getCustomer(cust_name); // customers.get(customers.search(cust_name));
							try {
								cust.removeItemFromCart();
							} catch (CartException e) {
								// Cart is already empty
								System.out.println(e.getMessage());
							}
							// Increment shopping time for all customers in the
							// store
							center.incrementTime();
							System.out.println("Customer " + cust.getName()
									+ " now has " + cust.getCart()
									+ " item(s) in their shopping cart.");
						} catch (ListIndexOutOfBoundsException e) {
							// Customer was not found
							System.out.println(e.getMessage());
						}
						break;

					case 4: // Customer is done shopping.
						// CHANGED FOR EFFICIENCY
						// CONSIDERING MAKING THIS A SHOPPINGCENTER METHOD TO
						// CUT DOWN ON REPEATED CODE
						AscendingOrderList<Customer, String> customers = center
								.getCustomers();
						high_time_cust = customers.get(0);// null;
						for (int i = 2 /* 1 */; i <= customers.size(); i++) {
							cust = customers.get(i);
							if (cust.getShoppingTime() > high_time_cust
									.getShoppingTime()) {
								high_time_cust = cust;
							} else {
								high_time_cust = cust;
							}
						}
						// If menu option 4 is chosen (Customer is done
						// shopping)
						// and the customer who has been in the Shopping Center
						// the
						// longest has no items in the cart, then the customer
						// should be given the choice to leave or to return to
						// shopping. The customer does not need to stand in line
						// to
						// check out. If the customer returns to shopping, the
						// customer's shopping time is reset.
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
							} else {
								high_time_cust.resetShoppingTime();
								center.addCustomer(high_time_cust);
								System.out
										.println("Customer "
												+ high_time_cust.getName()
												+ " with 0 items returned to shopping.");
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
								+ tempLine.toString() + ".");
						break;
					case 5: // Customer checks out.
						// Customers who are at the front of the lines can check
						// out
						// and leave or can decide to return and shop some more
						// (option 5). Customers check out in a fair manner: all
						// three checkout lines take turns in checking out
						// customers. If there is no customer in the line whose
						// turn
						// it is to check out a customer, then the next checkout
						// line that has customers in line will check out the
						// first
						// customer in line. If the customer decides to return
						// to
						// shopping, the time spent in the Shopping Center is
						// reset.
						lines = center.getCheckoutLines();
						boolean linesEmpty = true;
						CheckoutLine l = lines.get(lineNumber % 3);
						if (l.getLength() != 0) {
							linesEmpty = false;
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
							} else {
								customer.resetShoppingTime();
								center.addCustomer(customer);
								System.out.println("Customer "
										+ customer.getName() + " with "
										+ customer.getCart()
										+ " items returned to shopping.");
							}
						}
						if (linesEmpty) {
							System.out.println("No customers in any line.");
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
						inventory = center.getInventory();
						System.out.println("Items at restocking level:");
						//FIXED RANGE1
						for (int i = 0; i <= inventory.size(); i++) {
							item = inventory.get(i);
							if (item.getCurrentStock() <= item
									.getRestockThreshold()) {
								System.out.println("\t" + item.getName()
										+ " with " + item.getCurrentStock()
										+ " items.");
							}
						}
						break;

					case 9: // Reorder an item.
						// The Shopping Center system should offer the option to
						// restock a specified item by item name and
						// restocking amount.
						System.out
								.print(">>Enter item name to be re-ordered: ");
						input = br.readLine().trim();
						System.out.println(input);
						center.getInventoryItem(input);
						System.out.print(">>Enter restocking amount: ");
						amount = Integer.parseInt(br.readLine().trim());
						System.out.println(amount);
						amount = center.adjustStock(input, amount);
						System.out.println("Inventory now has " + amount + " "
								+ input + "(s).");
						break;
					case 10: // Close the Shopping Center.
						System.out
								.println("The Shopping Center is closing...come back tomorrow.");
						done = true;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Re-check your input and try again.");
//				} catch (ListIndexOutOfBoundsException e) {
//					System.out.println(e.getMessage());
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
