package game.chessGame;

import java.util.Scanner;

public class Game {
	
	// The initial game build

	private Player p1 = new Player();
	private Player p2 = new Player();
	public Board board = new Board();
	private static final int playerPieceNumber = 5;
	private GameState gamestate = GameState.RUNNING;
	private int playerPieceIndex = 0;
	
	// The players type in their name
	
	public Board getBoard() {
		return board;
	}
	
	public void nameSelection(Player player1, Player player2) {
		System.out.println("Player1 please type in your name : ");
		Scanner s = new Scanner(System.in);
		String answer = s.nextLine();
		player1.setName(answer);
		
		System.out.println("Player2 please type in your name : ");
		String answer2 = s.nextLine();
		player2.setName(answer2);
	}
	
	// Add pieces to players method
	
	public void addAllPieces(Player p , Side s) {
		for(int i = 0 ; i < board.getBoardColumnNumber() ; i++) {
			p.addPiece(new Pawn(s));
		}
		p.addPiece(new Rook(s));
		p.addPiece(new Rook(s));
		p.addPiece(new Knight(s));
		p.addPiece(new Knight(s));
		p.addPiece(new Bishop(s));
		p.addPiece(new Bishop(s));
		p.addPiece(new Queen(s));	
		p.addPiece(new King(s));

	}
	
	
	// Side picking method
	
	public void sideSelection() {
		System.out.println(p1.getName() + ", please pick white or black...");
		Scanner s = new Scanner(System.in);
		String answer = s.nextLine();
		
		if(answer.equals("white")) {
			p1.setSide(Side.WHITE);
			p2.setSide(Side.BLACK);
		}
		if(answer.equals("black")) {
			p1.setSide(Side.BLACK);
			p2.setSide(Side.WHITE);
		}
		addAllPieces(p1, p1.getSide());
		addAllPieces(p2, p2.getSide());
	}
	
	//Sets all the pieces on the board
	
	public void setPiecesOnBoard(Player p, Side s) {
		int pawnRow = 0;
		int otherPieceRow = 0;
		switch(s) {
			case BLACK:
				pawnRow = 1;
				otherPieceRow = 0;
				break;
			case WHITE:
				pawnRow = 6;
				otherPieceRow = 7;
				break;
		}
		int pw = 0;
		int min = 0;
		int max = 7;
		for(int i = 0 ; i < p.getPieceArray().size() ; i++) {
			if( p.getPieceArray().get(i).getType() == PieceType.PAWN ) {
				board.getBoardArray(pawnRow, pw).setPiece(p.getPieceArray(i));
				pw++;
			}
			if(p.getPieceArray().get(i).getType() != PieceType.PAWN) {
				board.getBoardArray(otherPieceRow, min).setPiece(p.getPieceArray(i));
				board.getBoardArray(otherPieceRow, max).setPiece(p.getPieceArray(i+1));
				min++;
				max--;
				i++;
			}				
		}
	}
	
	
	// Switch between the 2 players
	
	public void playerSwitch(Player playerArray[]) {
		Player temp = playerArray[0];
		playerArray[0] = playerArray[1];
		playerArray[1] = temp;
	}
	
	// Player picks from where to move
	
	public String fromPosition(Player p) {
		System.out.println(p.getName() + " pick the position of the piece you want to move: ");
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}
	
	// Player picks where to move
	
	public String toPosition(Player p) {
		System.out.println(p.getName() + " pick the position where you want to move the piece: ");
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}
	
	// Checks if any player is in check
	
