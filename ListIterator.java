// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: Implementing a Linked List-based Queue
// Resources used: None
package list;

public class ListIterator<T> { 
	protected ListNode<T> curNode;  /* Current node (of type ListNode) */
	
        /* ListIterator constructor. Accepts the current node. */
	public ListIterator(ListNode<T> currentNode) { 
		this.curNode = currentNode;
	}
	
	/**
	 * These two methods tell us if the iterator has run off
	 * the list on either side
	 */
	public boolean isPastEnd() { 
		return (curNode == null) || (curNode.next == null);
		/* Hint: How do you know if you’re at the dummy tail node? */
	}
	
	public boolean isPastBeginning() { 
		return (curNode == null) || (curNode.prev == null);
		/* Hint: How do you know if you’re at the dummy head node? */
	}
	
	/**
	 * Get the data at the current iterator position
	 * Return the data if appropriate, otherwise return null
	 */
	public T value() {
		if (isPastEnd() || isPastBeginning()) {
			return null;
		}
		return curNode.getData();
		/* Hint: Remember to first validate the position of the Iterator */
	}
	
	/**
	 * These two methods move the cursor of the iterator
	 * forward / backward one position
	 */
	public void moveForward() { 
		if(!isPastEnd()){
			curNode = curNode.next;
		}
		/* Hint: Remember to check IF you can move forward before you do! */
                /*       (Otherwise, do not move at all) */
	}
	
	public void moveBackward() { 
		if(!isPastBeginning()){
			curNode = curNode.prev;
		}
		/* Hint: Remember to check IF you can move backwards before you do! */
		/*       (Otherwise, do not move at all) */
	}
}


