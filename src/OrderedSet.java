/*
 Develop class OrderedSet<E extends Comparable<E>> as a generic collection 
 that stores nodes in a binary search tree data structure. Each node has
 a reference to data, a left binary search tree, and a right binary search 
 tree. The type to be stored is limited to those that implement interface 
 Comparable interface or any interface that extends Comparable. Construct an
 OrderedSet of elements where the elements are not comparable is not possible.
 
 @authors Rick Mercer and Luis Encinas
 */

public class OrderedSet<T extends Comparable<T>> {

	// A private class that stores one node in a Binary Search Tree
	private class TreeNode {

		private TreeNode right;
		private T data;
		private TreeNode left;

		public TreeNode(T element) {
			left = null;
			data = element;
			right = null;
		}
	} // end class TreeNode

	private TreeNode root;
	private int n;

	// Create an empty OrderedSet
	public OrderedSet() {
		root = null;
		n = 0;
	}

	/*
	 * Insert an element to this OrderedSet and return true keeping this an
	 * OrderedSet. element is already exists, do not change this OrderedSet, return
	 * false.
	 */
	public boolean insert(T element) {
		// TODO: Implement this method
		if (root == null) {
			root = new TreeNode(element);
			n += 1;
			return true;
		} else {
			return insertHelper(root, element);
		}
	}

	private boolean insertHelper(TreeNode currentNode, T element) {
		// TODO Auto-generated method stub
		if (currentNode.data.equals(element)) {
			return false;
		} else if (currentNode.data.compareTo(element) > 0) {
			if (currentNode.left == null) {
				currentNode.left = new TreeNode(element);
				n += 1;
				return true;
			}
			return insertHelper(currentNode.left, element);
		} else {
			if (currentNode.right == null) {
				currentNode.right = new TreeNode(element);
				n += 1;
				return true;
			}
			return insertHelper(currentNode.right, element);
		}
	}

	/*
	 * Return the number of elements in this OrderedSet, which should be 0 when
	 * first constructed. This may run O(n) or O(1)--your choice.
	 */
	public int size() {
		// TODO: Implement this method
		return n;
	}

	/*-
	 * Return one string that concatenates all elements in this OrderedSet as
	 * they are visited in order. Elements are separated by spaces as in "1 4 9" 
	 * for this OrderedSet:
	 *    4
	 *   / \
	 *  1   9
	 */
	public String toStringInorder() {
		// TODO: Implement this method
		return toStringInorderHelper(root).trim();
	}

	private String toStringInorderHelper(TreeNode currentNode) {
		// TODO Auto-generated method stub
		if (currentNode == null) {
			return "";
		}
		return toStringInorderHelper(currentNode.left) + " " + currentNode.data
				+ toStringInorderHelper(currentNode.right);

	}

	/*
	 * Return true is search equals an element in this OrderedSet, false if not.
	 */
	public boolean contains(T search) {
		// TODO: Implement this method
		return containsHelper(root, search);
	}

	private boolean containsHelper(TreeNode currentNode, T search) {
		// TODO Auto-generated method stub
		if (currentNode == null) {
			return false;
		}
		if (currentNode.data.equals(search)) {
			return true;
		} else if (currentNode.data.compareTo(search) > 0) {
			return containsHelper(currentNode.left, search);
		} else {
			return containsHelper(currentNode.right, search);
		}

	}

	/*
	 * Return the element in this OrderedSet that is greater than all other
	 * elements. If this OrderedSet is empty, return null. No recursion needed.
	 */
	public T max() {
		// TODO: Implement this method
		if (root == null) {
			return null;
		}
		return max(root);
	}

	private T max(TreeNode currentNode) {
		// TODO Auto-generated method stub
		if (currentNode.right == null) {
			return currentNode.data;
		}
		return max(currentNode.right);
	}

