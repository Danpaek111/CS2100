// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: Homework 5 - Vectors
// Resourced used: None
package vector;
@SuppressWarnings("unchecked")
public class Vector<T> implements SimpleList<T> {

	private T[] itemArray;
	private int size = 0;
	private static final int INITIAL_CAPACITY = 100;

	public Vector() {
		this(INITIAL_CAPACITY);  // calls the other constructor that takes an int parameter
	}
	
	public Vector(int capacity) {
		if (capacity < 1){
			capacity = INITIAL_CAPACITY;
		}
		this.itemArray = (T[]) new Object[capacity];
		this.size = 0;
	}

	public int capacity() {
		return this.itemArray.length;
	}

	/**
	 * When the underlying array fills up, we need to somehow resize it to accommodate the
	 * Vectors's elements.
	 * Ignores the request if the requested new capacity is too small to fit the elements
	 * already in the Vector
	 */
	public void resize(int newCapacity) {
		if (newCapacity < this.size){
			return;
		}
		T[] newArray = (T[]) new Object [newCapacity];
		for (int i = 0; i < this.size; i++){
			newArray[i] = this.itemArray[i];
		}
		this.itemArray = newArray;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.size; i++){
			this.itemArray[i] = null;
		}
		this.size = 0;
	}

	@Override
	public void insertAtTail(T item) {
		if (this.size >= this.itemArray.length) {
			this.resize(this.itemArray.length * 2);
		}
		this.itemArray[this.size] = item;
		this.size++;
	}

	@Override
	public void insertAtHead(T item) {
		if (this.size >= this.itemArray.length) {
			this.resize(this.itemArray.length * 2);
		}
		for (int i = this.size; i > 0; i--) {
			this.itemArray[i] = this.itemArray[i - 1];
		}
		this.itemArray[0] = item;
		this.size++;
	}

	@Override
	public void insertAt(int index, T item) {
		if (index < 0 || index > this.size) {
			return;
		}
		if (this.size >= this.itemArray.length) {
			this.resize(this.itemArray.length * 2);
		}
		if (index == 0) {
			insertAtHead(item);
			return;
		}
		if (index == this.size) {
			insertAtTail(item);
			return;
		}
		for (int i = this.size; i > index; i--) {
			this.itemArray[i] = this.itemArray[i - 1];
		}
		this.itemArray[index] = item;
		this.size++;
	}

	@Override
	public T removeAtTail() {
		if (this.size == 0) {
			return null;
		}
		T removed = this.itemArray[this.size - 1];
		this.itemArray[this.size - 1] = null;
		this.size--;
		return removed;
	}

	@Override
	public T removeAtHead() {
		if (this.size == 0) {
			return null;
		}
		T removed = this.itemArray[0];

		// Shift left
		for (int i = 0; i < this.size - 1; i++) {
			this.itemArray[i] = this.itemArray[i + 1];
		}
		this.itemArray[this.size - 1] = null;
		this.size--;

		return removed;
	}

	@Override
	public int find(T item) {
		if (item == null) {
			for (int i = 0; i < this.size; i++) {
				if (this.itemArray[i] == null) {
					return i;
				}
			}
			return -1;
		}

		for (int i = 0; i < this.size; i++) {
			if (item.equals(this.itemArray[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= this.size) {
			return null;
		}
		return this.itemArray[index];
	}

	/*
	 * This toString() method allow you to print a nicely formatted version of your Vector. E.g.
	 *     System.out.println( myVector );
	 * It uses utility methods in the Java Arrays library.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.itemArray[i]);
            if (i < this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
	}
}
