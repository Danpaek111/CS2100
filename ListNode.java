// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: Implementing a Linked List-based Queue
// Resources used: None
package list;

/**
 * 
 * @author Mark Floryan
 *
 */
public class ListNode<T> {
	
	/* Data being stored in this node */
	private T data;
	
	/* Reference to the next node in the list */
	protected ListNode<T> next;
	protected ListNode<T> prev;
	
	public ListNode(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	/* Getters */
	public T getData() { return this.data; }
}
