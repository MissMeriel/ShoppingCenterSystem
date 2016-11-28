package project_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DSA Project Driver
 * 
 * @author Meriel
 * @version 11.23.2016
 */

public class Driver {

	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));

	public static void main(String[] args) {

		ShoppingCenter center = new ShoppingCenter();
		
		try {
			System.out.println("Welcome to the Shopping Center System!\n");
			System.out.println("Please specify your stock.");
			System.out.print("How many items do you have?");
			int numItems = Integer.parseInt(br.readLine().trim());
			System.out.println(numItems);
			System.out.print("Please enter restocking amount: ");
			int restockThreshold = Integer.parseInt(br.readLine().trim());
			System.out.println(restockThreshold);
			ListRAB<Item> inventory = new ListRAB<Item>();
			for(int i = 0; i<numItems; i++){
				System.out.print("Enter item name : ");
				String name = br.readLine().trim();
				System.out.println(name);
				System.out.print("How many "+name+"s? ");
				int amount = Integer.parseInt(br.readLine().trim());
				System.out.println(amount);
				System.out.println(amount + "items of "+name+" have been placed in stock.");
				Item item = new Item(name, restockThreshold, amount);
				inventory.add(i, item);
			}
			center.setInventory(inventory);
			
			ListRAB<CheckoutLine> lines = new ListRAB<CheckoutLine>();
			lines.add(0, new CheckoutLine("regular1"));
			lines.add(1, new CheckoutLine("regular2"));
			lines.add(3, new ExpressLine("express"));
			System.out.print("Please select the checkout line that should check out customers first (regular1/regular2/express): ");
			String input = br.readLine().trim();
			System.out.println(input);
			if(input.equalsIgnoreCase("regular2")){
				lines.remove(1);
				lines.add(0, new CheckoutLine("regular2"));
			} else if(input.equalsIgnoreCase("express")){
				lines.remove(3);
				lines.add(0, new ExpressLine("express"));
			}
			
			boolean done = false;
			int[] array = null;
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
			while (!done) {
				try {
					System.out.print(">>Make your menu selection now: ");
					int input = Integer.parseInt(br.readLine().trim());
					System.out.println(input);

					switch (input) {
					case 1: // Customer enters Shopping Center.
						System.out.print("Enter customer name: ");
						String name = br.readLine();
						System.out.println(name);
						// if customer is not in ShoppingCenter already, add
						// them
						break;
					case 2: // Customer picks an item and places it the shopping
							// cart.
						break;
					case 3: // Customer removes an item from the shopping cart.
						break;
					case 4: // Customer is done shopping.
						break;
					case 5: // Customer checks out.
						break;
					case 6: // Print info about customers who are shopping.
						break;
					case 7: // Print info about customers in checkout lines.
						break;
					case 8: // Print info about items at or below re-stocking
							// level.
						break;
					case 9: // Reorder an item.
						break;
					case 10: // Close the Shopping Center.
						System.out
								.println("The Shopping Center is closing...come back tomorrow.");
						done = true;
						break;
					}
				} catch (Exception e) {
					System.out.println("Re-check your input and try again.");
					e.printStackTrace();
				} //
			} // end while(!done) loop
		} catch (IOException e) {
			System.out.println("Re-check your input and try again.");
		}
	} // end main

}
