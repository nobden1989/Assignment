package searchTrees.impl;

import searchTrees.ATree;

public class MyBinarySearchTree<AnyType extends Comparable<? super AnyType>>
		extends ATree<AnyType> {

	private BinaryNode<AnyType> root;
	private BinaryNode<AnyType> subRoot;

	private static final int CP_LESS = -1;
	private static final int CP_EQUAL = 0;
	private static final int CP_GREATER = 1;
	private static final int CP_NOTFOUND = -2;

	private static class BinaryNode<AnyType> {

		private AnyType element; // The data in the node
		private BinaryNode<AnyType> left; // Left child
		private BinaryNode<AnyType> right; // Right child

		private BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		private BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

	}

	public MyBinarySearchTree() {
		root = null;
	}

	@Override
	public boolean myContains(AnyType x) {

		BinaryNode<AnyType> curNode = root;

		while (null != curNode) {
			int result = CheckNode(curNode, x);
			if (CP_LESS == result) {
				curNode = curNode.left;
			} else if (CP_GREATER == result) {
				curNode = curNode.right;
			} else if (CP_EQUAL == result) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean myInsert(AnyType x) {
		if (null == root) {
			root = new BinaryNode<AnyType>(x, null, null);
			return true;
		}

		BinaryNode<AnyType> curNode = root;

		while (true) {
			int result = CheckNode(curNode, x);

			if (CP_LESS == result || CP_EQUAL == result) {
				if (null == curNode.left) {
					curNode.left = new BinaryNode<AnyType>(x, null, null);
					return true;
				} else {
					curNode = curNode.left;
				}
			} else if (CP_GREATER == result) {
				if (null == curNode.right) {
					curNode.right = new BinaryNode<AnyType>(x, null, null);
					return true;
				} else {
					curNode = curNode.right;
				}
			} else if (CP_NOTFOUND == result) {
				return false;
			}
		}
	}

	@Override
	public boolean myRemove(AnyType x) {
		// if(null == root){
		// return false;
		// }
		//
		// BinaryNode<AnyType> curNode = root;
		//
		// while (true) {
		// int result = CheckNode(curNode, x);
		//
		// if (CP_LESS == result) {
		// if (null == curNode.left) {
		// return false;
		// } else {
		// curNode = curNode.left;
		// }
		// } else if (CP_GREATER == result) {
		// if (null == curNode.right) {
		// return false;
		// } else {
		// curNode = curNode.right;
		// }
		// } else if (CP_EQUAL == result) {
		// return false;
		// }
		// }
		return false;
	}

	@Override
	public void printTree() {
		if (null == root)
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private void printTree(BinaryNode<AnyType> node) {
		if (node != null) {
			printTree(node.left);
			System.out.println(node.element);
			printTree(node.right);
		}
	}

	private int CheckNode(BinaryNode<AnyType> node, AnyType val) {
		if (null == node || null == node.element) {
			return CP_NOTFOUND;
		} else {
			int result = node.element.compareTo(val);
			if (result > 0) {
				return CP_LESS;
			} else if (result < 0) {
				return CP_GREATER;
			} else {
				return CP_EQUAL;
			}
		}
	}

}
