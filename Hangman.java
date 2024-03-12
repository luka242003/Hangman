/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private RandomGenerator rg = new RandomGenerator();
	HangmanLexicon lexicon = new HangmanLexicon();
	HangmanCanvas canvas;
	private String usedLetters = "";
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
       	startPlay();
   	}
    
    //Starting play
    private void startPlay() {
    	canvas.reset();
    	
    	String s = readLine("Click e to play. ");
    	if(s.equals("e")) {
    		int numberOfWords = lexicon.getWordCount();
    		int randomNumber = rg.nextInt(numberOfWords);
    		String word = getNewWord(randomNumber);
    		String checkerWord = changeWord(word);
    		play = true;
    		println("Welcome to Hangman!");
    		playHangman(checkerWord, word, play);
    	}
    		println("If you don't want to play goodbye!!!");
    		play = false;
    }
    
    //get word from lexicon
    private String getNewWord(int i) {
    	String word = lexicon.getWord(i);
    	return word;
    }
    
    //change the word
    private String changeWord(String word) {
    	String changedWord = "";
    	for(int i = 0; i < word.length(); i++) {
    		changedWord += "-";
    	}
    	return changedWord;
    }
    
    
    //starting play of hangman
    private void playHangman(String checkerWord, String word, boolean play) {
    	int checkAttempt = word.length();
    	int attempts = 8;
    	char letter;
    	while(play) {
    		checkAttempt = word.length();
    		returnMessage(checkerWord, attempts);
    		String s = readLine("Your guess: ");
    		String newCheckerWord = "";
    		s = s.toUpperCase(); // changing to uppercase
    		int counter = 0;
    		letter = letterChecker(s);
    		boolean isUsed = checkUsedLetters(letter);
    		usedLetters += letter;
    		for(int i = 0; i < word.length(); i++) {
    			if(letter == word.charAt(i) && counter < 1) {
    				println("The guess is correct.");
    				counter++;
    			}
    			else {
    				checkAttempt--;
    			}
    		}
    		attempts = checkAttempts(checkAttempt, attempts, letter, isUsed, word);
    		newCheckerWord = checkerWord;
			checkerWord = checkNewLetters(word, newCheckerWord, letter); 
			canvas.displayWord(checkerWord); //calling the method of hangman canvas
			checkWin(checkerWord, word);
    	}
    }
    
    //checks the letter
    private char letterChecker(String s) {
    	char letter = s.charAt(0);
    	boolean notLetter = true;
    	if((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'))  {
    		return letter;
    	}
    	else {
    		while(notLetter) {
    			println("Wrong symbol");
    			s = readLine("Enter guess: ");
    			s = s.toUpperCase();
    			letter = s.charAt(0);
    			if((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z')) {
    				notLetter = false;
    			}
    		}
    		return letter;
    	}
    }
    
    //checking the new letters
    private String checkNewLetters(String word, String newCheckerWord, char letter) {
    	String checkerWord = "";
    	for(int i = 0; i < word.length(); i++) {
    		if(letter == word.charAt(i)) {
    			checkerWord += letter;
    		}
    		else if(letter != word.charAt(i) && newCheckerWord.charAt(i) != '-')
    		{
    			checkerWord += newCheckerWord.charAt(i);
    		}
    		else if(letter != word.charAt(i) && newCheckerWord.charAt(i) == '-') {
    			checkerWord += '-';
    		}
    	}
    	return checkerWord;
    }
    
    //check if you lost
    private void checkLose(int attempts, String word) {
    	if(attempts == 0) {
			println("The word was: " + word);
			println("You lose");
			play = false;
			startPlay();
    	}
    	
	}
    
    //check if you have won
    private void checkWin(String checkerWord, String word) {
    	if(checkerWord.equals(word)) {
			println("You guessed the word: " + word);
			println("You won!");
			play = false; 
			startPlay();
		}
    }
    
    
    //check used letters
    private boolean checkUsedLetters(char letter) {
    	int counter = 0;
    	for(int i = 0; i < usedLetters.length(); i++) {
    		if(usedLetters.charAt(i) == letter) {
    			counter++;
    		}
    	}
    	if(counter >= 1) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //check your attempts
    private int checkAttempts(int checkAttempt, int attempts, char letter, boolean isUsed, String word) {
    	if(checkAttempt == 0) {
			attempts--;
			canvas.noteIncorrectGuess(letter, attempts, isUsed);    			
			println("There are no " + letter + "'s in the word");
			checkLose(attempts, word);
		}
    	return attempts;
    }
    
    
    //return message every time
    private void returnMessage(String checkerWord, int attempts) {
    	println("The word now looks like this: " + checkerWord);
		println("You have " + attempts + " guesses left.");
    }
   
    private boolean play = true;
}
