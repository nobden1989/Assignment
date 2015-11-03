package assignment;

import java.util.Random;

import hashTable.AHashTable;
import hashTable.impl.CuckooHashTable;
import hashTable.impl.QuadraticProbingHashTable;
import hashTable.impl.SeparateChainingHashTable;
import hashTable.impl.StringHashFamily;
import interfaces.ICollection;
import searchTrees.ATree;
import searchTrees.impl.AVLTree;
import searchTrees.impl.BinarySearchTree;
import searchTrees.impl.RedBlackBST;
import searchTrees.impl.SplayTree;

/**
 * Assignment class
 * 
 * @author Xiaoliang Tang
 */
public class Assignment {

	/**
	 * Main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Assignment 1-3.
		assignment1to3();

		// Assignment 4-6.
		assignment4to6();
	}

	static int count = 0;

	/**
	 * Assignment 1-3
	 */
	private static void assignment1to3() {

		// Random access test for CuckooHashTable
		CuckooHashTable<String> cHTable = new CuckooHashTable<String>(new StringHashFamily(3),
				2000);
		RunHashTableFullRandomTest(cHTable, 20);

		cHTable = new CuckooHashTable<String>(new StringHashFamily(3), 2000);
		RunHashTableRandomTest(cHTable, 20);

		// Random access test for QuadraticProbingHashTable
		QuadraticProbingHashTable<String> qHTable = new QuadraticProbingHashTable<String>(2000);
		RunHashTableFullRandomTest(qHTable, 20);

		qHTable = new QuadraticProbingHashTable<String>(2000);
		RunHashTableRandomTest(qHTable, 20);

		// Random access test for SeparateChainingHashTable
		SeparateChainingHashTable<String> sHTable = new SeparateChainingHashTable<>(2000);
		RunHashTableFullRandomTest(sHTable, 20);

		sHTable = new SeparateChainingHashTable<>(2000);
		RunHashTableRandomTest(sHTable, 20);

	}

	/**
	 * Assignment 4-6
	 */
	private static void assignment4to6() {

		// Static method need time to initialize for the first run,
		// that takes additional time we don't want to count in Tree Test,
		// so I run one empty test to initial the static ones.
		BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();
		RunOrderdTreeTest(testTree, 1);
		testTree = new BinarySearchTree<Integer>();
		RunRandomTreeTest(testTree, 1);
		testTree = new BinarySearchTree<Integer>();
		RunFullRandomTreeTest(testTree, 1);

		int NUMS = 100000;

		// BinarySearchTree Test
		BinarySearchTree<Integer> bsTree = new BinarySearchTree<Integer>();
		RunOrderdTreeTest(bsTree, NUMS);
		bsTree = new BinarySearchTree<Integer>();
		RunRandomTreeTest(bsTree, NUMS);
		bsTree = new BinarySearchTree<Integer>();
		RunFullRandomTreeTest(bsTree, NUMS);

		// AVLTree Test
		AVLTree<Integer> avlTree = new AVLTree<Integer>();
		RunOrderdTreeTest(avlTree, NUMS);
		AVLTree<Integer> avblTree = new AVLTree<Integer>();
		RunOrderdTreeTest(avblTree, NUMS);
		AVLTree<Integer> cvlTree = new AVLTree<Integer>();
		RunRandomTreeTest(cvlTree, NUMS);
		avlTree = new AVLTree<Integer>();
		RunFullRandomTreeTest(avlTree, NUMS);

		// RedBlackBST Test
		RedBlackBST<Integer, Integer> rbTree = new RedBlackBST<Integer, Integer>();
		RunOrderdTreeTest(rbTree, NUMS);
		rbTree = new RedBlackBST<Integer, Integer>();
		RunRandomTreeTest(rbTree, NUMS);
		rbTree = new RedBlackBST<Integer, Integer>();
		RunFullRandomTreeTest(rbTree, NUMS);

		// SplayTree Test
		SplayTree<Integer> sTree = new SplayTree<Integer>();
		RunOrderdTreeTest(sTree, NUMS);
		sTree = new SplayTree<Integer>();
		RunRandomTreeTest(sTree, NUMS);
		sTree = new SplayTree<Integer>();
		RunFullRandomTreeTest(sTree, NUMS);

	}