	/*
	 * Return the element in this OrderedSet that is less than all other elements.
	 * If this OrderedSet is empty, return null. No recursion needed.
	 */
	public T min() {
		// TODO: Implement this method
		if (root == null) {
			return null;
		}
		return min(root);
	}

	private T min(TreeNode currentNode) {
		// TODO Auto-generated method stub
		if (currentNode.left == null) {
			return currentNode.data;
		}
		return min(currentNode.left);
	}

	/*
	 * Return the intersection of this OrderedSet and the other OrderedSet as a new
	 * OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The
	 * intersection of two sets is the set of elements that are in both sets. The
	 * intersection of {2, 4, 5, 6} and {2, 5, 6, 9} is {2, 5, 6}
	 */
	public OrderedSet<T> intersection(OrderedSet<T> other) {
		// TODO: Implement this method
		OrderedSet<T> newSet = new OrderedSet<T>();
		newSet.intersection(other, root);
		return newSet;
	}

	private void intersection(OrderedSet<T> other, TreeNode currentNode) {
		// TODO Auto-generated method stub
		if (currentNode == null) {
			return;
		}
		if (other.contains(currentNode.data)) {
			this.insert(currentNode.data);
		}
		this.intersection(other, currentNode.left);
		this.intersection(other, currentNode.right);
	}

	/*
	 * Return the union of this OrderedSet and the other OrderedSet as a new
	 * OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The union
	 * of two sets is the set all distinct elements in the collection.[ The union of
	 * {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
	 */
	public OrderedSet<T> union(OrderedSet<T> other) {
		// TODO: Implement this method
		OrderedSet<T> newSet = new OrderedSet<T>();
		newSet.union(root);
		newSet.union(other.root);
		return newSet;
	}

	private void union(TreeNode currentNode) {
		// TODO Auto-generated method stub
		if (currentNode == null) {
			return;
		}
		this.insert(currentNode.data);
		this.union(currentNode.left);
		this.union(currentNode.right);

	}

	/*
	 * Return an OrderedSet that contains all elements greater than or equal to the
	 * first parameter (inclusive) and less than the second parameter (exclusive).
	 */
	public OrderedSet<T> subset(T inclusive, T exclusive) {
		// TODO: Implement this method
		OrderedSet<T> newSet = new OrderedSet<T>();
		newSet.subset(root, inclusive, exclusive);
		return newSet;
	}

	private void subset(TreeNode currentNode, T inclusive, T exclusive) {
		// TODO Auto-generated method stub
		if (currentNode == null) {
			return;
		}
		if (currentNode.data.compareTo(inclusive) >= 0 && currentNode.data.compareTo(exclusive) < 0) {
			this.insert(currentNode.data);
		}
		this.subset(currentNode.left, inclusive, exclusive);
		this.subset(currentNode.right, inclusive, exclusive);
	}

	/*-
	* If element equals an element in this OrderedSet, remove it and return
	* true. Return false whenever element is not found. In all cases, this
	* OrderedSet must remain a true OrderedSet. Here is one recommended algorithm
	
	https://drive.google.com/file/d/1yjnYeIufsY1EgqJvaQ1nOXC627ZWauVa/view?usp=sharing
	
	* This algorithm should be O(log n)
	*/
	public boolean remove(T element) {
		if (!contains(element)) {
			return false;
		}
		root = remove(root, element);
		n--;
		return true;
	}

	private TreeNode remove(TreeNode node, T element) {
		if (node == null) {
			return node;
		}
		int compareValue = element.compareTo(node.data);
		if (compareValue < 0) {
			node.left = remove(node.left, element);
		} else if (compareValue > 0) {
			node.right = remove(node.right, element);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}
			node.data = maxValue(node.left);
			node.left = remove(node.left, node.data);
		}
		return node;
	}

	private T maxValue(TreeNode node) {
		T maxValue = node.data;
		while (node.right != null) {
			maxValue = node.right.data;
			node = node.right;
		}
		return maxValue;
	}

}