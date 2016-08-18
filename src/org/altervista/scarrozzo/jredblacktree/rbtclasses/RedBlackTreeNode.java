package org.altervista.scarrozzo.jredblacktree.rbtclasses;

/**
 * 
 * @author Sergio Carrozzo
 *
 *         This class represent a node of a red black tree
 *
 * @param <T>
 *            The generic key for the node
 */
public class RedBlackTreeNode<T extends Comparable<T>> {

	public static enum RBT_COLORS {
		RED, BLACK
	};

	private RedBlackTreeNode<T> parent;
	private RedBlackTreeNode<T> leftChild;
	private RedBlackTreeNode<T> rightChild;
	private RBT_COLORS color;
	private T key;

	public RedBlackTreeNode(){
		this.color = RBT_COLORS.BLACK;
	}
	
	public RedBlackTreeNode(T key) {
		this.key = key;
		this.color = RBT_COLORS.BLACK;
	}

	public RedBlackTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(RedBlackTreeNode<T> parent) {
		this.parent = parent;
	}

	public RedBlackTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(RedBlackTreeNode<T> childLeft) {
		this.leftChild = childLeft;
	}

	public RedBlackTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(RedBlackTreeNode<T> childRight) {
		this.rightChild = childRight;
	}

	public RBT_COLORS getColor() {
		return color;
	}

	public void setColor(RBT_COLORS color) {
		this.color = color;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}
}
