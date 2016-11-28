package project_v1;

public class Queue<T> implements QueueInterface <T> {

	protected T[] items;
	protected int front;
	protected int back;
	protected int numItems;
	
	@SuppressWarnings("unchecked")
	public Queue() {
		items = (T[]) new Object[3];
		front = 0;
		back = items.length - 1;
		numItems = 0;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(Object newItem) throws QueueException {
		if (numItems == items.length) {
			resize();
		}		
		back = (back + 1) % items.length;
		items[back] = (T) newItem;
		numItems++;
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public void dequeueAll() {
		items = (T[]) new Object[3];
		numItems = 0;
	}

	@Override
	public T peek() throws QueueException {
		if (numItems == 0) {
			throw new QueueException("Queue empty.");
		}	
		return items[front];
	}
	
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
	
	public int getLength(){
		return numItems;
	}
	
	public String toString() {
		String myString = "";
		for (int i = 0, j = front; i < numItems; i++, j = (j+1) % items.length) {
			myString += items[j].toString() + " ";
		}
		return myString;	
	}
}
