package org.altervista.scarrozzo.jredblacktree.rbtclasses;

import java.util.ArrayList;

import org.altervista.scarrozzo.jredblacktree.rbtclasses.RedBlackTreeNode.RBT_COLORS;

/**
 * 
 * @author Sergio Carrozzo
 *
 *         This class represent a red black tree
 *
 * @param <T>
 *            generic key for the nodes
 */
public class RedBlackTree<T extends Comparable<T>> {

	private RedBlackTreeNode<T> nil;
	private RedBlackTreeNode<T> root;

	/**
	 * Create a new red black tree with root node key equals to the key
	 * parameter
	 * 
	 * @param key
	 *            The key of root node
	 */
	public RedBlackTree(T key) {
		this(new RedBlackTreeNode<T>(key));
	}

	private RedBlackTree(RedBlackTreeNode<T> root) {
		this.nil = new RedBlackTreeNode<T>();
		this.root = root;
		root.setLeftChild(nil);
		root.setRightChild(nil);
		root.setParent(nil);
		root.setColor(RBT_COLORS.BLACK);
	}

	/**
	 * Set the root node of the RBT
	 * 
	 * @param root
	 *            The new root node
	 */
	void setRootNode(RedBlackTreeNode<T> root) {
		this.root = root;
	}

	/**
	 * Get the root node of the RBT
	 * 
	 * @return The root node of the RBT
	 */
	RedBlackTreeNode<T> getRootNode() {
		return root;
	}

	/**
	 * Useful internal method to retrieve the nil node
	 * 
	 * @return The nil node
	 */
	RedBlackTreeNode<T> getNilNode() {
		return nil;
	}

	/**
	 * Find the successor of a given node
	 * 
	 * @param node
	 *            The node from which start to search the successor
	 * @return The successor node
	 */
	public RedBlackTreeNode<T> treeSuccessor(RedBlackTreeNode<T> node) {
		if (!node.getRightChild().equals(nil)) {
			return getMinNodeStartingFrom(node.getRightChild());
		}

		RedBlackTreeNode<T> successor = node.getParent();
		while (!successor.equals(nil) && node.equals(successor.getRightChild())) {
			node = successor;
			successor = successor.getParent();
		}
		return successor;
	}

	/**
	 * Find the predecessor of a given node
	 * 
	 * @param node
	 *            The node from which start to search the predecessor
	 * @return The predecessor node
	 */
	public RedBlackTreeNode<T> treePredecessor(RedBlackTreeNode<T> node) {
		if (!node.getLeftChild().equals(nil)) {
			return getMaxNodeStartingFrom(node.getLeftChild());
		}

		RedBlackTreeNode<T> predecessor = node.getParent();
		while (!predecessor.equals(nil) && node.equals(predecessor.getLeftChild())) {
			node = predecessor;
			predecessor = predecessor.getParent();
		}
		return predecessor;
	}

