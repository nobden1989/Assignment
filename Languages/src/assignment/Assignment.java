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

	public static void main(String[] args) throws IOException {
		// Assignment_01();

		Assignment_02();
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
		      while (m.find( )) {
		    	  System.out.println("File: "+ file.getName());
		          System.out.println("Found value: " + m.group(0) + " at " + m.start(0));
		      } 
		      while (m2.find( )) {
		    	  System.out.println("File: "+ file.getName());
		          System.out.println("Found value: " + m2.group(0) + " at " + m2.start(0));
		      } 
		}
		
	}

}
