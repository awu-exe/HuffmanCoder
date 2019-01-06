package Coders;

// Imports
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Encoder {
	// MARK: Properties
	
	/**
	 * Stores the frequency of chars in a file
	 * Looks like: "character number" for each node
	 */
	private ArrayList<String> charFrequencyList;
	// Stores the generated keys associated with chars
	private ArrayList<String> charKeyList;
	private int totalChars;
	
	// MARK: Constructor
	
	public Encoder() {
		charFrequencyList = new ArrayList<>();
		charKeyList = new ArrayList<>();
		totalChars = 0;
	}// End of Encoder constructor

	// MARK: Private Methods

	/**
	 * findCharFrequency 
	 * Finds the frequency of characters given a string
	 * @Param: str as a String 
	 * Returns nothing
	 */
	private void findCharFrequency(String str) {
		// Helper variables
		char target;
		// stores the amount of times "target" appears in str
		int count = 0;
		String temp = "";
		String result = "";

		// find frequencies of characters
		while (str.length() > 0) {
			/*
			 * Pick out the first character to find frequency for Str will "shrink" each
			 * iteration so target always has a new char
			 */
			target = str.charAt(0);
			// Run through chars in str and find the occurrence of target
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == target) {
					count++;
				} else {
					// In the end this will store the original str without any target chars
					temp += str.charAt(i);
				}
			} // End of for loop

			// Add frequency to arraylist
			charFrequencyList.add(str.charAt(0) + Integer.toString(count));
			// Update the total amount of characters
			totalChars += count;
			//reset variables for next iteration
			count = 0;
			temp = "";
			// Update str so that it does not contain the target char anymore
			str = temp;
		} // End of while loop
	}// End of findCharFrequency function
	
	/**
	 * createCharTree
	 * Creates a binary tree-like structure based on char frequencies, represented with brackets/commas
	 * @Param: nothing
	 * Returns a binary tree represented as a String
	 */
	private String createCharTree() {
		// Return immediately if only one char and assign it with key 0
		if(charFrequencyList.size() == 1) {
			// Returns the first ArrayList node and the char part in the node
			return "0" + charFrequencyList.get(0).charAt(0);
		} else if(charFrequencyList.isEmpty()) {
			// Return nothing if the list has nothing
			return "";
		}
		
		// Parse charFrequencyList ArrayList
		ArrayList<Integer> frequencyList = new ArrayList<>();
		ArrayList<String> charList = new ArrayList<>();
		for(int i = 0;i < charFrequencyList.size();i++) {
			// Gets the frequency for the ith char inside the original frequency ArrayList
			frequencyList.add(Integer.parseInt(charFrequencyList.get(i).substring(1)));
			// Gets the char at the ith node
			charList.add(charFrequencyList.get(i).substring(0,1));
		}
		
		/*
		 * Loops until all chars are placed into the first index of charList.
		 * Eventually frequencyList will only contain one Node and the frequency
		 * in that node will equal totalChar.
		 */
		while(totalChars != frequencyList.get(0)) {
			// Sort in ascending order using insertion sort
			// We combine the lowest frequency chars together first
			// and turn them into one node
			for(int i = 1;i < frequencyList.size();i++) {
				int freqKey = frequencyList.get(i);
				String charKey = charList.get(i);
				int j = i-1;
				
				while(j >= 0 && frequencyList.get(j) > freqKey) {
					// list[j+1] = list[j]
					frequencyList.set(j+1, frequencyList.get(j));
					charList.set(j+1, charList.get(j));
					j--;
				}// End of while loop
				frequencyList.set(j+1, freqKey);
				charList.set(j+1, charKey);
			}// End of for loop
			
			
			// Stores a combined version of charList(0) and charList(1)
			charList.set(1, "(" + charList.get(0) + "," + charList.get(1) + ")");
			// Stores a combined of version of frequencyList(0) and frequencyList(1)
			frequencyList.set(1, frequencyList.get(0) + frequencyList.get(1));
			// Remove old values
			charList.remove(0);
			frequencyList.remove(0);
		}// End of while loop
		
		// Return String version of the tree without extra brackets
		return charList.get(0);
	}// End of createCharTree function
	
	/**
	 * treeToKey
	 * A recursive method that takes a built tree String and creates a String that contains the
	 * keys of the each char.
	 * @param tree as a String. Also takes in the # of 0/1 before the node (basically
	 * Helps determine the level of the tree), need this for recursion.
	 * Returns String
	 */
	private String treeToKey(String tree, String priorLevels) {
		// Base case, return when only tree is one character
		if (tree.length() == 1) {
			charKeyList.add(tree + priorLevels);// add to the ArrayList
			return tree + priorLevels + " ";
		}

		int count = 0;// Counts # "("
		int index = 0; // Index counter

		// Finds the "," that divides the left and right branches
		// Starts at 1 because the first index is an extra "("
		for (int i = 1; i < tree.length(); i++) {
			// Prevents giving the wrong index for ","
			if (tree.charAt(i) == '(') {
				count++;
			}
			// Makes sure the ending indicator is the correct one or else tree doesn't get unloaded correctly
			else if (tree.charAt(i) == ')' && count != 0) {
				count--;
			}
			// Remember the index of the "," as it separates the right branch from the left
			// one
			if (tree.charAt(i) == ',' && count == 0) {
				index = i;
			}
		}
		// recall method, left branch + right branch, without the extra brackets at the
		// end
		return treeToKey(tree.substring(1, index), priorLevels + "0")
				+ treeToKey(tree.substring(index + 1, tree.length() - 1), priorLevels + "1");
	} // End of treeToKey function
	
	 /**
	   * readFile
	   * Method that reads the contents from a specified file
	   * @params the name of the file as a String
	   * Returns the contents of the file in a String
	   */ 
	public String readFile(String file) throws IOException {
		// BufferedReader setup
		BufferedReader br = null;
		String contents = "";// return value
		try {
			// initialize buffered reader
			br = new BufferedReader(new FileReader(file));
			String line;
			// read to the end of file
			while ((line = br.readLine()) != null) {
				// add on to result String
				contents += line;
			}
		} finally {
			br.close();
		}
		return contents;
	} // End of readFile function
	
	/**
	 * writeFile
	 * Method encodes a message and prints the encoded message into a new file
	 * @param the message to encode and the name of the file from which 
	 * the message was extracted from
	 * Returns nothing
	 */
	public void writeFile(String messageToEncode, String fileName) throws IOException{
		// Helper variables:
		
		// Counts the number of zeroes before a key
		int preZeroes = 0;
		// Stores the decimal version of the key
		int decimalOfKey = 0;
		// Temporary storage for  decimal
		String dTemp = "";
		// Stores the encoded message
		String encodedMessage = "";
		
		// Printwriter setup
		PrintWriter pw = null;
		try {
			// Create a new file
			pw = new PrintWriter(fileName + "Encoded.txt");
			
			// Encode the message, buildTree and treeToKey functions
			// should have been called at this point
			for(int i = 0;i < messageToEncode.length();i++) {
				for(int j = 0;j < charKeyList.size();j++) {
					// get the key associated to the char and add to encoded message
					if(messageToEncode.charAt(i) == charKeyList.get(j).charAt(0)) {
						encodedMessage += charKeyList.get(j).substring(1);
					}
				} // End of inner for loop
			} // End of outer for loop
			
			// Make message a multiple of 8 bits
			preZeroes = (int)(8 * Math.ceil(encodedMessage.length()/8.0) - encodedMessage.length());
			if(encodedMessage.length() % 8 != 0) {
				//add the extra to message to make data 8 bits
				for(int i = 0;i < preZeroes;i++) {
					encodedMessage += "0";
				}
			}
			
			// Write the amount of extra bits into the file (first line)
			pw.println(preZeroes);
			
			// Temporary storage for keys
			String keyTemp = "";
			
			// Sort keys from largest length to smallest length
			for(int i = 1;i < charKeyList.size();i++) {
				
				int k = charKeyList.get(i).substring(1).length();
				String kValue = charKeyList.get(i);
				int j = i - 1;
				
				while(j >= 0 && charKeyList.get(j).substring(1).length() < k) {
					charKeyList.set(j+1, charKeyList.get(j));
					j--;
				}
				
				charKeyList.set(j+1,kValue);
			} // End of sort
			
			// Print keys into file
			for(int i = 0;i < charKeyList.size();i++) {
				// Convert the key String into an integer
				decimalOfKey = Integer.parseInt(charKeyList.get(i).substring(1), 2);
				dTemp = Integer.toBinaryString(decimalOfKey);
				
				// Find the preceding zeroes of a key
				preZeroes = charKeyList.get(i).substring(1).length() - dTemp.length();
				
				// Write to file
				pw.print(charKeyList.get(i).charAt(0) + " " + (char)decimalOfKey + " " + preZeroes);
				// Exclude the last new line
				if(i == charKeyList.size() - 1) {
					// *** Separates "key" info from encoded message
					pw.println("***");
				}
			}
			
			for(int i = 0;i < encodedMessage.length()/8;i++) {
				// Print as ascii characters
				pw.print((char)Integer.parseInt(encodedMessage.substring(0,8), 2));
				encodedMessage = encodedMessage.substring(1);
			}
			
		} finally{
			pw.close();
		}
	} // End of writeFile function
	
	/**
	 * Encode
	 */
	public void Encode(String fileName) {
		String fileData;
		
		try {
			fileData = readFile(fileName);
			
			findCharFrequency(fileData);
			
			String tree = createCharTree();
			String decodedTree;
			
			// Decode Tree
			if(tree.length() != 2) {
				decodedTree = treeToKey(tree, "");
			} 
			// If tree only has one node
			else { 
				charKeyList.add(tree.substring(1) + tree.substring(0,1));
			}
			
			writeFile(fileData, fileName);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
	}
	
} // End of Encoded class