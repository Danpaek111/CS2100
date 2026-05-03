// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: HW 10 - AVL Trees
// Resources used: None
package tree;

/**
 * Self-balancing AVL Tree
 * @author CS 2100 Team
 *
 * @param <T>
 */
 
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{
	
	@Override
	public void insert(T data) {
		this.root = insert(data, this.root);
	}
	protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
		//TODO: Implement this method
		if (curNode == null){
			return new TreeNode<T>(data);
		}
		int compare = data.compareTo(curNode.data);
		if (compare < 0){
			curNode.left = insert(data, curNode.left);
		}
		else if (compare > 0){
			curNode.right = insert(data, curNode.right);
		}
		else{
			return curNode; //duplicate: do nothing
		}
		//update height
		curNode.height = Math.max(height(curNode.left), height(curNode.right)) + 1;
		//balance node
		curNode = balance(curNode);
		return curNode;
	}

	
	@Override
	public void remove(T data) {
		/* Call remove starting at the root of the tree */
		this.root = remove(data, this.root);
	}
	protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
		/* Call BST remove before balancing, use “super” to achieve this */
		curNode = super.remove(data,  curNode);
		
		/* Handle the case when remove returns null */
		if(curNode == null) return null;
		
		/* update the height of this node if necessary (if no change, that’s OK) */
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		
		/* rotate if necessary (call balance() method to balance the node) */
		curNode = this.balance(curNode);
		
		return curNode;
	}

	
	/**
	 * Balances the given node. Assumes it is the lowest unbalanced node if unbalanced
	 * @param
	 * @return
	 */
	private TreeNode<T> balance(TreeNode<T> curNode) {
		//TODO: Implement this method
		//get balance factor
		int bf = balanceFactor(curNode);
		//left heavy
		if (bf > 1){
			//left-right case
			if(balanceFactor(curNode.left) < 0){
				curNode.left = rotateLeft(curNode.left);
			}
			//left-left case
			return rotateRight(curNode);
		}
		if(bf < -1){
			//right-left case
			if(balanceFactor(curNode.right) > 0){
				curNode.right = rotateRight(curNode.right);
			}
			//right-right case
			return rotateLeft(curNode);
		}
		//Already balanced
		return curNode;
	}
	
	private TreeNode<T> rotateRight(TreeNode<T> curNode) {
		//TODO: Implement this method
		TreeNode<T> newRoot = curNode.left;
		TreeNode<T> moveSub = newRoot.right;
		newRoot.right = curNode;
		curNode.left = moveSub;
		//update height
		curNode.height = Math.max(height(curNode.left), height(curNode.right)) +1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) +1;
		return newRoot;
	}
	
	private TreeNode<T> rotateLeft(TreeNode<T> curNode){
		//TODO: Implement this method
		TreeNode<T> newRoot = curNode.right;
		TreeNode<T> moveSub = newRoot.left;
		newRoot.left = curNode;
		curNode.right = moveSub;
		//update height
		curNode.height = Math.max(height(curNode.left), height(curNode.right)) +1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) +1;
		return newRoot;
	}
	
	private int balanceFactor(TreeNode<T> node) {
		//TODO: Implement this method
		if (node == null){
			return 0;
		}
		return height(node.left) - height(node.right);
	}
	
	
}
