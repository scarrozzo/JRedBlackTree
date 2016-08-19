# JRedBlackTree
## A java implementation of a red black tree

### Description

This is a little library allowing you to create a red black tree data structure. A red black tree data structure guarantee searching in O(log n) time, as well as also for the insertion and deletion operations. 

In this repository you can find the eclipse project and the jar file that you can import in your projects. The creation and the other methods implemented are described in the javadoc under the doc folder.

### Technical notes
The main class for the creation and the manipulation of a red black tree is the "RedBlackTree.java", in which the most typical operations on a red black tree are been implemented (search, insert, delete, treeSuccessor, trePredecessor, min, max, print in-order/pre-order/post-order, etc.). The class was implemented through java generic types.

### Usage examples

    int[] testKeys = new int[] { 5, 12, -1, 67, 5, 4, 21, 45, 2, 3, 124, 300 };
  
		// create the RBT with only the root node
		RedBlackTree<Integer> rbt = new RedBlackTree<Integer>(10);

		// add the other nodes
		for (int value : testKeys) {
			rbt.insert(value);
		}

		// test print all nodes with post order principle
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

		// test casting from rbt to ArrayList
		System.out.println(rbt.toPreOrderList().toString());
		System.out.println(rbt.toInOrderList().toString());
		System.out.println(rbt.toPostOrderList().toString());



For more information on red black trees see: https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