	/**
	 * Run full random hash table test and print result
	 * 
	 * @param table
	 *            Any type of hash tree.
	 * @param expt
	 *            the power of 2.
	 */
	private static void RunHashTableFullRandomTest(AHashTable<String> table, int expt) {
		count = 0;
		Random rd = new Random();
		// Random access test for SeparateChainingHashTable
		for (int i = 1; i <= expt; i++) {
			count++;

			int NUMS = (int) Math.pow(2, Double.valueOf(i));
			String[] testData = new String[NUMS];
			for (int j = 0; j < NUMS; j++) {
				testData[j] = String.valueOf(rd.nextInt(NUMS));
			}

			RunTest(table, testData);

			// Static method need time to initialize for the first run,
			// that takes additional time we don't want to count in HashTable
			// Test,
			// so I run the first test twice to obtain the accurate time of N =
			// 2.
			if (count == 1) {
				i--;
			}
		}
	}

	/**
	 * Run random hash table test and print result
	 * 
	 * @param table
	 *            Any type of hash tree.
	 * @param expt
	 *            the power of 2.
	 */
	private static void RunHashTableRandomTest(AHashTable<String> table, int expt) {
		count = 0;
		Random rd = new Random();
		// Random access test for SeparateChainingHashTable
		for (int i = 1; i <= expt; i++) {

			count++;

			int NUMS = (int) Math.pow(2, Double.valueOf(i));
			String[] testData = new String[NUMS];
			String[] testData2 = new String[NUMS];

			for (int j = 0; j < NUMS; j++) {
				testData[j] = String.valueOf(rd.nextInt(NUMS));
				testData2[j] = String.valueOf(rd.nextInt(NUMS));
			}

			RunInsertTest(table, testData);
			RunContainsTest(table, testData2);
			RunRemoveTest(table, testData2);

			// Static method need time to initialize for the first run,
			// that takes additional time we don't want to count in HashTable
			// Test,
			// so I run the first test twice to obtain the accurate time of N =
			// 2.
			if (count == 1) {
				i--;
			}
		}
	}

	/**
	 * Run ordered tree test and print result
	 * 
	 * @param tree
	 *            Any type of search tree.
	 * @param size
	 *            the length of test data.
	 */
	private static void RunOrderdTreeTest(ATree<Integer> tree, int size) {
		count = 1;
		Integer[] testData = new Integer[size];
		Integer[] testData2 = new Integer[size];

		// Ordered data for insert and reversed ordered data for delete
		for (int i = 1; i <= size; i++) {
			testData[i - 1] = i;
			testData2[i - 1] = size - i + 1;
		}

		Integer[] testData3 = new Integer[size];
		Random rd = new Random();

		// Random data for search
		for (int i = 1; i <= size; i++) {
			testData3[i - 1] = rd.nextInt(size) + 1;
		}

		RunInsertTest(tree, testData);
		RunContainsTest(tree, testData3);
		RunRemoveTest(tree, testData2);
	}

	/**
	 * Run full random tree test and print result
	 * 
	 * @param tree
	 *            Any type of search tree.
	 * @param size
	 *            the length of test data.
	 */
	private static void RunFullRandomTreeTest(ATree<Integer> tree, int size) {
		count = 1;
		Integer[] testData = new Integer[size];
		Random rd = new Random();
		// Random access test for SeparateChainingHashTable
		for (int i = 1; i <= size; i++) {
			testData[i - 1] = rd.nextInt(size) + 1;
		}
		RunTest(tree, testData);
	}

	/**
	 * Run random tree test and print result
	 * 
	 * @param tree
	 *            Any type of search tree.
	 * @param size
	 *            the length of test data.
	 */
	private static void RunRandomTreeTest(ATree<Integer> tree, int size) {
		count = 1;
		Integer[] testData = new Integer[size];
		Integer[] testData2 = new Integer[size];
		Random rd = new Random();
		// Random access test for SeparateChainingHashTable
		for (int i = 1; i <= size; i++) {
			testData[i - 1] = rd.nextInt(size) + 1;
			testData2[i - 1] = rd.nextInt(size) + 1;
		}
		RunInsertTest(tree, testData);
		RunContainsTest(tree, testData2);
		RunRemoveTest(tree, testData2);
	}

	/**
	 * Run Insert test and print result
	 * 
	 * @param hCollection
	 *            Any type of hash table and search tree.
	 * @param testData
	 *            An array of test data.
	 */
	private static <AnyType> void RunInsertTest(ICollection<AnyType> hCollection,
			AnyType[] testData) {

		long NUMS = testData.length;

		// insert test
		long insertStart = System.nanoTime();
		for (int i = 0; i < NUMS; i++) {
			hCollection.myInsert(testData[i]);
		}
		long insertTime = System.nanoTime() - insertStart;
		double insertAvg = (double) insertTime / NUMS;

		// print result
		StringBuilder str = new StringBuilder();

		// Stander output
		// str.append(hCollection.getType()).append(" Test No.").append(count -
		// 1).append(" N = ")
		// .append(NUMS);
		// str.append(" Insert ").append(insertTime).append(" Insert Avg
		// ").append(insertAvg);

		// Output for data Collect
		str.append(hCollection.getType()).append(" ").append(NUMS).append(" ").append(insertTime)
				.append(" ").append(insertAvg);

		System.out.println(str);
	}

