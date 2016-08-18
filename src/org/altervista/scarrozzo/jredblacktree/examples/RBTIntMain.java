package org.altervista.scarrozzo.jredblacktree.examples;

import org.altervista.scarrozzo.jredblacktree.rbtclasses.RedBlackTree;
import org.altervista.scarrozzo.jredblacktree.rbtclasses.RedBlackTreeNode;

public class RBTIntMain {
	public static void main(String args[]) {

		int[] testKeys = new int[] { 5, 12, -1, 67, 5, 4, 21, 45, 2, 3, 124, 300 };

		// create the RBT with only the root node
		RedBlackTree<Integer> rbt = new RedBlackTree<Integer>(10);

		// add the other nodes
		for (int value : testKeys) {
			rbt.insert(value);
		}

		// test print all nodes
		rbt.printPostOrderAllNodes();

		// test search
		RedBlackTreeNode<Integer> node = rbt.search(-1);
		if (node != null) {
			System.out.println("Found node with key: "+node.getKey());
		}else{
			System.out.println("Node not found");
		}
		
		// test max, min
		System.out.println("Max: "+rbt.getMaxNode().getKey());
		System.out.println("Min: "+rbt.getMinNode().getKey());
		
		// test successor, predecessor
		System.out.println("Successor of 21: "+rbt.treeSuccessor(rbt.search(21)).getKey());
		System.out.println("Predecessor of 21: "+rbt.treePredecessor(rbt.search(21)).getKey());
		
		// test delete
		rbt.delete(rbt.search(21));
		rbt.delete(rbt.getMinNode());
		rbt.printPostOrderAllNodes();

		// test to list casting
		System.out.println(rbt.toPreOrderList().toString());
		System.out.println(rbt.toInOrderList().toString());
		System.out.println(rbt.toPostOrderList().toString());
	}
}
