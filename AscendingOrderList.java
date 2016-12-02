package project_v3;

/**
 * This class represents the AscendingOrderList ADT 
 * @author Jon Spratt
 *
 * @param <T> The type of the items in this collection
 * @param <KT> The key type for the comparable items in the collection
 */
public class AscendingOrderList<T extends KeyedItem<KT>, KT extends Comparable<? super KT>> {
	/**
	 * The underlying array used as a collection of Items
	 */
	private T[] items;
	/**
	 * The number of items in the collection
	 */
	private int numItems;
	
	/**
	 * Constructor for AscendingOrderList
	 */
	public AscendingOrderList() {
		items = (T[]) new KeyedItem[3];
		numItems = 0;
	}
	
	/**
	 * Add an item to the collection by key in ascending order
	 * @param item the item to add to the collection
	 */
	public void add(T item) throws ListIndexOutOfBoundsException {
		if (numItems == items.length) 
        {
        	resize();
        }  // end if
		
		int index = Math.abs(search(item.getKey())) - 1;
		
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems - 1; pos >= index; pos--)
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        } // end if
        else {
        	throw new ListIndexOutOfBoundsException("Index of out bounds");
        }
	}

	/**
	 * Retrieve an item from the collection by index
	 * @param index the index to retrieve an item from
	 */
	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index <= numItems)
        {
			index -= 1;
            return items[index];
        }
        else
        {
        	throw new ListIndexOutOfBoundsException("The selection is not in the shopping center.");
        }  // end if
	}

	/**
	 * Search for an item by key using the Binary Search II algorithm
	 * @param searchKey the key to search for
	 */
	public int search(KT searchKey) {
		int index = 0;
		int low = 0;
		int high = numItems;
		int mid = (low + high) / 2;
		
		while (low != high) {		
			if (searchKey.compareTo(items[mid].getKey()) <= 0){
				high = mid;
			}
			else {
				low = mid + 1;
			}
			mid = (low + high) / 2;
		}
				
		if (items[mid]== null || searchKey.equals(items[mid].getKey())) {
			index = mid + 1;
		}
		else {
			index = (mid + 1) * -1;
		}
		return index;
	}

	/**
	 * Standard Accessor - for whether or not the collection is empty
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Standard Accessor - for the size of the collection
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Remove an item from the collection by index
	 */
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index <= numItems)
        {
			index -= 1;
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos-1] = items[pos];
            }  // end for
            items[--numItems] = null; //fixes memory leak
        }
        else
        {
        	throw new ListIndexOutOfBoundsException("Customer not found");
        }  // end if
	}
	
	/**
	 * Clear the collection
	 */
	public void clear() {
		items = (T[]) new KeyedItem[3];
		numItems = 0;
	}
	
	/**
	 * Resize the underlying array to make room for additional items
	 */
	private void resize() {
		T[] temp = (T[]) new KeyedItem[(int) (items.length * 1.5)];
		
		for (int i = 0; i < numItems; i++) {
			temp[i] = items[i];
		}
		items = temp;
	}
	
	/**
	 * Standard toString method to represent all of the items in the list
	 * @return returns a String of all items in the list
	 */
	public String toString() {
		String myString = "";
		for (int i = 0; i < numItems; i++) {
			myString += items[i].toString();
		}
		return myString;
	}
}




//SPARE PARTS - REMOVE LATER
/* BINARY SEARCH I on KeyedItem key comparison
boolean found = false;
while (!found && low != high) {
	mid = (low + high) / 2;
	if (searchKey.compareTo(items[mid].getKey()) == 0) {
		index = mid;
		found = true;
	}
	else if (searchKey.compareTo(items[mid].getKey()) < 0) {
		high = mid;
	}
	else {
		low = mid + 1;
	}
}

if (items[mid] == null || searchKey.equals(items[mid].getKey())) {
	mid = (low + high) / 2;
	index = mid + 1;
}
else { 
	mid = (low + high) / 2;
	index = (mid + 1) * -1;
}		
return index;
*/
