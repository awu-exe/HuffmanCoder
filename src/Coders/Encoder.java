package Coders;

// Imports
import java.util.ArrayList;

class Encoder {
	// MARK: Properties
	
	/*
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

	/*
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
	
	/*
	 * createCharTree
	 * Creates a binary tree-like structure based on char frequencies, represented with brackets/commas
	 * @Param: nothing
	 * returns a binary tree represented as a String
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
				charList.set(j, charKey);
			}// End of for loop
			
			
			// Stores a combined version of charList(0) and charList(1)
			charList.set(1, "(" + charList.get(0) + "," + charList.get(1) + ")");
			// Stores a combined of version of frequencyList(0) and frequencyList(1)
			frequencyList.set(1, frequencyList.get(0) + frequencyList.get(1));
		}// End of while loop
		
		// Return String version of the tree without extra brackets
		return charList.get(0);
	}// End of createCharTree function
}