import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<T> implements BSTInterface<T> {
	protected BSTNode<T> root;
	protected Comparator<T> comp;
	
	protected boolean found;
	
	
	/**
	 * Constructor1: no args. Sets root to null, and initializes comparator
	 */
	public BinarySearchTree() {
		root = null;
		comp = new Comparator<T>() {
			public int compare(T element1, T element2) {
				return ((Comparable)element1).compareTo(element2);
			}
		};
	}
	
	
	/**
	 * Constructor 2: Sets root to null and comparator to parameter
	 * @param comp comparator
	 */
	public BinarySearchTree(Comparator<T> comp) {
		root = null;
		this.comp = comp;
	}
	
	
	/**
	 * Returns whether or not the tree is full (never)
	 */
	public boolean isFull() {
		return false;
	}
	
	
	/**
	 * returns whether or not the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	
	/**
	 * returns minimum value in tree
	 */
	public T min() {
		// empty tree = no min value
		if (isEmpty()) { 
			return null;
		}
		
		// left-most value is min value
		BSTNode<T> node = root;
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		
		return node.getInfo();
	}
	
	
	/**
	 * returns maximum value in tree
	 */
	public T max() {
		// empty tree = no max
		if (isEmpty()) {
			return null;
		}
		
		// right-most value is max value
		BSTNode<T> node = root;
		while (node.getRight() != null) {
			node = node.getRight();
		}
		
		return node.getInfo();
	}
	
	
	/**
	 * calls recursive method that returns size of tree
	 */
	public int size() {
		return recSize(root);
	}
	
	
	/**
	 * recursively evaluates number of nodes in tree
	 * @param node root of current subtree
	 * @return size of entire tree
	 */
	private int recSize(BSTNode<T> node) {
		// if current root of subtree is null, size of this subtree is 0
		if (node == null) {
			return 0;
		}
		
		// if current node is a leaf node, size of current subtree is 1
		if (node.getLeft() == null && node.getRight() == null) {
			return 1;
		}
		
		// recursive size calculator
		return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
	}
	
	
	/**
	 * calls recursive method that checks whether or not a value
	 * is contained in the tree
	 */
	@Override
	public boolean contains(T target) {
		return recContains(root, target);
	}
	
	
	/**
	 * recursively searches for a particular value in the tree
	 * @param node root of current subtree
	 * @param target value searching for
	 * @return boolean whether or not value is found
	 */
	private boolean recContains(BSTNode<T> node, T target) {
		// if current node is false, reached end of tree, target not found
		if (node == null) {
			return false;
		}
		
		// if the target value is less than current node's value, go left
		if (comp.compare(target, node.getInfo()) < 0) {
			return recContains(node.getLeft(), target);
			
		// else if target value is more than current node's value, go right
		} else if (comp.compare(target, node.getInfo()) > 0){
			return recContains(node.getRight(), target);
			
		// else, target node is found so return true.
		} else {
			return true;
		}
	}
	
	
	/**
	 * calls recursive function that returns reference to
	 * node which the searched for value is its value
	 */
	public T get(T target) {
		return recGet(root, target);
	}
	
	
	/**
	 * recursive function that returns reference to
	 * node whose value is target
	 * @param node root of current subtree
	 * @param target value searched for
	 * @return node with value of target or null if not found
	 */
	private T recGet(BSTNode<T> node, T target) {
		// same as recContains, but returns target node's VALUE
		if (node == null ) {
			return null;
		}
		
		if (comp.compare(target, node.getInfo()) < 0) {
			return recGet(node.getLeft(), target);
			
		} else if (comp.compare(target, node.getInfo()) > 0) {
			return recGet(node.getRight(), target);
			
		} else {
			return node.getInfo();
		}
	}
	
	
	/**
	 * Gets iterator according the the traversal specified in the 
	 * parameter
	 */
	public Iterator<T> getIterator(BSTInterface.Traversal orderType) {
		final ArrayDeque<T> q = new ArrayDeque<T>();
		
		if (orderType == BSTInterface.Traversal.Preorder) {
			preOrder(root, q);
			
		} else if (orderType == BSTInterface.Traversal.Inorder) {
			inOrder(root, q);
			
		} else if (orderType == BSTInterface.Traversal.Postorder) {
			postOrder(root, q);
		}
		
		return new Iterator<T>() {
			public boolean hasNext() {
				return !q.isEmpty();
			}
			
			public T next() {
				if (!hasNext()) { 						 
					throw new IndexOutOfBoundsException("illegal invocation of next in BinarySearchTree iterator.\n");
				}
				return q.remove();
			}
			
			public void remove() {
				throw new UnsupportedOperationException("Remove operation unsupported for BST iterator");
			}
			
		};
	}
	
	
	/**
	 * preorder traversal: root, left, right
	 * @param node root of current subtree
	 * @param q queue to add nodes in to in preorder order
	 */
	private void preOrder(BSTNode<T> node, ArrayDeque<T> q) {
		if (node != null) {
			q.add(node.getInfo());
			preOrder(node.getLeft(), q);
			preOrder(node.getRight(), q);
		}
	}
	
	
	/**
	 * inorder traversal: left, root, right
	 * @param node root of current subtree
	 * @param q queue to add nodes in to in preorder order
	 */
	private void inOrder(BSTNode<T> node, ArrayDeque<T> q) {
		if (node != null) {
			inOrder(node.getLeft(), q);
			q.add(node.getInfo());
			inOrder(node.getRight(), q);
		}
	}
	
	
	/**
	 * postorder traversal: left, right, root
	 * @param node node root of current subtree
	 * @param q q queue to add nodes in to in preorder order
	 */
	private void postOrder(BSTNode<T> node, ArrayDeque<T> q) {
		if (node != null) {
			postOrder(node.getLeft(), q);
			postOrder(node.getRight(), q);
			q.add(node.getInfo());
		}
	}
	
	
	/**
	 * Calls recursive method to add elemnent to tree
	 * @param element node value to add to tree
	 */
	public boolean add(T element) {
		root = recAdd(root, element);
		return true;
	}
	
	
	/**
	 * recursive method that adds data to tree
	 * @param node root of current subtree
	 * @param element value to add to tree
	 * @return root of revised tree
	 */
	private BSTNode<T> recAdd(BSTNode<T> node, T element) {
		// if current node not initiated, set to element value
		if (node == null) {
			node = new BSTNode<T>(element);
		
		// else if value of element is less than current node, go left
		} else if (((Comparable<T>) element).compareTo(node.getInfo()) < 0) {
			node.setLeft(recAdd(node.getLeft(), element));
		
		// else go right
		} else  {
			node.setRight(recAdd(node.getRight(), element));
		} 
		
		// return root of revised tree
		return node;
	}
	
	
	/**
	 * Method that calls a recursive method to remove data 
	 * element from the tree
	 * @param element to remove from tree
	 * @return boolean weather element found and removed
	 */
	@Override
	public boolean remove(T element) {
		root = recRemove(root, element);
		return found;
	}
	
	
	/**
	 * Method that finds target node, then calls method to remove it
	 * @param node root of current subtree
	 * @param target value to remove from tree
	 * @return boolean weather target value found in tree
	 */
	private BSTNode<T> recRemove(BSTNode<T> node, T target) {
		// if current node is null, element not found so return false
		if (node == null) {
			found = false;
			
		// else if target less than vvalue of current node, go left
		} else if (((Comparable<T>)target).compareTo(node.getInfo()) < 0) {
			node.setLeft(recRemove(node.getLeft(), target));
		
		// else if target more than value of current node, go right
		} else if (((Comparable<T>)target).compareTo(node.getInfo()) > 0) {
			node.setRight(recRemove(node.getRight(), target));
		
		// otherwise, target found, call remove method then return true (found)
		} else {
			node = removeNode(node);
			found = true;
		}
		
		return node;
	}
	
	
	/**
	 * method that actually removes node once it's found
	 * @param node root of current subtree
	 * @return new value of node to remove
	 */
	private BSTNode<T> removeNode(BSTNode<T> node) {
		T data;
		
		// if current node has no left-child, reset removed node's data to right child
		if (node.getLeft() == null) {
			return node.getRight();
		
		// else if current node has no right child, reset removed node's data to left child
		} else if (node.getRight() == null) {
			return node.getLeft();
		
		// if current node has two children, need to search for logical predecessor 
		} else {
			// get value of current node's predecessor (largest value that is smaller than
			// removed node's value)
			data = (getPredecessor(node.getLeft()));
			// set removed node's info to the predecessor's info
			node.setInfo(data);
			node.setLeft(recRemove(node.getLeft(), data)); // I don't get this line (just being honest)
			return node;
		}
	}
	
	
	/**
	 * Method that finds a node's logical predecessor, which is
	 * the node with the largest value
	 * @param subtree root of current subtree
	 * @return value of logical predecessor
	 */
	private T getPredecessor(BSTNode<T> subtree) {
		BSTNode<T> predecessor = subtree;
		while (predecessor.getRight() != null) {
			predecessor = predecessor.getRight();
		}
		
		return predecessor.getInfo();
	}


	@Override
	public Iterator<T> iterator() {   /////////////// ?????????
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * prints out tree in breadth-first order
	 */
	@Override
	public String toString() {
		String bst = "";
		
		LinkedList<BSTNode<T>> ll = new LinkedList<BSTNode<T>>();
		ll.add(root);
		
		BSTNode<T> temp;
		while(!ll.isEmpty()) {
			temp = ll.poll();
			bst += temp.getInfo().toString() + " "; 						
			if (temp.getLeft() != null) {
				ll.add(temp.getLeft());
			}
			
			if (temp.getRight() != null) {
				ll.add(temp.getRight());
			}
		}
		return bst;
	}
}
