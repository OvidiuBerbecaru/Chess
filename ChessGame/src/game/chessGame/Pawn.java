package game.chessGame;

public class Pawn extends Piece {
	
	public Pawn(Side s) {
		setSide(s);
		switch(s) {
			case BLACK:
				setSymbol("\u265F");
				break;
			case WHITE:
				setSymbol("\u2659");
				break;
		}
		
		setPieceType(PieceType.PAWN);
	}
	
	public boolean isValidMove(Board board, String from, String to) {
		boolean answer = false;
		int fromRow =  8 - Character.getNumericValue(from.charAt(1));
		int fromColumn = stringToIntColumn(board, from);
		int toRow = 8 - Character.getNumericValue(to.charAt(1));
		int toColumn = stringToIntColumn(board, to);
		
		switch(board.getCell(from).getPiece().getSide()) {
			case WHITE:
				if(board.getBoardArray(toRow, toColumn).getPiece().getType() == PieceType.SPACE
					&& toColumn == fromColumn && toRow == fromRow - 1) {
					
						answer = true;
					
				}
				
				if(board.getBoardArray(toRow, toColumn).getPiece().getSide() == Side.BLACK
					&& toRow == fromRow - 1) {
						if(toColumn == fromColumn - 1 || toColumn == fromColumn + 1) {
							answer = true;
						}
				}
				break;
			case BLACK:
				if(board.getBoardArray(toRow, toColumn).getPiece().getType() == PieceType.SPACE
				&& toColumn == fromColumn && toRow == fromRow + 1) {
				
					answer = true;
				
				}
			
				if(board.getBoardArray(toRow, toColumn).getPiece().getSide() == Side.WHITE
						&& toRow == fromRow + 1) {
							if(toColumn == fromColumn - 1 || toColumn == fromColumn + 1) {
								answer = true;
							}
				}
				break;
		}
	return answer;	
	}

}
