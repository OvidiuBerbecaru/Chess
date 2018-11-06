package game.chessGame;

import java.util.ArrayList;

public class Bishop extends Piece {
	private ArrayList<String> trajectory;
	
	public Bishop(Side s) {
		setSide(s);
		switch(s) {
			case BLACK:
				setSymbol("\u265D");
				break;
			case WHITE:
				setSymbol("\u2657");
				break;
		}
		
		setPieceType(PieceType.BISHOP);
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
		trajectory = new ArrayList<String>();
		
		int fromRow = board.rowStringPosToInt(from);
		int fromCol = board.colStringPosToInt(from);
		int toRow = board.rowStringPosToInt(to);
		int toCol = board.colStringPosToInt(to);
		
		if(toCol - toRow == fromCol - fromRow) {
			if(toCol < fromCol) {
				for(int i = toRow + 1 ; i < fromRow ; i++) {
					for(int j = toCol + 1 ; j < fromCol ; j++) {
						if(j - i == toCol - toRow) {
							trajectory.add(board.cellIntPosToStringPos(i, j));
						}
					}
				}
			}
			if(toCol > fromCol) {
				for(int i = fromRow + 1 ; i < toRow ; i++) {
					for(int j = fromCol + 1 ; j < toCol ; j++) {
						if(j - i == toCol - toRow) {
							trajectory.add(board.cellIntPosToStringPos(i, j));
						}
					}
				}
			}
		}
		if(toCol + toRow == fromCol + fromRow) {
			if(toCol < fromCol) {
				for(int i = toRow - 1 ; i > fromRow ; i--) {
					for(int j = toCol + 1 ; j < fromCol ; j++) {
						if(j + i == toCol + toRow) {
							trajectory.add(board.cellIntPosToStringPos(i, j));
						}
					}
				}
			}
			if(toCol > fromCol) {
				for(int i = fromRow - 1 ; i > toRow ; i--) {
					for(int j = fromCol + 1 ; j < toCol ; j++) {
						if(j + i == toCol + toRow) {
							trajectory.add(board.cellIntPosToStringPos(i, j));
						}
					}
				}
			}
		}
		
		
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
			if( (fromRow + fromColumn != toRow + toColumn) && (fromRow - fromColumn != toRow - toColumn) ) {
				answer = false;
			}
		}
		else{
			answer = false;
		}
		return answer;
	}
	
}
