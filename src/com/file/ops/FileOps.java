package com.file.ops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Class FileOps.
 */
public class FileOps {

	/** The name. */
	private static String NAME = "filename";

	/** The count. */
	private static int count = 0;

	/** The file size limit in bytes. */
	private static int FILE_SIZE_LIMIT_IN_BYTES = 2000;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		incrementFileCount();
		for (int i = 0; i < 100; i++) {
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
		File myObj = new File(NAME + "_" + count);
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
			System.out.println("File already exists.");
		}

		Path path = Paths.get(NAME + "_" + count);
		long bytes = Files.size(path);
		System.out.println(String.format("%,d bytes", bytes));
		System.out.println(String.format("%,d kilobytes", bytes / 1024));
		if (bytes > FILE_SIZE_LIMIT_IN_BYTES) {
			// create new file.
			count++;
			write(logStatement, NAME + "_" + count);
		} else {
			// log in existing file.
			write(logStatement, NAME + "_" + count);
		}

	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	private static void incrementFileCount() {
		boolean fileExists = true;
		File f = new File(NAME + "_" + ++count);
		while (fileExists) {
			if (f.exists() && !f.isDirectory()) {
				f = new File(NAME + "_" + ++count);
			} else {
				fileExists = false;
			}
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