	public boolean isCheck(Player p) {
		boolean answer = false;
		int kingRowPosition = 0;
		int kingColumnPosition = 0;
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if(board.getBoardArray(i, j).getPiece().getType() == PieceType.KING
					&& board.getBoardArray(i, j).getPiece().getSide() == p.getSide()) {
						
						kingRowPosition = i;
						kingColumnPosition = j;
						break;
				}
			}
		}
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if(board.getBoardArray(i, j).getPiece().getSide() != p.getSide()
					&& board.getBoardArray(i, j).getPiece().getSide() != null
					&& board.getBoardArray(i, j).getPiece().isValidMove(board, board.getCellInt(i, j).getIndex(), board.getCellInt(kingRowPosition, kingColumnPosition).getIndex())) {
						
						answer = true;
						
						if(board.getBoardArray(i, j).getPiece().getType() == PieceType.QUEEN
							|| board.getBoardArray(i, j).getPiece().getType() == PieceType.ROOK
							|| board.getBoardArray(i, j).getPiece().getType() == PieceType.BISHOP) {
							
								String from = board.cellIntPosToStringPos(i, j);
								String to = board.cellIntPosToStringPos(kingRowPosition, kingColumnPosition);
								
								board.getBoardArray(i, j).getPiece().setTrajectory(board, from, to);
							
						}
				}
			}
		}
		
		return answer;
	}
	
	
	// Checks if any player is check-mate
	
	public boolean isCheckMate(Player p) {
		int kingRow = 0;
		int kingCol = 0;
		
		for(int i = 0 ; i < board.getBoardRowNumber() ; i++) {
			for(int j = 0 ; j < board.getBoardColumnNumber() ; j++) {
				if(p.getSide() == board.getCellInt(i, j).getPiece().getSide()
					&& board.getCellInt(i, j).getPiece().getType() == PieceType.KING) {
					
					kingRow = i;
					kingCol = j;
					
					
				}
			}
		}
		
		if(isCheck(p) && !isKingMovingOutOfCheck(kingRow, kingCol)) {
			
			// Ramas de vazut daca mutarea vreunei piese poate scoate din sah-mat
			
		}
	}
	
	// Checks if the king can move out of check
	
	public boolean isKingMovingOutOfCheck(int row, int col) {
		boolean answer = false;
		
		for(int i = row - 1 ; i <= row + 1 ; i++) {
			for(int j = col - 1 ; j <= col + 1 ; j++) {
				if(board.getCellInt(row, col).getPiece().isValidMove(board, board.cellIntPosToStringPos(row, col),  board.cellIntPosToStringPos(i, j))) {
					answer = true;
					break;
				}
			}
		}
		
		return answer;
	}
	
	// Checks if the game is tied
	
	public void tieCheck(int step) {
		
	}
	
	// Checks which player won the game
	
//	public GameState whoWon() {
//		
//	}
	
	
	public void game() {
		Player playerArray[] = new Player[2];
		playerArray[0] = p1;
		playerArray[1] = p2;
		String name;
		int step = 0;
		
		
		nameSelection(p1, p2);
		sideSelection();
		setPiecesOnBoard(p1, p1.getSide());
		setPiecesOnBoard(p2, p2.getSide());
		board.displayBoard();
		
		p1.movePiece(board, "a2", "a3");
		p1.movePiece(board, "a3", "a4");
		p1.movePiece(board, "a1", "a3");
		p1.movePiece(board, "a3", "f3");
		p1.movePiece(board, "b2", "b3");
		p1.movePiece(board, "c1", "a3");
		p1.movePiece(board, "d1", "c1");
		p1.movePiece(board, "f1", "e8");
		p1.movePiece(board, "a3", "e7");
		p1.movePiece(board, "c1", "a3");
		p1.movePiece(board, "a3", "b4");
		p1.movePiece(board, "b4", "b7");
		p1.movePiece(board, "e7", "d6");
		p1.movePiece(board, "d6", "f4");
		p2.movePiece(board, "e8", "e7");
		p1.movePiece(board, "f3", "d3");
		p1.movePiece(board, "f4", "h6");
		board.displayBoard();
		p2.movePiece(board, "e7", "e6");
		p2.movePiece(board, "e6", "d6");
//		p1.movePiece(board, "e2", "e3");
//		p1.movePiece(board, "e3", "e4");
//		p2.movePiece(board, "e5", "e4");
//		p2.movePiece(board, "e4", "f3");
//		p1.movePiece(board, "e1", "e2");
//		p1.movePiece(board, "e2", "e3");
//		p1.movePiece(board, "e2", "f3");
//		p1.movePiece(board, "c2", "c3");
//		p2.movePiece(board, "e4", "f3");
//		p1.movePiece(board, "e2", "e1");
//		p2.movePiece(board, "e4", "d3");
//		p2.movePiece(board, "d3", "c3");
//		p2.movePiece(board, "e8", "e7");
		
		
		board.displayBoard();
		//System.out.println(isCheck(p2));
		
		
		//board.displayBoard();
		
//		while(gamestate == GameState.RUNNING) {
//			
//			switch(gamestate) {
//				case RUNNING :
//					String fromPosition = fromPosition(playerArray[0]);
//					String toPosition = toPosition(playerArray[0]);
//					playerArray[0].movePiece(board, fromPosition, toPosition);
//					board.displayBoard();
//					playerSwitch(playerArray);
//					
//					break;
//			}
//		}	
	}
	
	public static void main(String []args) {
		new Game().game();
	}
}
