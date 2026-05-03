// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: Implementing a Linked List-based Queue
// Resources used: None
package list;

/**
 * 
 * A custom built linked list
 * T here is the type the list stores
 */
public class LinkedList<T> implements SimpleList<T>{

	/* Dummy head and tail */
	private ListNode<T> head, tail;
	private int size;
	
	public LinkedList() {
		head = new ListNode<>(null);
		tail = new ListNode<>(null);
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	/**
	 * Clears out the entire list
	 */
	public void clear() {
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	/**
	 * Inserts new data at the end of the list (i.e., just before the dummy tail node)
	 * @param data
	 */
	public void insertAtTail(T data) {
		ListNode<T> n = new ListNode<T>(data);
		ListNode<T> before = tail.prev;
		n.prev = before;
		n.next = tail;
		before.next = n;
		tail.prev = n;
		size++;
	}
	
	/**
	 * Inserts data at the front of the list (i.e., just after the dummy head node
	 * @param data
	 */
	public void insertAtHead(T data) {
		ListNode<T> n = new ListNode<T>(data);
		ListNode<T> after = head.next;
		n.prev = head;
		n.next = after;
		head.next = n;
		after.prev = n;
		size++;
	}
	
	/**
	 * Inserts node such that index becomes the position of the newly inserted data
	 * @param data
	 * @param index
	 */
	public void insertAt(int index, T data) {
		if (index < 0 || index > size){
			return;
		}
		ListNode<T> current = head.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		ListNode<T> n = new ListNode<T>(data);
		ListNode<T> before = current.prev;
		n.prev = before;
		n.next = current;
		before.next = n;
		current.prev = n;
		size++;
	}
	
	/**
	 * Inserts data after the node pointed to by iterator
	 */
	public void insert(ListIterator<T> it, T data) {
		if(it == null || it.curNode == null){
			return;
		}
		if(it.curNode == tail){
			return;
		}
		ListNode<T> current = it.curNode;
		ListNode<T> after = current.next;
		ListNode<T> n = new ListNode<T>(data);
		n.prev = current;
		n.next = after;
		current.next = n;
		after.prev = n;
		size++;
	}
	
	
	
	public T removeAtTail(){
		if(size == 0){
			return null;
		}
		ListNode<T> last = tail.prev;
		ListNode<T> before = last.prev;
		before.next = tail;
		tail.prev = before;
		size--;
		return last.getData();
	}
	
	public T removeAtHead(){
		if(size == 0){
			return null;
		}
		ListNode<T> first = head.next;
		ListNode<T> after = first.next;
		head.next = after;
		after.prev = head;
		size--;
		return first.getData();
	}
	
	/**
	 * Remove based on Iterator position
	 * Sets the iterator to the node AFTER the one removed
	 */
	public T remove(ListIterator<T> it) {
		if (it == null || it.curNode == null){
			return null;
		}
		if (it.curNode == head || it.curNode == tail){
			return null;
		}
		ListNode<T> current = it.curNode;
		ListNode<T> before = current.prev;
		ListNode<T> after = current.next;
		before.next = after;
		after.prev = before;
		it.curNode = after;
		size--;
		return current.getData();
	}
	
	/**
	 * Returns index of first occurrence of the data in the list, or -1 if not present
	 * @param data
	 * @return
	 */
	public int find(T data) {
		int index = 0;
		ListNode<T> current = head.next;
		while(current != tail){
			if (data == null){
				if(current.getData() == null){
					return index;
				}
			}
			else{
				if(data.equals(current.getData())){
					return index;
				}
			}
			current = current.next;
			index++;
		}
		return -1;
	}
	
	/**
	 * Returns the data at the given index, null if anything goes wrong (index out of bounds, empty list, etc.)
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if (index < 0 || index >= size){
			return null;
		}
		ListNode<T> current = head.next;
		for (int i = 0; i < index; i++){
			current = current.next;
		}
		return current.getData();
	}
	
	/**
	 * Returns the list as space separated values
	 */
	public String toString() {
		String toRet = "[";
		int count = 0;
		
		ListNode<T> curNode = head.next;
		while(curNode != tail) {
			count++;
			toRet += curNode.getData();
			if (count != size)
				toRet += ", ";
			curNode = curNode.next;
		}
		
		return toRet + "]";
	}
	
	/* Return iterators at front and end of list */
	public ListIterator<T> front(){ 
		return new ListIterator<T>(head.next);
	}

	public ListIterator<T> back(){
		return new ListIterator<T>(tail.prev);
	}
}
