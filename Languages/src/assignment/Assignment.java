package assignment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import util.FileHelper;

public class Assignment {

	private static final String HTML_PATH = System.getProperty("user.dir") + "\\Html Files";
	private static final String DEST_PATH = System.getProperty("user.dir") + "\\Text\\";
	private static final String RESULT_PATH = System.getProperty("user.dir") + "\\RegexResult\\";

	public static void main(String[] args) throws IOException {
		// Assignment_01();

		// Assignment_02();
		Assignment_03();
	}

	private static void Assignment_01() throws IOException {

		List<File> fileList = FileHelper.getFileList(HTML_PATH);
		StringBuilder sb = new StringBuilder();
		for (File file : fileList) {
			org.jsoup.nodes.Document doc = Jsoup.parse(FileHelper.readToBuffer(sb, file));
			FileHelper.writeFile(doc.text(), DEST_PATH + file.getName().replace(".htm", ".txt"));
		}

	}

	private static void Assignment_02() throws IOException {
		List<File> fileList = FileHelper.getFileList(DEST_PATH);
		StringBuilder sb = new StringBuilder();
		for (File file : fileList) {

			String phonePt = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
			String emailPt = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}";

			// Create a Pattern object
			Pattern r = Pattern.compile(phonePt);
			Pattern r2 = Pattern.compile(emailPt);

			// Now create matcher object.
			String fileString = FileHelper.readToBuffer(sb, file);
			Matcher m = r.matcher(fileString);
			Matcher m2 = r2.matcher(fileString);
			while (m.find()) {
				System.out.println("File: " + file.getName());
				System.out.println("Found value: " + m.group(0) + " at " + m.start(0));
			}
			while (m2.find()) {
				System.out.println("File: " + file.getName());
				System.out.println("Found value: " + m2.group(0) + " at " + m2.start(0));
			}
		}

	}

	private static void Assignment_03() throws IOException {
		List<File> fileList = FileHelper.getFileList(HTML_PATH);
		StringBuilder sb = new StringBuilder();
		
		FileHelper.writeFile("", RESULT_PATH + "W3 Result.txt");
		FileHelper.writeFile("", RESULT_PATH + "Folders Result.txt");
		FileHelper.writeFile("", RESULT_PATH + "References Result.txt");
		FileHelper.writeFile("", RESULT_PATH + "Domain Result.txt");
		
		for (File file : fileList) {

			String w3 = "(http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]*w3\\.org(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@$%^&*+?:_/=]*(?<!\\.)*)*";
			String folders = "(http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@$%^&*+?:_/=]*(?<!\\.)*)*";
			String references = "(http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-]+)*(/#[a-zA-Z0-9\\.-~_/]+(?<!\\.)*)+";
			String domain = "(http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.(net|org|com)(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@$%^&*+?:_/=]*(?<!\\.)*)*";

			// Create a Pattern object
			Pattern pW3 = Pattern.compile(w3);
			Pattern pFolders = Pattern.compile(folders);
			Pattern pRef = Pattern.compile(references);
			Pattern pDomain = Pattern.compile(domain);

			// Now create matcher object.
			String fileString = FileHelper.readToBuffer(sb, file);
			Matcher mW3 = pW3.matcher(fileString);
			Matcher mFolders = pFolders.matcher(fileString);
			Matcher mRef = pRef.matcher(fileString);
			Matcher mDomain = pDomain.matcher(fileString);
			
			String fileName = file.getName();
			
			sb.setLength(0);
			sb.append("File: ").append(fileName).append("\n");
			while (mW3.find()) {
				sb.append("\tFound value: ").append(mW3.group(0)).append(" at ").append(mW3.start(0)).append("\n");
			}
			FileHelper.appendFile(sb.toString(), RESULT_PATH + "W3 Result.txt");
			
			sb.setLength(0);
			sb.append("File: ").append(fileName).append("\n");
			while (mFolders.find()) {
				sb.append("\tFound value: ").append(mFolders.group(0)).append(" at ").append(mFolders.start(0)).append("\n");
			}
			FileHelper.appendFile(sb.toString(), RESULT_PATH + "Folders Result.txt");
			
			sb.setLength(0);
			sb.append("File: ").append(fileName).append("\n");
			while (mRef.find()) {
				sb.append("\tFound value: ").append(mRef.group(0)).append(" at ").append(mRef.start(0)).append("\n");
			}
			
			FileHelper.appendFile(sb.toString(), RESULT_PATH + "References Result.txt");
			
			sb.setLength(0);
			sb.append("File: ").append(fileName).append("\n");
			while (mDomain.find()) {
				sb.append("\tFound value: ").append(mDomain.group(0)).append(" at ").append(mDomain.start(0)).append("\n");
			}
			FileHelper.appendFile(sb.toString(), RESULT_PATH + "Domain Result.txt");
		}

	}

}
