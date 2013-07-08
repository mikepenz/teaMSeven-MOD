package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Extra {

	public String FILE_CONTENT;
	public String[] SPLITTED_STRING;
	public String[] SPLIT_MULTIPLE_SEPARATORS;
	public String INSIDE_BRACKETS;

	/* This class contains some useful methods to make life easier 
	 * when you need to work with files:
	 * - read files content as String-array or ListArray
	 * - check if file exists (returns true or false)
	 */



	/*
	 * get file content
	 */

	public String ReadFileContent(File file) {
		FileInputStream fin1 = null;
		try {
			fin1 = new FileInputStream(file);
			byte fileContent[] = new byte[(int)file.length()];
			fin1.read(fileContent);
			FILE_CONTENT = new String(fileContent);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		}
		catch (IOException ioe) {
			System.out.println("Exception while reading file " + ioe);
		}
		finally {
			try {
				if (fin1 != null) {
					fin1.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
		return FILE_CONTENT;
	}

	/*
	 * check if file exists
	 */

	public boolean CheckIfFileExists(File file) {
		if(file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * Split strings and returns a String-array
	 */

	public String[] StringSpliter(String stringToSplit, String separator) {
		SPLITTED_STRING = stringToSplit.split(separator);
		
		return SPLITTED_STRING;	
	}

	/*
	 * get String-array from a file that contains multiple separators or brackets
	 */
	public String[] ExtraStringSplitter(File file, String first_separator, String last_separator) {
		String content = ReadFileContent(file);
		SPLIT_MULTIPLE_SEPARATORS = content.replace(first_separator, "").replace(last_separator, "").split(" ");

		return SPLITTED_STRING;
	}
	
	/*
	 * retrieve the value between brackets
	 */
	
	public String IsInsideBranckets(File file, String first_separator, String last_separator) {
		String content = ReadFileContent(file);
		int bropen = content.indexOf(first_separator);
		int brclose = content.lastIndexOf(last_separator);
		INSIDE_BRACKETS = content.substring(bropen + 1, brclose);	
		
		return INSIDE_BRACKETS;
	}

	
}
