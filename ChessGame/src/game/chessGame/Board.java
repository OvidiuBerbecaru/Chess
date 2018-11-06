package game.chessGame;

public class Board {
	private final int boardColumnNumber = 8;
	private final int boardRowNumber = 8;
	private final String boardLetterVector[] = {"a", "b", "c", "d", "e", "f", "g", "h"};
	private Cell boardArray[][] = new Cell[boardColumnNumber][boardRowNumber];
	
	public Board() {
		for(int i = 0 ; i < boardRowNumber; i++) {
			for(int j = 0 ; j < boardColumnNumber ; j++) {
				boardArray[i][j] = new Cell(boardLetterVector[j] + (8-i));
				Piece piece = new Space();
				boardArray[i][j].setPiece(piece);
			}	
		}
	}
	
	public int getBoardColumnNumber() {
		return boardColumnNumber;
	}
	
	public int getBoardRowNumber() {
		return boardRowNumber;
	}
	
	public void displayBoard() {
		System.out.print("  ");
		for(int i = 0 ; i < boardColumnNumber ; i++) {
			System.out.print("  " + boardLetterVector[i] + "  ");
		}
		System.out.println();
		int lineIndex = 8;
		System.out.println("   ---------------------------------------");
		for(int i = 0 ; i < boardRowNumber ; i++) {
			System.out.print(lineIndex);
			System.out.print(" | ");
			for(int j = 0 ; j < boardColumnNumber ; j++) {
				System.out.print(boardArray[i][j].getPiece().getSymbol());
				System.out.print(" | ");
			}
		System.out.print(lineIndex);
		lineIndex--;
		System.out.println();
		System.out.println("   ---------------------------------------");
		}
		System.out.print("  ");
		for(int j = 0 ; j < boardColumnNumber ; j++) {
			System.out.print("  " + boardLetterVector[j] + "  ");
		}
		System.out.println();
		System.out.println();
	}
	
	public Cell getBoardArray(int row, int column) {
		return boardArray[row][column];
	}
	
	public Cell getCell(String index) {
		int row = 8 - Character.getNumericValue(index.charAt(1));
		int column = 0;
		for(int i = 0 ; i < boardLetterVector.length ; i++) {
			if( boardLetterVector[i].equals(Character.toString(index.charAt(0))) ) {
				column = i;
				break;
			}
		}
		return boardArray[row][column];
	}
	
	public String cellIntPosToStringPos(int row, int column) {
		String strRow = Integer.toString(8 - row);
		String strColumn = boardLetterVector[column];
		
		return strColumn + strRow;
	}
	
	public int rowStringPosToInt(String position) {
		return 8 - Character.getNumericValue(position.charAt(1));
	}
	
	public int colStringPosToInt(String position) {
		int column = 0;
		for(int i = 0 ; i < boardLetterVector.length ; i++) {
			if( boardLetterVector[i].equals(Character.toString(position.charAt(0))) ) {
				column = i;
				break;
			}
		}
		
		return column;
	}
	
	public Cell getCellInt(int row, int column) {
		return boardArray[row][column];
	}
	
	public String[] getBoardLetterVector() {
		return this.boardLetterVector;
	}
	
	public String getBoardLetterVectorAt(int i) {
		return this.boardLetterVector[i];
	}
	
	public void setCell(String index , Piece piece) {
		this.getCell(index).setPiece(piece);
	}

}
