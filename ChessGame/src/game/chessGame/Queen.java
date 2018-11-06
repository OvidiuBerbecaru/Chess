package game.chessGame;

import java.util.ArrayList;

public class Queen extends Piece {
	private ArrayList<String> trajectory;
	
	public Queen(Side s) {
		setSide(s);
		switch(s) {
			case BLACK:
				setSymbol("\u265B");
				break;
			case WHITE:
				setSymbol("\u2655");
				break;
		}
		
		setPieceType(PieceType.QUEEN);
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
		int columnIndex = fromColumn;
		int rowIndex = fromRow;
		
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
			if(fromRow - fromColumn == toRow - toColumn) {
				if(fromRow < toRow) {
					while(columnIndex < toColumn && rowIndex < toRow){
						if(board.getBoardArray(rowIndex + 1, columnIndex + 1).getPiece().getType() != PieceType.SPACE
								&& rowIndex + 1 != toRow && columnIndex + 1 != toColumn) {
							answer = false;
							break;
						}
						rowIndex++;
						columnIndex++;
					}
				}
				if(fromRow > toRow) {
					while(columnIndex > toColumn && rowIndex > toRow) {
						if(board.getBoardArray(rowIndex - 1, columnIndex - 1).getPiece().getType() != PieceType.SPACE
								&& rowIndex - 1 != toRow && columnIndex - 1 != toColumn) {
							answer = false;
							break;
						}
						rowIndex--;
						columnIndex--;
					}
				}
			}
			if(fromRow + fromColumn == toRow + toColumn) {
				if(fromRow < toRow) {
					while(columnIndex > toColumn && rowIndex < toRow){
						if(board.getBoardArray(rowIndex + 1, columnIndex - 1).getPiece().getType() != PieceType.SPACE
								&& rowIndex + 1 != toRow && columnIndex - 1 != toColumn) {
							answer = false;
							break;
						}
						rowIndex++;
						columnIndex--;
					}
				}
				if(fromRow > toRow) {
					while(columnIndex < toColumn && rowIndex > toRow) {
						if(board.getBoardArray(rowIndex - 1, columnIndex + 1).getPiece().getType() != PieceType.SPACE
								&& rowIndex - 1 != toRow && columnIndex + 1 != toColumn) {
							answer = false;
							break;
						}
						rowIndex--;
						columnIndex++;
					}
				}
			}
			if( (fromRow + fromColumn != toRow + toColumn) && (fromRow - fromColumn != toRow - toColumn)
				&& (fromRow != toRow) && (fromColumn != toColumn) ) {
				answer = false;
			}
		}
		else {
			answer = false;
		}
		return answer;
	}
	
}
