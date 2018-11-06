package game.chessGame;

import java.util.ArrayList;


public class Player {
	private String name;
	private ArrayList<Piece> pieceArray = new ArrayList<Piece>();
	private Side side;
	
	public Player() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Piece> getPieceArray() {
		return pieceArray;
	}
	
	public Piece getPieceArray(int index) {
		return pieceArray.get(index);
	}
	
	public void addPiece(Piece piece) {
		pieceArray.add(piece);
	}
	
	public void setSide(Side side) {
		this.side = side;
	}
	
	public Side getSide() {
		return side;
	}
	
	public void movePiece(Board board,String from, String to) {
		if(board.getCell(from).getPiece().isValidMove(board, from, to)
			&& board.getCell(from).getPiece().getSide() == this.getSide()) {
				board.setCell(to, board.getCell(from).getPiece());
				Piece p = new Space();
				board.setCell(from, p);
		}
	}
	
}
