// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: HW 9 - Binary Search Tree
// Resources used: None
package tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Tree<T>{

    @Override
    public void insert(T data) {
        this.root = insert(data, root);
    }

    /**
     * Helper method for inserting recursively
     * @param data
     * @param curNode
     * @return a reference to the new root of the subtree
     */
    protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
        //TODO: Implement this method
        // if null, becomes new leaf
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
        // if compare is 0, do nothing (ignoring duplicates)
        return curNode;
    }


    /**
     * Returns a boolean (true of false) if the element was found in a BST
     */
    @Override
    public boolean find(T data) {
        return find(data, root);
    }

    // Helper method
    private boolean find(T data, TreeNode<T> curNode) {
        //TODO: Implement this method
        if (curNode == null){
            return false;
        }
        int compare = data.compareTo(curNode.data);
        if (compare == 0){
            return true;
        }
        else if (compare < 0){
            return find(data, curNode.left);
        }
        return find(data, curNode.right);
    }


    /**
     * Returns the max item in the given subtree
     */
    public T findMax() {
        return findMax(this.root);
    }

    // Helper method
    private T findMax(TreeNode<T> curNode) {
        //TODO: Implement this method
        if (curNode == null){
            return null;
        }
        //keep going right until we can't anymore
        while (curNode.right != null) {
            curNode = curNode.right;
        }
        return curNode.data;
    }

    //-----------------------------------------------------------------------------
    //THE METHOD BELOW IS PARTIALLY IMPLEMENTED    
    //YOU NEED TO COMPLETE THE IMPLEMENTATION
    //-----------------------------------------------------------------------------

    @Override
    public void remove(T data) {
        this.root = remove(data, root); // Call remove at the root of the tree
    }

    protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
        /* Note the use of compareTo() in the solution! */

	// first check if the node to be inserted is null, if so return the node
        if(curNode == null) return curNode;


        // if item I want to remove is smaller than the data of the current node...
        else if (data.compareTo(curNode.data) < 0) {
            // recursively call remove on LEFT subtree
	    /* TODO: insert code here */
            curNode.left = remove(data, curNode.left);
        }
        // if item I want to remove is larger than the data of the current node...
        else if (data.compareTo(curNode.data) > 0) {
	    // recursively call remove on RIGHT subtree
	    /* TODO: insert code here */
            curNode.right = remove(data, curNode.right);
            
        }
	/* Found item to remove, time to remove */
        else { 
	    /* Case 1: node is a leaf, return null */
            if (curNode.left == null && curNode.right == null){
                return null;
            }
	    /* Case 2A: else if node only has a left child, then return left child */
            else if (curNode.left != null && curNode.right == null){
                return curNode.left;
            }
	    /* Case 2B: else if node only has a right child, then return right child */
            else if (curNode.left == null && curNode.right != null){
                return curNode.right;
            }
            /* Case 3: else (node has 2 children) so:
               UNCOMMENT CODE BELOW AND PUT IN APPROPRIATE SPOT */
            T toDel = findMax(curNode.left);  // find inorder predecessor (max of left child)
            curNode.data = toDel;		  // copy value from inorder predecessor to node
            curNode.left = remove(toDel, curNode.left);  // delete the inorder predecessor
            return curNode;
        }
        return curNode;
    }
}