	/**
	 * Search the node with the key equals to the key parameter
	 * 
	 * @param key
	 *            The key used to find the node
	 * @return The node found or null
	 */
	public RedBlackTreeNode<T> search(T key) {
		RedBlackTreeNode<T> x = root;
		while (!x.equals(nil)) {
			if (x.getKey().equals(key)) {
				return x;
			} else if (x.getKey().compareTo(key) > 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}
		return null;
	}

	/**
	 * Get the tree minimum starting from a given node
	 * 
	 * @param startingNode
	 *            The starting node
	 * @return The minimum node or null if the parameter startingNode is nil
	 */
	public RedBlackTreeNode<T> getMinNodeStartingFrom(RedBlackTreeNode<T> startingNode) {
		RedBlackTreeNode<T> x = startingNode;
		RedBlackTreeNode<T> y = nil;
		while (!x.equals(nil)) {
			y = x;
			x = x.getLeftChild();
		}
		if (y != nil) {
			return y;
		} else {
			return null;
		}
	}

	/**
	 * Get the tree minimum starting from the root node
	 * 
	 * @return The minimum node or null if root is nil
	 */
	public RedBlackTreeNode<T> getMinNode() {
		return getMinNodeStartingFrom(root);
	}

	/**
	 * Get the tree maximum starting from a given node
	 * 
	 * @param startingNode
	 *            The starting node
	 * @return The maximum node or null if the parameter startingNode is nil
	 */
	public RedBlackTreeNode<T> getMaxNodeStartingFrom(RedBlackTreeNode<T> startingNode) {
		RedBlackTreeNode<T> x = startingNode;
		RedBlackTreeNode<T> y = nil;
		while (!x.equals(nil)) {
			y = x;
			x = x.getRightChild();
		}
		if (y != nil) {
			return y;
		} else {
			return null;
		}
	}

	/**
	 * Get the tree maximum starting from the root node
	 * 
	 * @return The maximum node or null if root is nil
	 */
	public RedBlackTreeNode<T> getMaxNode() {
		return getMaxNodeStartingFrom(root);
	}

	/**
	 * Insert a new node in the red black tree
	 * 
	 * @param key
	 *            The key value of the new node
	 */
	public void insert(T key) {
		insert(new RedBlackTreeNode<T>(key));
	}

	/**
	 * Insert a new node to the tree
	 * 
	 * @param node
	 *            The new node
	 */
	private void insert(RedBlackTreeNode<T> node) {
		RedBlackTreeNode<T> x = root;
		RedBlackTreeNode<T> y = nil;
		while (!x.equals(nil)) {
			y = x;
			if (node.getKey().compareTo(x.getKey()) >= 0) {
				x = x.getRightChild();
			} else if (node.getKey().compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			}
		}
		if (y.getKey().compareTo(node.getKey()) > 0) {
			y.setLeftChild(node);
		} else {
			y.setRightChild(node);
		}
		node.setParent(y);
		node.setLeftChild(nil);
		node.setRightChild(nil);
		node.setColor(RBT_COLORS.RED);
		RedBlackTreeUtils.rbtInsertFixup(this, node);
	}

	/**
	 * Delete a node
	 * 
	 * @param node
	 *            The node to delete
	 */
	public void delete(RedBlackTreeNode<T> node) {
		RedBlackTreeNode<T> y = node;
		RedBlackTreeNode<T> x = nil;
		RBT_COLORS y_original_color = y.getColor();
		if (node.getLeftChild().equals(nil)) {
			x = node.getRightChild();
			RedBlackTreeUtils.rbtTransplant(this, node, node.getRightChild());
		} else if (node.getRightChild().equals(nil)) {
			x = node.getLeftChild();
			RedBlackTreeUtils.rbtTransplant(this, node, node.getLeftChild());
		} else {
			y = getMinNodeStartingFrom(node.getRightChild());
			y_original_color = y.getColor();
			x = y.getRightChild();
			if (y.getParent().equals(node)) {
				x.setParent(y);
			} else {
				RedBlackTreeUtils.rbtTransplant(this, y, y.getRightChild());
				y.setRightChild(node.getRightChild());
				y.getRightChild().setParent(y);
			}
			RedBlackTreeUtils.rbtTransplant(this, node, y);
			y.setLeftChild(node.getLeftChild());
			y.getLeftChild().setParent(y);
			y.setColor(node.getColor());
		}
		if (y_original_color == RBT_COLORS.BLACK) {
			RedBlackTreeUtils.deleteFixup(this, x);
		}
	}

	/**
	 * Print all the nodes starting from the root with in order principle
	 */
	public void printInOrderAllNodes() {
		if (root != nil) {
			printInOrderAllNodes(root, 0, "root");
		} else {
			System.out.println("The RBT is empty");
		}
	}

	/**
	 * Print elements with in-order criterium
	 * 
	 * @param node
	 *            the node from which to start to print
	 * @param level
	 *            the current level of node we are printing
	 * @param typeChild
	 *            show if the node is the left or the right child of the parent
	 *            node
	 */
	private void printInOrderAllNodes(RedBlackTreeNode<T> node, int level, String typeChild) {
		if (node != nil) {
			if (node.getLeftChild() != nil) {
				printInOrderAllNodes(node.getLeftChild(), level + 1, "left child");
			}

			if (node.getKey() != null && node.getColor() != null) {
				String output = "Node at level " + level + ". Key: " + node.getKey().toString() + ". Color: "
						+ node.getColor().toString() + ". Type child: " + typeChild + ". Parent: "
						+ node.getParent().getKey();
				System.out.println(output);
			}

			if (node.getRightChild() != nil) {
				printInOrderAllNodes(node.getRightChild(), level + 1, "right child");
			}
		}
	}

	/**
	 * Print all the nodes starting from root with the pre-order principle
	 */
	public void printPreOrderAllNodes() {
		if (root != nil) {
			printPreOrderAllNodes(root, 0, "root");
		} else {
			System.out.println("The RBT is empty");
		}
	}

	/**
	 * Print elements with pre-order criterium
	 * 
	 * @param node
	 *            the node from which to start to print
	 * @param level
	 *            the current level of node we are printing
	 * @param typeChild
	 *            show if the node is the left or the right child of the parent
	 *            node
	 */
	private void printPreOrderAllNodes(RedBlackTreeNode<T> node, int level, String typeChild) {
		if (node != nil) {

			if (node.getKey() != null && node.getColor() != null) {
				String output = "Node at level " + level + ". Key: " + node.getKey().toString() + ". Color: "
						+ node.getColor().toString() + ". Type child: " + typeChild + ". Parent: "
						+ node.getParent().getKey();
				System.out.println(output);
			}

			if (node.getLeftChild() != nil) {
				printPreOrderAllNodes(node.getLeftChild(), level + 1, "left child");
			}

			if (node.getRightChild() != nil) {
				printPreOrderAllNodes(node.getRightChild(), level + 1, "right child");
			}
		}
	}

	/**
	 * print all the nodes starting from root with post-order principle
	 */
	public void printPostOrderAllNodes() {
		if (root != nil) {
			printPostOrderAllNodes(root, 0, "root");
		} else {
			System.out.println("The RBT is empty");
		}
	}

	/**
	 * Print elements with post-order criterium
	 * 
	 * @param node
	 *            the node from which to start to print
	 * @param level
	 *            the current level of node we are printing
	 * @param typeChild
	 *            show if the node is the left or the right child of the parent
	 *            node
	 */
	private void printPostOrderAllNodes(RedBlackTreeNode<T> node, int level, String typeChild) {
		if (node != nil) {

			if (node.getLeftChild() != nil) {
				printPostOrderAllNodes(node.getLeftChild(), level + 1, "left child");
			}

			if (node.getRightChild() != nil) {
				printPostOrderAllNodes(node.getRightChild(), level + 1, "right child");
			}

			if (node.getKey() != null && node.getColor() != null) {
				String output = "Node at level " + level + ". Key: " + node.getKey().toString() + ". Color: "
						+ node.getColor().toString() + ". Type child: " + typeChild + ". Parent: "
						+ node.getParent().getKey();
				System.out.println(output);
			}
		}
	}

	/**
	 * From red black tree to array list adding with pre order principle
	 * 
	 * @return The array list
	 */
	public ArrayList<T> toPreOrderList() {
		ArrayList<T> list = new ArrayList<T>();
		fromRBTtoPreOrderList(root, list);
		return list;
	}

	private void fromRBTtoPreOrderList(RedBlackTreeNode<T> node, ArrayList<T> list) {

		list.add(node.getKey());

		if (node.getLeftChild() != nil) {
			fromRBTtoPreOrderList(node.getLeftChild(), list);
		}

		if (node.getRightChild() != nil) {
			fromRBTtoPreOrderList(node.getRightChild(), list);
		}
	}

	/**
	 * From red black tree to array list adding with in order principle
	 * 
	 * @return The array list
	 */
	public ArrayList<T> toInOrderList() {
		ArrayList<T> list = new ArrayList<T>();
		fromRBTtoInOrderList(root, list);
		return list;
	}

	private void fromRBTtoInOrderList(RedBlackTreeNode<T> node, ArrayList<T> list) {

		if (node.getLeftChild() != nil) {
			fromRBTtoInOrderList(node.getLeftChild(), list);
		}

		list.add(node.getKey());

		if (node.getRightChild() != nil) {
			fromRBTtoInOrderList(node.getRightChild(), list);
		}
	}

	/**
	 * From red black tree to array list adding with post order principle
	 * 
	 * @return The array list
	 */
	public ArrayList<T> toPostOrderList() {
		ArrayList<T> list = new ArrayList<T>();
		fromRBTtoPostOrderList(root, list);
		return list;
	}

	private void fromRBTtoPostOrderList(RedBlackTreeNode<T> node, ArrayList<T> list) {

		if (node.getLeftChild() != nil) {
			fromRBTtoPostOrderList(node.getLeftChild(), list);
		}

		if (node.getRightChild() != nil) {
			fromRBTtoPostOrderList(node.getRightChild(), list);
		}

		list.add(node.getKey());
	}
}
