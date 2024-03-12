/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;

import java.util.ArrayList;


public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}
	
	
	public HangmanLexicon() {
		
		try {
		rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
		while(true) {
			if(rd.readLine() == null) {
				rd.close();
				break;
			}
			words.add(rd.readLine());
		}


		}
		catch(IOException e) {
		
		}
	}

/** Returns the word at the specified index.  **/
	public String getWord(int index){
		return words.get(index);
	}
	private ArrayList <String> words = new ArrayList <String>();
	private BufferedReader rd;
}
