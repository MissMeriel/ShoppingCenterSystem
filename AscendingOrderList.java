package master;

/**
 * This class represents the AscendingOrderList ADT
 * 
 * @author Jon Spratt
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
	 * 
	 * @param item
	 *            the item to add to the collection
	 */
	public void add(T item) throws ListIndexOutOfBoundsException {
//		System.out.println("in AOSL add");
//		System.out.println("\tnumItems:" + numItems);
//		System.out.println("\titems length:" + items.length);
		if (numItems == items.length) {
			resize();
		} // end if

		int index = search(item.getKey());

//		System.out.println("\tindex:" + index);
//		System.out.println();
		if (index < 0 && index <= numItems) {
			index = (index * -1) - 1;
			// make room for new element by shifting all items at
			// positions >= index toward the end of the
			// list (no shift if index == numItems+1)
			for (int pos = numItems - 1; pos >= index; pos--) {
//				System.out.println("\titems[" + (pos + 1) + "] = "
//						+ items[pos + 1] + "assigned " + "items[" + pos
//						+ "] = " + items[pos]);
				items[pos + 1] = items[pos];
//				for (T t : items) {
//					if (t != null) {
//						System.out.print(t.toString() + " ");
//					}
//				}
//				System.out.println();
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
	 * 
	 * @param index
	 *            the index to retrieve an item from
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

		System.out.println();
		while (low != high) {
			//System.out.println("mid="+mid+"; items[mid]:"+items[mid]);
			if (searchKey.compareTo(items[mid].getKey()) <= 0) {
				high = mid;
			} else {
				low = mid + 1;
			}
			mid = (low + high) / 2;
		}
		// why if items[mid]== null?
//		System.out.println("numItems:"+numItems);
//		System.out.print("mid="+mid+"; ");
		//System.out.println("items[mid]:"+items[mid]);
		if (mid < numItems && items[mid] != null && searchKey.equals(items[mid].getKey())) {
			index = mid /* +1 */;
		} else {
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
		if (index >= 0 && index < numItems) {
			//index -= 1;
			// delete item by shifting all items at
			// positions > index toward the beginning of the list
			// (no shift if index == size)
			for (int pos = index + 1; pos < numItems; pos++) // textbook code
																// modified to
																// eliminate
																// logic error
																// causing
																// ArrayIndexOutOfBoundsException
			{
				items[pos - 1] = items[pos];
			} // end for
			items[--numItems] = null; // fixes memory leak
		} else {
			throw new ListIndexOutOfBoundsException("Customer not found");
		} // end if
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
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new KeyedItem[(int) (items.length * 3 / 2)];
//		System.out.println("in AOSL resize");
//		System.out.println("\ttemp length:" + temp.length);
//		System.out.println("\titems length:" + items.length);

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
