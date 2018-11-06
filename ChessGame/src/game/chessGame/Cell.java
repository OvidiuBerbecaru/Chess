package game.chessGame;

public class Cell {
	private String index;
	private Piece piece;
	
	public Cell() {
		piece = null;
	}

	public Cell(String index) {
		this.index = index;
	}
	
	public void setNumber(String index) {
		this.index = index;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece getPiece() {
		return piece;
	}
}
