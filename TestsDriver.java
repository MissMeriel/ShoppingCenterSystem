package project_v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class TestsDriver {
	
	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		
		ShoppingCenter acme = new ShoppingCenter();
		AscendingOrderList<Customer, String> customers = acme.getCustomers();
		AscendingOrderList<Item, String> inventory = acme.getInventory();
		
		//AscendingOrderList<Customer, String> customers = new AscendingOrderList<Customer, String>();
		//AscendingOrderList<Item, String> inventory = new AscendingOrderList<Item, String>();
		
		inventory = generateRandomInventory(5);
		customers = generateTestCustomers(customers);
		System.out.println(customers.toString());
		System.out.println(inventory.toString());	
		
		
		//String item_name = br.readLine().trim();
		
		try {
			Customer cust;
			System.out.println(cust = customers.get(customers.search("Charlie")));
			Item item = inventory.get(inventory.search("Banana"));
			item.adjustCurrentStock(-1);
			System.out.println(inventory.toString());
		}
		catch (ListIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static AscendingOrderList<Customer, String> generateTestCustomers(AscendingOrderList<Customer, String> customers) {
		customers.add(new Customer("Alpha"));
		customers.add(new Customer("Zulu"));
		customers.add(new Customer("Sierra"));
		customers.add(new Customer("Lima"));
		customers.add(new Customer("Romeo"));
		return customers;
	}
	
	public static AscendingOrderList<Item, String> generateRandomInventory(int numberOfItems) {
	    AscendingOrderList<Item, String> randomInventory = new AscendingOrderList<Item, String>();
	    Random random = new Random();
	    String word_string = "";
	    char[] word;
	    for(int i = 0; i < numberOfItems; i++)
	    {
	    	word_string = "";
	        word = new char[random.nextInt(3)+3]; // words of length 3 through 5. (1 and 2 letter words are boring.)
	        for(int j = 0; j < word.length; j++)
	        {
	        	word_string += (char)('a' + random.nextInt(26));
	           // word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomInventory.add(new Item(word_string, random.nextInt(15), random.nextInt(50)));
	    }
	    return randomInventory;
	}

}
