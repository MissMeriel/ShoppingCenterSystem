package master1;

/**
 * This class represents the AscendingOrderList ADT
 * 
 * @author Jon Spratt, Meriel Stein
 *
 * @param <T>
 *            The type of the items in this collection
 * @param <KT>
 *            The key type for the comparable items in the collection
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
	@SuppressWarnings("unchecked")
	public AscendingOrderList() {
		items = (T[]) new KeyedItem[3];
		numItems = 0;
	}

	/**
	 * Add an item to the collection by key in ascending order
	 * @param item the item to add to the collection
	 * @throws ListIndexOutOfBoundsException on indexes between zero and 
	 * the current number of items in the collection or indexes greater than
	 * the current number of items in the collection
	 */
	public void add(T item) throws ListIndexOutOfBoundsException {
		if (numItems == items.length) {
			resize();
		} // end if
		int index = search(item.getKey());
		if (index < 0 && index <= numItems) {
			index = (index * -1) - 1;
			// make room for new element by shifting all items at
			// positions >= index toward the end of the
			// list (no shift if index == numItems+1)
			for (int pos = numItems - 1; pos >= index; pos--) {
				items[pos + 1] = items[pos];
			} // end for
			// insert new item
			items[index] = item;
			numItems++;
		} // end if
		else {
			String s = "Index of out bounds";
			if (index >= 0) {
				s += "; item already exists in list.";
			}
			throw new ListIndexOutOfBoundsException(s);
		}
	}

	/**
	 * Retrieve an item from the collection by index from 0 to size()-1.
	 * @param index the index to retrieve an item from
	 * @throws ListIndexOutOfBoundsException on indexes less than zero or 
	     * greater than the current number of items in the collection
	 * @return returns the item found at the given index
	 */
	public T get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index <= numItems) {
			// index -= 1;
			return items[index];
		} else {
			throw new ListIndexOutOfBoundsException("Index " + index
					+ " is out of range.");
		} // end if
	}

	/**
	 * Search for an item by key using the Binary Search II algorithm
	 * 
	 * @return index from 0 to size()-1 if item is found; (index+1)*-1 of where
	 *         the item would go if item is not found
	 * @param searchKey
	 *            the key to search for
	 */
	public int search(KT searchKey) {
		int index = 0;
		int low = 0;
		int high = numItems;
		int mid = (low + high) / 2;

		while (low != high) {
			if (searchKey.compareTo(items[mid].getKey()) <= 0) {
				high = mid;
			} else {
				low = mid + 1;
			}
			mid = (low + high) / 2;
		}
		if (mid < numItems && items[mid] != null && searchKey.equals(items[mid].getKey())) {
			index = mid;
		} else {
			index = (mid + 1) * -1;
		}
		return index;
	}

	/**
	 * Standard Accessor - for whether or not the collection is empty
	 * @return returns true if the collection is empty, false otherwise
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Standard Accessor - for the size of the collection
	 * @return returns the number of items in the collection
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Remove an item from the collection by index
	 * @param index the index in which to remove an item from
	 * @throws ListIndexOutOfBoundsException on indexes less than zero or 
	     * greater than or equal to the current number of items in the collection
	 */
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems) {
			// delete item by shifting all items at
			// positions > index toward the beginning of the list
			// (no shift if index == size)
			for (int pos = index + 1; pos < numItems; pos++) 
			{
				items[pos - 1] = items[pos];
			} // end for
			items[--numItems] = null; 
		} else {
			throw new ListIndexOutOfBoundsException("Customer not found");
		} // end if
	}

	/**
	 * Clear the collection
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		items = (T[]) new KeyedItem[3];
		numItems = 0;
	}

	/**
	 * Resize the underlying array to make room for additional items
	 */
	private void resize() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new KeyedItem[(int) (items.length * 3 / 2)];
		for (int i = 0; i < items.length; i++) {
			temp[i] = items[i];
		}
		items = temp;
	}

	/**
	 * Standard toString method to represent all of the items in the list
	 * 
	 * @return returns a String of all items in the list
	 */
	public String toString() {
		String myString = "";
		for (int i = 0; i < numItems; i++) {
			myString += items[i].toString() + " ";
		}
		return myString;
	}
}