	/**
	 * Run search test and print result
	 * 
	 * @param hCollection
	 *            Any type of hash table and search tree.
	 * @param testData
	 *            An array of test data.
	 */
	private static <AnyType> void RunContainsTest(ICollection<AnyType> hCollection,
			AnyType[] testData) {

		long NUMS = testData.length;

		// contains test
		long containStart = System.nanoTime();
		for (int i = 0; i < NUMS; i++) {
			hCollection.myContains(testData[i]);
		}
		long containTime = System.nanoTime() - containStart;
		double containAvg = (double) containTime / NUMS;

		// print result
		StringBuilder str = new StringBuilder();

		// Stander output
		// str.append(hCollection.getType()).append(" Test No.").append(count -
		// 1).append(" N = ")
		// .append(NUMS);
		// str.append("Contains ").append(containTime).append(" Contains Avg
		// ").append(containAvg);

		// Output for data Collect
		str.append(hCollection.getType()).append(" ").append(NUMS).append(" ").append(containTime)
				.append(" ").append(containAvg);

		System.out.println(str);
	}

	/**
	 * Run Remove test and print result
	 * 
	 * @param hCollection
	 *            Any type of hash table and search tree.
	 * @param testData
	 *            An array of test data.
	 */
	private static <AnyType> void RunRemoveTest(ICollection<AnyType> hCollection,
			AnyType[] testData) {

		long NUMS = testData.length;

		// remove test
		long removeStart = System.nanoTime();
		for (int i = 0; i < NUMS; i++) {
			hCollection.myRemove(testData[i]);
		}
		long removeTime = System.nanoTime() - removeStart;
		double removeAvg = (double) removeTime / NUMS;

		// print result
		StringBuilder str = new StringBuilder();

		// Stander output
		// str.append(hCollection.getType()).append(" Test No.").append(count -
		// 1).append(" N = ")
		// .append(NUMS);
		// str.append("Remove ").append(removeTime).append(" Remove Avg
		// ").append(removeAvg);

		// Output for data collect
		str.append(hCollection.getType()).append(" ").append(NUMS).append(" ").append(removeTime)
				.append(" ").append(removeAvg);

		System.out.println(str);
	}

	/**
	 * Run All test and print result
	 * 
	 * @param hCollection
	 *            Any type of hash table and search tree.
	 * @param testData
	 *            An array of test data.
	 */
	private static <AnyType> void RunTest(ICollection<AnyType> hCollection, AnyType[] testData) {

		long NUMS = testData.length;

		// insert test
		long insertStart = System.nanoTime();
		for (int i = 0; i < NUMS; i++) {
			hCollection.myInsert(testData[i]);
		}
		long insertTime = System.nanoTime() - insertStart;
		double insertAvg = (double) insertTime / NUMS;

		// contains test
		long containStart = System.nanoTime();
		for (int i = 0; i < NUMS; i++) {
			hCollection.myContains(testData[i]);
		}
		long containTime = System.nanoTime() - containStart;
		double containAvg = (double) containTime / NUMS;

		// remove test
		long removeStart = System.nanoTime();
		for (int i = 0; i < NUMS; i++) {
			hCollection.myRemove(testData[i]);
		}
		long removeTime = System.nanoTime() - removeStart;
		double removeAvg = (double) removeTime / NUMS;

		// print result
		StringBuilder str = new StringBuilder();

		// Stander output
		// str.append(hCollection.getType()).append(" Test No.").append(count -
		// 1).append(" N = ")
		// .append(NUMS);
		// str.append(" Insert ").append(insertTime).append(" Insert Avg
		// ").append(insertAvg)
		// .append(" ");
		// str.append("Contains ").append(containTime).append(" Contains Avg
		// ").append(containAvg)
		// .append(" ");
		// str.append("Remove ").append(removeTime).append(" Remove Avg
		// ").append(removeAvg);

		// Output for data collect
		str.append(hCollection.getType()).append(" ").append(NUMS).append(" ").append(insertTime)
				.append(" ").append(containTime).append(" ").append(removeTime).append(" ")
				.append(insertAvg).append(" ").append(containAvg).append(" ").append(removeAvg);

		System.out.println(str);
	}
}
