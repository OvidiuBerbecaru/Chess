package game.chessGame;

public class Piece {
	private PieceType pieceType;
	private String symbol;
	private Side side;
	
	
	public Side getSide() {
		return side;
	}
	
	public void setSide(Side side) {
		this.side = side;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public Piece() {}
	
	public Piece(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	
	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	
	public PieceType getType() {
		return pieceType;
	}
	
	public boolean isValidMove(Board board, String from, String to) {
		return true;
	}
	
	protected static boolean isSameSideCheck(Board board, String from, String to) {
		if(board.getCell(from).getPiece().getSide() == board.getCell(to).getPiece().getSide()) {
			return true;
		}
		return false;
	}
	
	public boolean isOtherKingValidMove(Board board, String to, Side side) {
		return false;
	}
	
	public void setTrajectory(Board board, String from, String to) {
		
	}
	
	// Transforms the String coordonate to an int
	
	protected static int stringToIntColumn(Board board, String s) {
		int column = 0;
		
		for(int i = 0 ; i < board.getBoardLetterVector().length ; i++) {
			if( board.getBoardLetterVectorAt(i).equals(Character.toString(s.charAt(0))) ) {
				column = i;
				break;
			}
		}
		return column;
	}
	
}
