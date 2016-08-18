package org.altervista.scarrozzo.jredblacktree.rbtclasses;

import org.altervista.scarrozzo.jredblacktree.rbtclasses.RedBlackTreeNode.RBT_COLORS;

/**
 * 
 * @author Sergio Carrozzo
 * 
 *         This class contains utility methods for the typical operations used
 *         on a red black tree
 *
 */
public class RedBlackTreeUtils {

	/**
	 * Left rotation used for fixup the insertion phase
	 * 
	 * @param tree
	 *            The RBT
	 * @param node
	 *            The node on which rotate
	 */
	static <T extends Comparable<T>> void leftRotate(RedBlackTree<T> tree, RedBlackTreeNode<T> node) {
		RedBlackTreeNode<T> child = node.getRightChild();
		node.setRightChild(child.getLeftChild());
		if (child.getLeftChild() != tree.getNilNode()) {
			child.getLeftChild().setParent(node);
		}
		child.setParent(node.getParent());
		if (node.getParent() == tree.getNilNode()) {
			tree.setRootNode(child);
		} else if (node.equals(node.getParent().getLeftChild())) {
			node.getParent().setLeftChild(child);
		} else {
			node.getParent().setRightChild(child);
		}
		child.setLeftChild(node);
		node.setParent(child);
	}

	/**
	 * Right rotation used for fixup the insertion phase
	 * 
	 * @param tree
	 *            The RBT
	 * @param node
	 *            The node on which rotate
	 */
	static <T extends Comparable<T>> void rightRotate(RedBlackTree<T> tree, RedBlackTreeNode<T> node) {
		RedBlackTreeNode<T> child = node.getLeftChild();
		node.setLeftChild(child.getRightChild());
		if (child.getRightChild() != tree.getNilNode()) {
			child.getRightChild().setParent(node);
		}
		child.setParent(node.getParent());
		if (node.getParent() == tree.getNilNode()) {
			tree.setRootNode(child);
		} else if (node.equals(node.getParent().getLeftChild())) {
			node.getParent().setLeftChild(child);
		} else {
			node.getParent().setRightChild(child);
		}
		child.setRightChild(node);
		node.setParent(child);
	}

	/**
	 * The insert fixup method to preserve the RBT properties
	 * 
	 * @param tree
	 *            The RBT
	 * @param node
	 *            The inserted node
	 */
	static <T extends Comparable<T>> void rbtInsertFixup(RedBlackTree<T> tree, RedBlackTreeNode<T> node) {
		while (node.getParent().getColor().equals(RBT_COLORS.RED)) {
			if (node.getParent().equals(node.getParent().getParent().getLeftChild())) {
				RedBlackTreeNode<T> uncle = node.getParent().getParent().getRightChild();
				if (uncle.getColor().equals(RBT_COLORS.RED)) {
					node.getParent().setColor(RBT_COLORS.BLACK);
					uncle.setColor(RBT_COLORS.BLACK);
					node.getParent().getParent().setColor(RBT_COLORS.RED);
					node = node.getParent().getParent();
				} else {
					if (node.equals(node.getParent().getRightChild())) {
						node = node.getParent();
						leftRotate(tree, node);
					}
					node.getParent().setColor(RBT_COLORS.BLACK);
					node.getParent().getParent().setColor(RBT_COLORS.RED);
					rightRotate(tree, node.getParent().getParent());
				}
			} else {
				RedBlackTreeNode<T> uncle = node.getParent().getParent().getLeftChild();
				if (uncle.getColor().equals(RBT_COLORS.RED)) {
					node.getParent().setColor(RBT_COLORS.BLACK);
					uncle.setColor(RBT_COLORS.BLACK);
					node.getParent().getParent().setColor(RBT_COLORS.RED);
					node = node.getParent().getParent();
				} else {
					if (node.equals(node.getParent().getLeftChild())) {
						node = node.getParent();
						rightRotate(tree, node);
					}
					node.getParent().setColor(RBT_COLORS.BLACK);
					node.getParent().getParent().setColor(RBT_COLORS.RED);
					leftRotate(tree, node.getParent().getParent());
				}
			}
		}
		tree.getRootNode().setColor(RBT_COLORS.BLACK);
	}

