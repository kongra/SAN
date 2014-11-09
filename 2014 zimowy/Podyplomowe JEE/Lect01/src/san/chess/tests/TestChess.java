package san.chess.tests;

import san.chess.ChessBoard;
import san.chess.Color;
import san.chess.File;
import san.chess.Position;
import san.chess.Rank;
import san.chess.pieces.King;
import san.chess.pieces.Queen;
import san.chess.pieces.Rook;

public class TestChess {

  public static void main(String[] args) {
    // System.out.println(new King(Color.BLACK, new Position(File.A, Rank.ONE)));
    
    ChessBoard start1 = ChessBoard.initial();
    ChessBoard start2 = ChessBoard.initial();
    
    System.out.println(start1.equals(start2));
    
  }

}
