package master;

/**
 * This class is used to represent an item in a shopping center
 * @author Jon Spratt
 * @version v1_11.20.2016
 */
public class Item extends KeyedItem<String>{
	/**
	 * The name of this item
	 */
	private String name;
	/**
	 * The re-stocking threshold for this item
	 */
	private int restockThreshold;
	/**
	 * The current stock quantity for this item
	 */
	private int currentStock;
	
	/**
	 * Full argument constructor for an item
	 * @param name the name to set for this item
	 * @param restockThreshold the re-stocking threshold to set for this item
	 * @param currentStock the current stock to set for this item
	 */
	public Item(String name, int restockThreshold, int currentStock) {
		super(name);
		this.name = name;
		this.restockThreshold = restockThreshold;
		this.currentStock = currentStock;
	}
	
	/**
	 * Constructor for item by name and re-stocking threshold. 
	 * Current stock equals zero upon construction
	 * @param name
	 * @param restockThreshold
	 */
	public Item(String name, int restockThreshold) {
		super(name);
		this.name = name;
		this.restockThreshold = restockThreshold;
		currentStock = 0;
	}
	
	/**
	 * Standard Accessor - for the name of this item
	 * @return returns the name of this item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Standard Accessor - for the re-stocking threshold of this item
	 * @return returns the re-stocking threshold amount for an item
	 */
	public int getRestockThreshold() {
		return restockThreshold;
	}
	
	/**
	 * Standard Mutator - for the re-stocking threshold of this item
	 * @param restockThreshold the re-stocking threshold to set for an item
	 */
	public void setRestockThreshold(int restockThreshold) {
		this.restockThreshold = restockThreshold;
	}
	
	/**
	 * Standard Accessor - for the current stock quantity of this item
	 * @return returns the current stock quantity of this item
	 */
	public int getCurrentStock() {
		return currentStock;
	}
	
	/**
	 * Adjust the current stock quantity 
	 * Can adjust by negative or positive value
	 * If adjusted by negative value, floor equals 0. You cannot have a negative amount of items
	 * @param amount the amount to adjust the current stock by
	 */
	public void adjustCurrentStock(int amount) {
		if (currentStock + amount < 0) {
			throw new ItemException("No "+name+"s in stock.");
		} else {
			currentStock += amount;	
		}
	}
	
	/**
	 * Standard toString method for the items in the collection
	 */
	public String toString() {
		return "\tItem name: " + name + 
				"\n\tRestocking threshold: " + restockThreshold +
				"\n\tCurrent stock: " + currentStock + "\n";
	}
}
