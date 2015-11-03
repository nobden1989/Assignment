package assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import Data.Pair;
import heaps.BinomialQueue;
import searchTrees.impl.SplayTree;
import util.FileHepler;

/**
 * @author Xiaoliang, Tang
 *
 */
public class AssignmentPlus {

	// File Directory
	public static final String STR_FILEDIR = System.getProperty("user.dir") + "\\Text";

	// Test Driver
	public static void main(String[] args) throws IOException {

		// SearchPages(STR_FILEDIR, new String[] { "order", "the" }, 10);
		// FindFrequentWords(STR_FILEDIR, false, 10);
		SpellChecker(STR_FILEDIR, System.getProperty("user.dir") + "\\Target");
	}

	/**
	 * Assignment 8 Convert Html Content into a Map.
	 * 
	 * @param file
	 *            Input File
	 * @param strBuilder
	 *            a buffer to read the strings in the file
	 * @param hTable
	 *            a Map that store the Frequency of words, i/o param.
	 *
	 */
	public static void HtmlWordCounter(File file, StringBuilder strBuilder,
			Map<String, Integer> hTable) {

		strBuilder.setLength(0);

		try {
			FileHepler.readToBuffer(strBuilder, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringTokenizer strToken = new StringTokenizer(strBuilder.toString(),
				" ~!@#$%^&*()_+-={}|[]\\:;\'\"<>?,./\t\n\r\f", false);
		while (strToken.hasMoreTokens()) {
			String word = strToken.nextToken();
			if (word.length() > 0) {
				if (hTable.containsKey(word)) {
					hTable.put(word, hTable.get(word) + 1);
				} else {
					hTable.put(word, 1);
				}
			}
		}
	}

	/**
	 * 
	 * Assignment 9
	 * 
	 * Search and print the top N best matches pages
	 * 
	 * @param file
	 *            Input File
	 * @param words
	 *            matched words
	 * @param topN
	 *            the amount of pages shown
	 *
	 */
	public static void SearchPages(String dir, String[] words, int topN) {

		BinomialQueue<Pair<String, Integer>> bQueue = new BinomialQueue<>();

		List<File> fileList = FileHepler.getFileList(STR_FILEDIR);

		StringBuilder strBuilder = new StringBuilder();

		for (File file : fileList) {
			int score = 0;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			HtmlWordCounter(file, strBuilder, map);
			for (String word : words) {

				if (map.containsKey(word)) {
					score += map.get(word);
				}
			}
			bQueue.insert(new Pair<String, Integer>(file.getName(), score, Pair.CMP_MIN));

		}

		strBuilder.setLength(0);
		strBuilder.append("Search Words: ");
		for (String word : words) {
			strBuilder.append(word).append(", ");
		}
		strBuilder.delete(strBuilder.length() - 2, strBuilder.length() - 1);
		System.out.println(strBuilder);

		for (int i = 0; i < topN; i++) {
			System.out.println(bQueue.deleteMin());
		}
	}

	/**
	 * 
	 * Assignment 10
	 * 
	 * Create a SplayTree that store the Web Dictionary
	 * 
	 * @param dir
	 *            File directory
	 * @param orderType
	 *            Pair.CMP_MAX:Show most frequent words.Pair.CMP_MIN:Show least
	 *            frequent words Pair.CMP_KEY: ordered by words, asc.
	 * @return SplayTree a SplayTree that store the frequency of the words
	 *         counted in pages
	 *
	 */
	public static SplayTree<Pair<String, Integer>> CreateDictionary(String dir, int orderType) {

		List<File> fileList = FileHepler.getFileList(STR_FILEDIR);
		Hashtable<String, Integer> hTable = new Hashtable<String, Integer>();
		for (File file : fileList) {
			HtmlWordCounter(file, new StringBuilder(), hTable);
		}

		SplayTree<Pair<String, Integer>> sTree = new SplayTree<Pair<String, Integer>>();

		for (String key : hTable.keySet()) {
			sTree.insert(new Pair<String, Integer>(key, hTable.get(key)));
		}

		return sTree;
	}

	/**
	 * 
	 * Assignment 12
	 * 
	 * Create a SplayTree that store the Web Dictionary with out frequency
	 * counter
	 * 
	 * @param dir
	 *            File directory
	 * @param orderType
	 *            Pair.CMP_MAX:Show most frequent words.Pair.CMP_MIN:Show least
	 *            frequent words Pair.CMP_KEY: ordered by words, asc.
	 * @return SplayTree a SplayTree that store the frequency of the words
	 *         counted in pages
	 *
	 */
	public static SplayTree<String> CreateDictionary(String dir) {

		List<File> fileList = FileHepler.getFileList(STR_FILEDIR);
		Hashtable<String, Integer> hTable = new Hashtable<String, Integer>();
		for (File file : fileList) {
			HtmlWordCounter(file, new StringBuilder(), hTable);
		}

		SplayTree<String> sTree = new SplayTree<String>();

		for (String key : hTable.keySet()) {
			sTree.insert(key);
		}

		return sTree;
	}

	/**
	 * 
	 * Assignment 11
	 * 
	 * Find N most/least frequent words in given pages
	 * 
	 * @param dir
	 *            File directory
	 * @param orderType
	 *            Pair.CMP_MAX:Show most frequent words.Pair.CMP_MIN:Show least
	 *            frequent words Pair.CMP_KEY: ordered by words, asc.
	 * @param topN
	 *            the amount of words shown
	 *
	 */
	public static void FindFrequentWords(String dir, int orderType, int topN) {

		SplayTree<Pair<String, Integer>> sTree = CreateDictionary(dir, orderType);

		for (int i = 0; i < topN; i++) {
			Pair<String, Integer> p = sTree.findMin();
			System.out.println(p);
			sTree.remove(p);
		}
	}

	/**
	 * 
	 * Assignment 12
	 * 
	 * Find words only exist in dir2's file
	 * 
	 * @param dir
	 *            File directory
	 * @param dir2
	 *            Dir of Page(s) to be checked
	 *
	 */
	public static void SpellChecker(String dir, String dir2) {

		SplayTree<String> sTree = CreateDictionary(dir);

		Hashtable<String, Integer> hTable = new Hashtable<>();

		List<File> fileList = FileHepler.getFileList(dir2);

		for (File file : fileList) {
			HtmlWordCounter(file, new StringBuilder(), hTable);
		}

		List<String> newWords = new ArrayList<String>();

		for (String key : hTable.keySet()) {

			if (!sTree.contains(key)) {
				sTree.insert(key);
				newWords.add(key);
			}
		}

		for (String word : newWords) {
			System.out.println(word);
		}

	}

}
