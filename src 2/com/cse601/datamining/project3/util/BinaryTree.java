package com.cse601.datamining.project3.util;

public class BinaryTree {

	public Node root;
	Node root1;
	private int size = -1;

	public void addNode(double key, int column, String node, double leftLabel, double rightLabel) {

		Node newNode = new Node(key, column, node, leftLabel, rightLabel);

		if (root == null) {
			root = newNode;
		} else {

			Node focusNode = root;
			Node parent;

			while (true) {
				parent = focusNode;

				if (key < focusNode.key) {
					focusNode = focusNode.leftChild;
					if (focusNode == null) {
						parent.leftChild = newNode;//ISSUE reported by Aman. Column value changing instead of row number. 
						return;
					}

				} 
				if (key >= focusNode.key) {
					focusNode = focusNode.rightChild;
					if (focusNode == null) {
						parent.rightChild = newNode;
						return; // All Done
					}
				}
			}
		}
	}

	public void inOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println("Key: "+(float)focusNode.key);
			System.out.println("Col: "+focusNode.column);
			System.out.println(focusNode.node);
			System.out.println("Left: "+focusNode.leftLabel);
			System.out.println("Right: "+focusNode.rightLabel);
			System.out.print("\n");
			inOrderTraverseTree(focusNode.rightChild);
		}

	}

	public void preorderTraverseTree(Node focusNode) {

		if (focusNode != null) {
			System.out.print(focusNode.key + " ");
			preorderTraverseTree(focusNode.leftChild);
			preorderTraverseTree(focusNode.rightChild);
		}
	}

	public void postOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {
			postOrderTraverseTree(focusNode.leftChild);
			postOrderTraverseTree(focusNode.rightChild);
			System.out.print(focusNode.key + " ");

		}

	}


	public Node findNode(int key) {

		Node focusNode = root;

		while (focusNode.key != key) {

			if (key < focusNode.key) {
				focusNode = focusNode.leftChild;
			} else {
				focusNode = focusNode.rightChild;
			}

			if (focusNode == null)
				return null;
		}
		return focusNode;
	}
	
	
	public class Node {

		public double key;
		public int column;
		public String node;
		public double leftLabel;
		public double rightLabel;
		

		public Node leftChild;
		public Node rightChild;
		
		
		public Node(double key, int column, String node, double leftLabel, double rightLabel) {
			this.key = key;
			this.column = column;
			this.node = node;
			this.leftLabel = leftLabel;
			this.rightLabel = rightLabel;
		}
		
		public double getKey() {
			return key;
		}
		public void setKey(double key) {
			this.key = key;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		public String getNode() {
			return node;
		}
		public void setNode(String node) {
			this.node = node;
		}

		public double getLeftLabel() {
			return leftLabel;
		}

		public void setLeftLabel(double leftLabel) {
			this.leftLabel = leftLabel;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		

	}
	
	

	/*public static void main(String[] args) {

		BinaryTree theTree = new BinaryTree();

		theTree.addNode(50);
		theTree.addNode(25);
		theTree.addNode(15);
		theTree.addNode(30);
		theTree.addNode(75);
		theTree.addNode(85);

		// Different ways to traverse binary trees
		System.out.println("\nInOrder Traversal");
		theTree.inOrderTraverseTree(theTree.root);
		System.out.println("\nPreOrder Traversal");
		theTree.preorderTraverseTree(theTree.root);
		System.out.println("\nPostOrderTraversal");
		theTree.postOrderTraverseTree(theTree.root);

		// Find the node witsplitey 75
		System.out.println("\nNode with the key 75");
		System.out.println(theTree.findNode(75).key);

		for (int i = 0; i <= theTree.size; i++) {
			System.out.print(theTree.serial[i] + " ");
		}

		System.out.println("\n############ De-Serialized #############\n");
		theTree.createDeserializedTree();
		System.out.println("\nInOrder Traversal De-Serialized");
		theTree.inOrderTraverseTree(theTree.root);
		System.out.println("\nPreOrder Traversal De-Serialized");
		theTree.preorderTraverseTree(theTree.root);
		System.out.println("\nPostOrderTraversal De-Serialized");
		theTree.postOrderTraverseTree(theTree.root);

	}*/




}