package assignment;

import java.io.IOException;
import java.util.logging.FileHandler;

import memoryManagement.BTree;
import memoryManagement.FileHelper;
import memoryManagement.In;
import memoryManagement.IndexMinPQ;
import memoryManagement.Sort;
import memoryManagement.StdOut;

public class Assignment {

	private static final String DIR_FOLDER = System.getProperty("user.dir") + "\\data\\";
	private static final String DIR_SOURCEFILE = DIR_FOLDER + "RNA-seq-reads-1M.txt";
	private static final String DIR_OURFILE = DIR_FOLDER + "RNA-seq-reads-1M-Sorted.txt";
	private static final String DIR_OURFILEB = DIR_FOLDER + "B-Tree-Sorted.txt";

	private static final int FILECOUNT = 4;

	private static final long MAXMEMLEN = 1024 * 1024;

	public static void main(String[] args) {
		try {
			// Assignment_01();
			Assignment_02();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void Assignment_01() throws IOException {

		int lineCount = 0;

		// Split File
		In testFile = new In(DIR_SOURCEFILE);

		while (testFile.hasNextLine()) {
			lineCount++;
			testFile.readLine();
		}

		testFile.close();

		int fileLine = lineCount / FILECOUNT;
		int lastLines = lineCount % FILECOUNT;

		In inFile = new In(DIR_SOURCEFILE);

		StringBuffer sb = new StringBuffer();

		for (int k = 0; k < FILECOUNT; k++) {
			int writeLn = fileLine;

			if (k == 3) {
				writeLn += lastLines;
			}

			for (int i = 0; i < writeLn; i++) {
				sb.append(inFile.readLine().trim()).append("\n");
			}
			sb.setLength(sb.length() - 1);
			FileHelper.writeFile(sb, DIR_FOLDER + (char) ('A' + k) + ".txt");
			sb.setLength(0);
		}

		inFile.close();

		// Sort Files
		In[] inFiles = new In[FILECOUNT];

		for (int k = 0; k < FILECOUNT; k++) {
			inFiles[k] = new In(DIR_FOLDER + (char) ('A' + k) + ".txt");

			int fileLn = fileLine;
			if (k == 3) {
				fileLn += lastLines;
			}

			String[] lines = new String[fileLn];

			for (int i = 0; i < fileLn; i++) {
				sb.append(inFiles[k].readLine().trim()).append("\n");
				lines[i] = sb.toString();
				sb.setLength(0);
			}

			Sort.quicksort(lines);

			for (String s : lines) {
				sb.append(s);
			}

			FileHelper.writeFile(sb, DIR_FOLDER + (char) ('A' + k) + "S.txt");

			sb.setLength(0);

			inFiles[k].close();

		}

		// Merge sort files

		In[] sortFiles = new In[FILECOUNT];

		for (int k = 0; k < FILECOUNT; k++) {
			sortFiles[k] = new In(DIR_FOLDER + (char) ('A' + k) + "S.txt");
		}

		IndexMinPQ<String> pq = new IndexMinPQ<String>(FILECOUNT);
		for (int i = 0; i < FILECOUNT; i++) {
			if (!sortFiles[i].isEmpty())
				pq.insert(i, sortFiles[i].readString().trim());
		}

		// Create(Clear) output File
		FileHelper.writeFile(sb, DIR_OURFILE);

		// Extract and print min and read next from its stream.
		while (!pq.isEmpty()) {
			sb.append(pq.minKey()).append("\n");
			if (sb.length() > MAXMEMLEN) {
				FileHelper.appendFile(sb, DIR_OURFILE);
				sb.setLength(0);
			}
			int i = pq.delMin();
			if (!sortFiles[i].isEmpty())
				pq.insert(i, sortFiles[i].readString().trim());
		}

		if (sb.length() > 1) {
			sb.setLength(sb.length() - 1);
			FileHelper.appendFile(sb, DIR_OURFILE);
			sb.setLength(0);
		}

		for (int k = 0; k < FILECOUNT; k++) {
			sortFiles[k].close();
		}
	}

	private static void Assignment_02() throws IOException {
		BTree<String, String> bTree = new BTree<String, String>();

		In inFile = new In(DIR_SOURCEFILE);

		while (inFile.hasNextLine()) {
			bTree.put(inFile.readLine().trim(), "0");
		}

		StringBuffer sb = new StringBuffer();
		sb.append(bTree.travel());

		FileHelper.writeFile(sb, DIR_OURFILEB);

		inFile.close();

	}

}
