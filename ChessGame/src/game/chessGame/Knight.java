package game.chessGame;


public class Knight extends Piece {
	
	public Knight(Side s) {
		setSide(s);
		switch(s) {
			case BLACK:
				setSymbol("\u265E");
				break;
			case WHITE:
				setSymbol("\u2658");
				break;
		}
		
		setPieceType(PieceType.KNIGHT);
	}
	
	
	public boolean isValidMove(Board board, String from, String to) {
		boolean answer = false;
		int fromRow =  8 - Character.getNumericValue(from.charAt(1));
		int fromColumn = stringToIntColumn(board, from);
		int toRow = 8 - Character.getNumericValue(to.charAt(1));
		int toColumn = stringToIntColumn(board, to);
		
		if(!isSameSideCheck(board, from, to)) {
			if(Math.abs(toColumn - fromColumn) == 1 && Math.abs(toRow - fromRow) == 2) {
				answer = true;
			}
			if(Math.abs(toColumn - fromColumn) == 2 && Math.abs(toRow - fromRow) == 1) {
				answer = true;
			}
		}
		return answer;
	}
	
}
