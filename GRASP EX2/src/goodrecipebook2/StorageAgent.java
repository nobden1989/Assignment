package goodrecipebook2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StorageAgent {
	// FABRICATION --- new class not existing in domain model is
	// "fabricated" to store
	// and retrieve serialized objects
	private static final String filename = "data/Objects.ser";
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	public static boolean objectsExist() {
		File fn = new File(filename);
		return fn.exists();
	}

	private static void openFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream(filename));
		} catch (IOException ioException) {
			System.out.println("Error opening file");
		}
	}

	public static void SaveObjects(ArrayList<Object> objs) {
		openFile();
		try {
			for (Object obj : objs)
				output.writeObject(obj);
		} catch (IOException ioException) {
			System.out.println("Error writing to file");
			return;
		} catch (NoSuchElementException elementException) {
			System.out.println(elementException.getMessage());
		}
		closeFile();
	}

	private static void closeFile() {
		try {
			if (output != null)
				output.close();
		} catch (IOException ex) {
			System.out.println("Error closing file");
			System.exit(1);
		}
	}

	public static ArrayList<Object> ReadObjects() {
		openFile();
		ArrayList<Object> objs = new ArrayList<Object>();
		ObjectInputStream input_recipe = null;
		try {
			input_recipe = new ObjectInputStream(new FileInputStream(filename));
		} catch (IOException ex) {
			System.out.println("Error opening file");
		}
		;
		try {
			while (true) {
				objs.add(input.readObject());
			}
		} catch (Exception ex) {
			System.out.println("Error reading from file");
		}
		closeFile();
		return objs;
	}
}
