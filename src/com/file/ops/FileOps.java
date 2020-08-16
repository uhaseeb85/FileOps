package com.file.ops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * The Class FileOps.
 */
public class FileOps {

	/** The name. */
	private static String NAME = "filename";

	/** The random string. */
	private static String randomString;

	/** The count. */
	private static int count = 1;

	static {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		randomString = "_" + generatedString + "_";
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 1000; i++) {
			log("my Sample Log");
		}
	}

	/**
	 * Log.
	 *
	 * @param logStatement the log statement
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void log(String logStatement) throws IOException {
		
		File myObj = new File(NAME + randomString + count);
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
			System.out.println("File already exists.");
		}

		Path path = Paths.get(NAME + randomString + count);
		long bytes = Files.size(path);
		System.out.println(String.format("%,d bytes", bytes));
		System.out.println(String.format("%,d kilobytes", bytes / 1024));
		if (bytes > 20000) {
			// create new file.
			count++;
			write(logStatement, NAME + randomString + count);
		} else {
			// log in existing file.
			write(logStatement, NAME + randomString + count);
		}

	}

	/**
	 * Write.
	 *
	 * @param logStatement the log statement
	 * @param fileName     the file name
	 * @return true, if successful
	 */
	public static boolean write(String logStatement, String fileName) {
		try (FileWriter fw = new FileWriter(fileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("the text ::" + logStatement);
			// more code
			out.println("more text" + logStatement);
			// more code
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
		return true;
	}
}
