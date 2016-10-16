package turing;

public class Tape {
	private Cell currentCell; // Current cell pointer
	
	public Tape() { //Constructor to create a blank tape with a single cell, which contains a blank space.
		Cell newCell = new Cell();
		newCell.content = ' ';
		newCell.prev = null;
		newCell.next = null;
		currentCell = newCell;
	}
	
	public Cell getCurrentCell() { //The pointer to current cell.
		return currentCell;
	}
	
	public char getContent() { //The content of current cell.
		return currentCell.content;
	}
	
	public void setContent(char ch) { //ch The character to be set into the current cell.
		currentCell.content = ch;
	}

	public void moveLeft() { //Moves the current cell one position to the left along the tape.
		if (currentCell.prev == null) {
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.prev = null;
			newCell.next = currentCell;
			currentCell.prev = newCell;
		}
		currentCell = currentCell.prev;
	}
	
	public void moveRight() { //Moves the current cell one position to the right along the tape.
		if (currentCell.next == null) {
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.next = null;
			newCell.prev = currentCell;
			currentCell.next = newCell;
		}
		currentCell = currentCell.next;
	}

	public String getTapeContents() { //Returns a String consisting of the chars from all the cells on the tape.
		Cell pointer = currentCell;
		
		while (pointer.prev != null)
			pointer = pointer.prev;
		
		String strContent = "";
		while (pointer != null) {
			strContent += pointer.content;
			pointer = pointer.next;
		}
		
		strContent = strContent.trim(); //Returns a copy of the string, with leading and trailing whitespace omitted.
		return strContent;
	}
}
