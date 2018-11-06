package game.chessGame;

public class King extends Piece {
	
	public King(Side s) {
		setSide(s);
		switch(s) {
			case BLACK:
				setSymbol("\u265A");
				break;
			case WHITE:
				setSymbol("\u2654");
				break;
		}
		
		setPieceType(PieceType.KING);
	}
	
	public boolean isValidMove(Board board, String from, String to) {
		boolean answer = false;
		int fromRow =  8 - Character.getNumericValue(from.charAt(1));
		int fromColumn = stringToIntColumn(board, from);
		int toRow = 8 - Character.getNumericValue(to.charAt(1));
		int toColumn = stringToIntColumn(board, to);
		boolean valid = false;
		boolean otherKingValidMove = false;
		int otherKingRow = 0;
		int otherKingColumn = 0;
		Side side = board.getCell(from).getPiece().getSide();
		
		if(from.equals(to)) {
			return false;
		}
		
		if(isFutureCheck(board, to, getSide())) {
			return false;
		}
		
		if(!isSameSideCheck(board, from, to)) {
			if((Math.abs(fromColumn - toColumn) <= 1 )
				&& (Math.abs(fromRow - toRow) <= 1 )) {
					valid = true;
			}
		}
		
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if( board.getBoardArray(i, j).getPiece().getType() == PieceType.KING 
					&& board.getBoardArray(i, j).getPiece().getSide() != board.getCell(from).getPiece().getSide() ) {
					
						otherKingRow = i;
						otherKingColumn = j;
						break;
					
				}
			}
		}
		
		if((Math.abs(otherKingColumn - toColumn) == 1 || Math.abs(otherKingColumn - toColumn) == 0)
				&&(Math.abs(otherKingRow - toRow) == 1 || Math.abs( otherKingRow - toRow) == 0)) {
					
					otherKingValidMove = true;
		}
		
		if(valid && !isCheckNotFromKing(board, to, side) && !otherKingValidMove ) {
			answer = true;
		}
		
		return answer;
	}
	
	public boolean isOtherKingValidMove(Board board, String to, Side side) {
		int otherKingRow = 0;
		int otherKingColumn = 0;
		boolean otherKingValidMove = false;
		
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if( board.getBoardArray(i, j).getPiece().getType() == PieceType.KING 
					&& board.getBoardArray(i, j).getPiece().getSide() != side ) {
					
						otherKingRow = i;
						otherKingColumn = j;
						break;
					
				}
			}
		}
		
		int toColumn = board.colStringPosToInt(to);
		int toRow = board.rowStringPosToInt(to);
		
		if((Math.abs(otherKingColumn - toColumn) == 1 || Math.abs(otherKingColumn - toColumn) == 0)
				&&(Math.abs(otherKingRow - toRow) == 1 || Math.abs( otherKingRow - toRow) == 0)) {
					
					otherKingValidMove = true;
		}
		
		return otherKingValidMove;
	}
	
	public boolean isCheckNotFromKing(Board board, String position, Side side) {
		boolean answer = false;
		
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if(board.getBoardArray(i, j).getPiece().getSide() != side
					&& board.getBoardArray(i, j).getPiece().getType() != PieceType.KING
					&& board.getBoardArray(i, j).getPiece().getSide() != null
					&& board.getBoardArray(i, j).getPiece().isValidMove(board, board.getCellInt(i, j).getIndex(), position) ) {
						answer = true;
						break;
				}
			}
		}
		
		return answer;
	}
	
	public boolean isFutureCheck(Board board, String to, Side s) {
		boolean answer = false;
		
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if(board.getCellInt(i, j).getPiece().getSide() != s && board.getCellInt(i, j).getPiece().getType() != PieceType.KING) {
					if(board.getCellInt(i, j).getPiece().isValidMove(board, board.cellIntPosToStringPos(i, j), to)) {
						answer = true;
						return answer;
					}
					
				}
				
				if(board.getCellInt(i, j).getPiece().getSide() != s && board.getCellInt(i, j).getPiece().getType() == PieceType.KING) {
					if(board.getCellInt(i, j).getPiece().isOtherKingValidMove(board, to, s)) {
						answer = true;
						return answer;
					}
				}
				
			}
		}
		return answer;
	}
	
}
