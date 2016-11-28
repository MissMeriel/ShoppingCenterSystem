package project_v1;
/*
 * Purpose: Data Structures and Algorithms Lab 8
 * Status: Complete and thoroughly tested
 * Last update: 11/02/16
 * Submitted:  11/03/16
 * Comment: 
 * @author: Jon Spratt
 * @version: 2016.11.02
 */
public class AscendingOrderStringList implements AscendinglyOrderedStringListInterface {

	private String[] items;
	private int numItems;
	
	public AscendingOrderStringList() {
		items = new String[4];
		items[0] = "F";
		numItems = 1;
	}
	
	@Override
	public void add(String item) throws ListIndexOutOfBoundsException {
		if (numItems == items.length) //fixes implementation errors, fixes programming style
        {
        	resize();
        }  // end if
		
		int index = search(item);
		
        if (index >= 1 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
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

	@Override
	public String get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
        	return null;
        }  // end if
	}

	@Override
	public int search(String item) {
		int index = 1;
		int low = 1;
		int high = numItems;
		int mid = 1;
		items[0] = "F";
		
		while (low != high) {
			mid = (low + high) / 2;
			if (item.compareTo(items[mid]) <= 0){
				high = mid;
			}
			else {
				low = mid + 1;
			}
		}
		
		if (item.equals(items[mid])) {
			items[0] = "T";
		}
		mid = (low + high) / 2;
		index = mid;
		return index;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 1;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 1 && index < numItems)
        {
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
        	throw new ListIndexOutOfBoundsException("Index out of bounds");
        }  // end if
	}
	
	public void clear() {
		items = new String[3];
		items[0] = "F";
		numItems = 1;
	}
	
	/**
	 * Resize the underlying array to make room for additional items
	 */
	private void resize() {
		String[] temp = new String[(int) (items.length * 1.5)];
		
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
		for (int i = 1; i < numItems; i++) {
			myString += items[i].toString() + " ";
		}
		return myString;
	}
}
