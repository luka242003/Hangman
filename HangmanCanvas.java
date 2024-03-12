/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	 
/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawInitial();
		usedLetter = "";
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		GLabel currentWord = new GLabel(word);
		currentWord.setFont(new Font("Arial", 35, 20));
		if(getElementAt(getWidth() /2 -  BEAM_LENGTH, getHeight() - 60) != null) {
			remove(getElementAt(getWidth() /2 -  BEAM_LENGTH, getHeight() - 60)); 
		}
		add(currentWord, getWidth() /2 -  BEAM_LENGTH, getHeight() - 60);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int attempts, boolean isUsed) {
		if(attempts == 7) {
			drawHead();
		}
		else if( attempts == 6) {
			drawBody();
		}
		else if(attempts == 5) {
			drawLeftHand();
		}
		else if(attempts == 4) {
			 drawRightHand();
		}
		else if(attempts == 3) {
			drawLeftLeg();
		}
		else if(attempts == 2) {
			drawRightLeg();
		}
		else if(attempts == 1) {
			drawLeftFoot();
		}
		else if(attempts == 0) {
			drawRightFoot();
		}
		if(!isUsed) {
			usedLetter += letter;
			GLabel usedLetters = new GLabel(usedLetter);
			if(getElementAt(getWidth() /2 -  BEAM_LENGTH, getHeight() - 30) != null) {
				remove(getElementAt(getWidth() /2 -  BEAM_LENGTH, getHeight() - 30)); 
			}
			add(usedLetters, getWidth() /2 -  BEAM_LENGTH, getHeight() - 30);
		}
	}

	private void drawScaffold(double y1) {
		GLine scaffold = new GLine(getWidth() /2 -  BEAM_LENGTH, y1, getWidth() /2 -  BEAM_LENGTH, y1 - SCAFFOLD_HEIGHT);
		add(scaffold);
	}
	
	private void drawBeam(double y1) {
		GLine beam = new GLine(getWidth() /2 -  BEAM_LENGTH, y1 - SCAFFOLD_HEIGHT, getWidth() /2, y1 - SCAFFOLD_HEIGHT);
		add(beam);
	}
	
	private void drawRope(double y1) {
		GLine rope = new GLine(getWidth() /2, y1 - SCAFFOLD_HEIGHT, getWidth() /2, y1 - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(rope);
	}
	
	private void drawInitial() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5;
		drawScaffold(y1);
		drawBeam(y1);
		drawRope(y1);
	}
	
	private void drawHead() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5;
		GOval head = new GOval(getWidth() /2 - HEAD_RADIUS,  y1 - SCAFFOLD_HEIGHT + ROPE_LENGTH, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head);
	}
	
	private void drawBody() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS;
		GLine body = new GLine(getWidth() /2, y1 , getWidth() /2, y1 + BODY_LENGTH);
		add(body);
	}
	
	private void drawLeftHand() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine l1 = new GLine(getWidth() /2, y1, getWidth() /2 - UPPER_ARM_LENGTH,  y1);
		add(l1);
		GLine l2 = new GLine(getWidth() /2 - UPPER_ARM_LENGTH, y1, getWidth() /2 - UPPER_ARM_LENGTH,   y1 + LOWER_ARM_LENGTH);
		add(l2);
	}
	
	private void drawRightHand() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine l1 = new GLine(getWidth() /2, y1, getWidth() /2 + UPPER_ARM_LENGTH,  y1);
		add(l1);
		GLine l2 = new GLine(getWidth() /2 + UPPER_ARM_LENGTH, y1, getWidth() /2 + UPPER_ARM_LENGTH,   y1 + LOWER_ARM_LENGTH);
		add(l2);
	}
	
	private void drawLeftLeg() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine hip = new GLine( getWidth() /2, y1, getWidth() /2 - HIP_WIDTH,  y1);
		add(hip);
		GLine leftLeg = new GLine(getWidth() /2 - HIP_WIDTH, y1, getWidth() /2 - HIP_WIDTH, y1 + LEG_LENGTH);
		add(leftLeg);
	}
	
	private void drawRightLeg() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine hip = new GLine( getWidth() /2, y1, getWidth() /2 + HIP_WIDTH,  y1);
		add(hip);
		GLine leftLeg = new GLine(getWidth() /2 + HIP_WIDTH, y1, getWidth() /2 + HIP_WIDTH, y1 + LEG_LENGTH);
		add(leftLeg);
	}
	
	private void drawLeftFoot() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine leftFoot = new GLine(getWidth() /2 - HIP_WIDTH, y1, getWidth() /2 - HIP_WIDTH - FOOT_LENGTH, y1);
		add(leftFoot);
	}
	
	private void drawRightFoot() {
		double y1 = (getHeight()) / 2 + (SCAFFOLD_HEIGHT) / 2.5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine rightFoot = new GLine(getWidth() /2 + HIP_WIDTH, y1, getWidth() /2 + HIP_WIDTH + FOOT_LENGTH, y1);
		add(rightFoot);
	}
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private String usedLetter = "";
}
