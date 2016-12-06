package master1;

/**
 * Array-based implementation of the Queue ADT.
 * @author Jon Spratt, Meriel Stein
 *
 * @param <T> the type of item stored in this ADT
 */
public class Queue< T extends KeyedItem<?>> implements QueueInterface <T> {

	/**
	 * The collection of items
	 */
	protected T[] items;
	/**
	 * The index of the front of the queue
	 */
	protected int front;
	/**
	 * The index of the back of the queue
	 */
	protected int back;
	/**
	 * The amount of items currently stored in the collection
	 */
	protected int numItems;
	
	/**
	 * Constructor for Queue ADT
	 */
	@SuppressWarnings("unchecked")
	public Queue() {
		items = (T[]) new KeyedItem[3];
		front = 0;
		back = items.length - 1;
		numItems = 0;
	}

	/**
	 * Determine whether or not the collection is empty
	 * @return returns true if the collection is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Add an item to the back of the queue
	 * @param newItem the item to be added to the queue
	 */
	@Override
	public void enqueue(T newItem) throws QueueException {
		if (numItems == items.length) {
			resize();
		}		
		back = (back + 1) % items.length;
		items[back] = (T) newItem;
		numItems++;
	}

	/**
	 * Remove an item from the front of the queue
	 * @return returns the item that was removed from the front of the queue
	 */
	@Override
	public T dequeue() throws QueueException {
		T myItem = null;
		if (numItems == 0) {
			throw new QueueException("Queue is empty.");
		}
		else {
			myItem = items[front];
			items[front] = null;
			front = (front + 1) % items.length;
			numItems--;
		}
		return myItem;
	}

	/**
	 * Remove all items from the collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void dequeueAll() {
		items = (T[]) new Object[3];
		numItems = 0;
	}

	/**
	 * Retrieve the item at the front of the queue and leave in place
	 * @return returns the item at the front of the queue
	 */
	@Override
	public T peek() throws QueueException {
		if (numItems == 0) {
			throw new QueueException("Queue empty.");
		}	
		return items[front];
	}
	
	/**
	 * Resize the underlying collection to make room for more items
	 */
	@SuppressWarnings("unchecked")
	protected void resize() {
		T[] temp = (T[]) new Object[(int)(items.length * 1.5)];
		for (int i = 0; i < numItems; i++) {
			temp[i] = items[(front + i) % items.length];
		}
		items = temp;
		front = 0;
		back = numItems - 1;
	}
	
	/**
	 * Determine the length of the queue
	 * @return returns the number of items in the collection
	 */
	public int getLength(){
		return numItems;
	}
	
	/**
	 * Determine whether or not the queue contains a specific item
	 * @param item the item to check for
	 * @return returns true if the item in in the queue, false otherwise
	 */
	public boolean contains(T item){
		for(int i = 0; i< numItems; i++){
			if(item.getKey().equals(items[(front + i) % items.length].getKey())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Standard toString method to represent all of the items in the collection
	 * @return returns a String of all items in the collection
	 */
	public String toString() {
		String myString = "";
		for (int i = 0, j = front; i < numItems; i++, j = (j+1) % items.length) {
			myString += items[j].toString() + " ";
		}
		return myString;	
	}
}