	/**
	 * The transplant operation used on binary search and red black trees
	 * 
	 * @param tree
	 *            The red black tree
	 * @param oldNode
	 *            The node to remove
	 * @param newNode
	 *            The node that replaces the old node
	 */
	static <T extends Comparable<T>> void rbtTransplant(RedBlackTree<T> tree, RedBlackTreeNode<T> oldNode,
			RedBlackTreeNode<T> newNode) {
		if (oldNode.getParent().equals(tree.getNilNode())) {
			tree.setRootNode(newNode);
		} else if (oldNode.equals(oldNode.getParent().getLeftChild())) {
			oldNode.getParent().setLeftChild(newNode);
		} else {
			oldNode.getParent().setRightChild(newNode);
		}
		newNode.setParent(oldNode.getParent());
	}

	/**
	 * The delete fixup method to preserve the RBT properties
	 * 
	 * @param tree
	 *            The RBT
	 * @param node
	 *            The node to remove
	 */
	static <T extends Comparable<T>> void deleteFixup(RedBlackTree<T> tree, RedBlackTreeNode<T> node) {
		RedBlackTreeNode<T> x = tree.getNilNode();
		while (!node.equals(tree.getRootNode()) && node.getColor() == RBT_COLORS.BLACK) {
			if (node.equals(node.getParent().getLeftChild())) {
				x = node.getParent().getRightChild();
				if (x.getColor() == RBT_COLORS.RED) {
					x.setColor(RBT_COLORS.BLACK);
					node.getParent().setColor(RBT_COLORS.RED);
					leftRotate(tree, node.getParent());
					x = node.getParent().getRightChild();
				}
				if (x.getLeftChild().getColor() == RBT_COLORS.BLACK
						&& x.getRightChild().getColor() == RBT_COLORS.BLACK) {
					x.setColor(RBT_COLORS.RED);
					node = node.getParent();
				} else {
					if (x.getRightChild().getColor() == RBT_COLORS.BLACK) {
						x.getLeftChild().setColor(RBT_COLORS.BLACK);
						x.setColor(RBT_COLORS.RED);
						rightRotate(tree, x);
						x = node.getParent().getRightChild();
					}
					x.setColor(node.getParent().getColor());
					node.getParent().setColor(RBT_COLORS.BLACK);
					x.getRightChild().setColor(RBT_COLORS.BLACK);
					leftRotate(tree, node.getParent());
					node = tree.getRootNode();
				}
			}else{
				x = node.getParent().getLeftChild();
				if (x.getColor() == RBT_COLORS.RED) {
					x.setColor(RBT_COLORS.BLACK);
					node.getParent().setColor(RBT_COLORS.RED);
					rightRotate(tree, node.getParent());
					x = node.getParent().getLeftChild();
				}
				if (x.getRightChild().getColor() == RBT_COLORS.BLACK
						&& x.getLeftChild().getColor() == RBT_COLORS.BLACK) {
					x.setColor(RBT_COLORS.RED);
					node = node.getParent();
				} else {
					if (x.getLeftChild().getColor() == RBT_COLORS.BLACK) {
						x.getRightChild().setColor(RBT_COLORS.BLACK);
						x.setColor(RBT_COLORS.RED);
						leftRotate(tree, x);
						x = node.getParent().getLeftChild();
					}
					x.setColor(node.getParent().getColor());
					node.getParent().setColor(RBT_COLORS.BLACK);
					x.getLeftChild().setColor(RBT_COLORS.BLACK);
					rightRotate(tree, node.getParent());
					node = tree.getRootNode();
				}
			}
		}
		node.setColor(RBT_COLORS.BLACK);
	}
}
