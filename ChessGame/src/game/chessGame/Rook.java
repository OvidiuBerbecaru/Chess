package game.chessGame;

import java.util.ArrayList;

public class Rook extends Piece {
	private ArrayList<String> trajectory;
	
	public Rook(Side s) {
		
		setSide(s);
		switch(s) {
			case BLACK:
				setSymbol("\u265C");
				break;
			case WHITE:
				setSymbol("\u2656");
				break;
		}
		
		setPieceType(PieceType.ROOK);
	}
	
	public void createTrajectory() {
		trajectory = new ArrayList<String>();
	}
	
	public void addToTrajectory(String pos) {
		trajectory.add(pos);
	}
	
	public ArrayList<String> getTrajectory() {
		return trajectory;
	}
	
	public void resetTrajectory() {
		trajectory.clear();
	}
	
	public void setTrajectory(Board board, String from, String to) {
		
	}
	
	
	public boolean isValidMove(Board board, String from, String to) {
		boolean answer = true;
		int fromRow =  8 - Character.getNumericValue(from.charAt(1));
		int fromColumn = stringToIntColumn(board, from);
		int toRow = 8 - Character.getNumericValue(to.charAt(1));
		int toColumn = stringToIntColumn(board, to);
		
		if(!isSameSideCheck(board, from, to)) {
			if(fromRow == toRow) {
				if(fromColumn < toColumn) {
					for(int i = fromColumn + 1 ; i < toColumn ; i++) {
						if(board.getBoardArray(fromRow, i).getPiece().getType() != PieceType.SPACE) {
							answer = false;
							break;
						}
					}
				}
				if(fromColumn > toColumn) {
					for(int i = fromColumn -1 ; i > toColumn ; i--) {
						if(board.getBoardArray(fromRow, i).getPiece().getType() != PieceType.SPACE) {
							answer = false;
							break;
						}
					}
				}
			}
			if(fromColumn == toColumn) {
				if(fromRow < toRow) {
					for(int i = fromRow + 1 ; i < toRow ; i++) {
						if(board.getBoardArray(i, fromColumn).getPiece().getType() != PieceType.SPACE) {
							answer = false;
							break;
						}
					}
				}
				if(fromRow > toRow) {
					for(int i = fromRow - 1 ; i > toRow ; i--) {
						if(board.getBoardArray(i, fromColumn).getPiece().getType() != PieceType.SPACE) {
							answer = false;
							break;
						}
					}
				}
			}
			if(fromColumn != toColumn && fromRow != toRow) {
				answer = false;
			}
		}
		else {
			answer = false;
		}	
		return answer;
	}
	
}
