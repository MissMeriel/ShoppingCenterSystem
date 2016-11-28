package project_v1;

	// ********************************************************
	// Array-based implementation of the ADT list.
	// *********************************************************
	public class ListRAB<T> implements ListInterface<T>
	{
		/**
		 * Underlying structure to store items 
		 */
	    protected T []items;  // an array of list items
	    /**
	     * Amount of items in the collection
	     */
	    protected int numItems;  // number of items in list

	    /**
	     * Constructor for Resizable Array Based List ADT (ListRAB)
	     */
	    public ListRAB()
	    {
	        items = (T[]) new Object[3];
	        numItems = 0;
	    }  // end default constructor

	    /**
	     * Accessor for whether or not the collection is empty
	     * @return returns true if the collection has no items, false otherwise
	     */
	    public boolean isEmpty()
	    {
	        return (numItems == 0);
	    } // end isEmpty

	    /**
	     * Accessor for the size of the collection
	     * @return returns the number of items in the collection
	     */
	    public int size()
	    {
	        return numItems;
	    }  // end size

	    /**
	     * Remove all items from the collection
	     */
	    public void removeAll()
	    {
	        // Creates a new array; marks old array for
	        // garbage collection.
	        items = (T[]) new Object[3];
	        numItems = 0;
	    } // end removeAll

	    /**
	     * Add an item to the collection at a specified index
	     * @param index the index in which to add the item
	     * @param item the item to add to the collection
	     */
	    public void add(int index, T item) throws ListIndexOutOfBoundsException
	    {
	        if (numItems == items.length) //fixes implementation errors, fixes programming style
	        {
	        	resize();
	        }  // end if
	        if (index >= 0 && index <= numItems)
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
	    } //end add

	    /**
	     * Retrieve an item from the collection at a specified index
	     * @param index the index in which to retrieve an item from the collection
	     * @return returns the item at the specified index, if index is in range, null otherwise
	     */
	    public T get(int index) throws ListIndexOutOfBoundsException
	    {
	        if (index >= 0 && index < numItems)
	        {
	            return items[index];
	        }
	        else
	        {
	        	return null;
	        }  // end if
	    } // end get

	    /**
	     * Remove the item at a specified index
	     */
	    public void remove(int index) throws ListIndexOutOfBoundsException
	    {
	        if (index >= 0 && index < numItems)
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
	    } //end remove
	    
	    /**
		 * Resize the underlying array to make room for additional items
		 */
		private void resize() {
			T[] temp = (T[]) new Object[(int) (items.length * 1.5)];
			
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
				myString += items[i].toString() + " ";
			}
			return myString;
		}
		
		/**
		 * Reverse the order of the items in the list
		 */
		public void reverse() {
			if (!(numItems == 0)) {
				for (int i = 0, j = numItems - 1; i < (numItems / 2); i++, j--) {
					T temp = items[i];
					items[i] = items[j];
					items[j] = temp;
				}
			}
		}
	}
