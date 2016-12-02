package project_v3;

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

		//Instance variables to be used during application runtime
		ShoppingCenter center = new ShoppingCenter();
		AscendingOrderList<Item, String> inventory = new AscendingOrderList<Item, String>();
		AscendingOrderList<Customer, String> customers = center.getCustomers();
		ListRAB<CheckoutLine> lines = new ListRAB<CheckoutLine>();
		Customer cust;
		Customer high_time_cust;
		Item item;
		String item_name;
		String cust_name;
		String input;
		

		//Primitives to be used during application runtime
		boolean done = false;
		int menuSelection = -1;
		int numItems = 0;
		int restockThreshold = 0;
		int amount = 0;
		try {
			System.out.println("Welcome to the Shopping Center System!\n");
			System.out.println("Please specify your stock.");
			System.out.print("How many items do you have? ");
			numItems = Integer.parseInt(br.readLine().trim());
			System.out.println(numItems);
			System.out.print("Please enter restocking amount: ");
			restockThreshold = Integer.parseInt(br.readLine().trim());
			System.out.println(restockThreshold);
			for(int i = 0; i<numItems; i++){
				System.out.print("Enter item name : ");
				String name = br.readLine().trim();
				System.out.println(name);
				System.out.print("How many "+name+"s? ");
				amount = Integer.parseInt(br.readLine().trim());
				System.out.println(amount);
				System.out.println(amount + " "+name+"(s) have been placed in stock.");
				item = new Item(name, restockThreshold, amount);
				inventory.add(item);
			}
			center.setInventory(inventory);
			
			lines.add(0, new CheckoutLine("regular1"));
			lines.add(1, new CheckoutLine("regular2"));
			lines.add(2, new ExpressLine("express"));
			System.out.print("Please select the checkout line that should check out customers first (regular1/regular2/express): ");
			input = br.readLine().trim();
			System.out.println(input);
			if(input.equalsIgnoreCase("regular2")){
				lines.remove(1);
				lines.add(0, new CheckoutLine("regular2"));
			} else if(input.equalsIgnoreCase("express")){
				lines.remove(3);
				lines.add(0, new ExpressLine("express"));
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
						customers.add(new Customer(cust_name));
						break;
					case 2: // Customer picks an item and places it the shopping
							// cart.						
						System.out.print(">>Enter customer name : ");
						cust_name = br.readLine().trim();
						System.out.println(cust_name);
						
						//Find customer by name, add item to their cart.
						//Reduce shopping center inventory quantity by one for the item chosen
						try {
							cust = customers.get(customers.search(cust_name)); 
							System.out.print(">>What item does " + cust.getName() + " want? ");
							item_name = br.readLine().trim();
							
							
							item = inventory.get(inventory.search(item_name));
							item.adjustCurrentStock(-1);
							cust.addItemToCart();
					
							System.out.println("Customer " + cust.getName() + " now has " +
									cust.getCart() + " item(s) in their shopping cart.");
							
							//Increment shopping time for all customers in the store
							for (int i = 1; i <= customers.size(); i++) { 	// Starts at one because AscendingOrderList get(i) method uses i - 1
								customers.get(i).incrementShoppingTime();	
							}
						}
						catch (ListIndexOutOfBoundsException e) {
							// Customer or Item was not found
							System.out.println(e.getMessage());
						}
						break;
					case 3: // Customer removes an item from the shopping cart.
						System.out.print(">>Enter customer name : ");
						cust_name = br.readLine().trim();
						try {
							cust = customers.get(customers.search(cust_name));
								try {
									cust.removeItemFromCart();
								}
								catch (CartException e) {
									// Cart is already empty
									System.out.println(e.getMessage());
								}
								
								System.out.println("Customer " + cust.getName() + " now has " +
											cust.getCart() + " item(s) in their shopping cart.");
						}
						catch (ListIndexOutOfBoundsException e) {
							// Customer was not found
							System.out.println(e.getMessage());
						}
						break;
					case 4: // Customer is done shopping.
						high_time_cust = null;
						for (int i = 1; i <= customers.size(); i++) {
							cust = customers.get(i);
							if (high_time_cust != null) {
								if (cust.getShoppingTime() > high_time_cust.getShoppingTime()) {
									high_time_cust = cust;
								}
							}
							else {
								high_time_cust = cust;
							}
						}
						
						// Determine which line to queue high_time_customer in and queue them
						
						break;
					case 5: // Customer checks out.
						break;
					case 6: // Print info about customers who are shopping.
						System.out.println(customers.toString());
						break;
					case 7: // Print info about customers in checkout lines.
						for (int i = 0; i < lines.size(); i++) {
							System.out.println(lines.get(i).toString());
						}
						break;
					case 8: // Print info about items at or below re-stocking
							// level.
						for (int i = 1; i <= inventory.size(); i++) { // Starts at one because AscendingOrderList get(i) method uses i - 1
							item = inventory.get(i);
							if (item.getCurrentStock() <= item.getRestockThreshold()) {
								System.out.println(item.toString());
							}
						}
						break;
					case 9: // Reorder an item.
						break;
					case 10: // Close the Shopping Center.
						System.out.println("The Shopping Center is closing...come back tomorrow.");
						done = true;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Re-check your input and try again.");
				} //
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
	}

}
