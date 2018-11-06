package game.chessGame;

public class Space extends Piece {
	
	public Space() {
		setPieceType(PieceType.SPACE);
		setSymbol("  ");
		setSide(null);
	}
	
	public boolean isValidMove(Board board, String from, String to) {
		return false;
	}
	
}
