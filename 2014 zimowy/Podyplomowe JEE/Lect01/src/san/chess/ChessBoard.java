package san.chess;

import java.util.Arrays;

import san.chess.pieces.Pawn;
import san.chess.pieces.Piece;

public class ChessBoard {

  private final Piece[][] pieces;

  private ChessBoard(Piece[][] pieces) {
    super();
    this.pieces = pieces;
  }

  public static ChessBoard initial() {
    Piece[][] pieces = new Piece[8][8];
    // WHITE PAWNS
    int i = 0;
    for (File f : File.values()) {
      pieces[1][i] = new Pawn(Color.WHITE, new Position(f, Rank.TWO));
      i += 1;
    }

    // BLACK PAWNS
    i = 0;
    for (File f : File.values()) {
      pieces[6][i] = new Pawn(Color.BLACK, new Position(f, Rank.SEVEN));
      i += 1;
    }

    // WHITE PIECES

    // BLACK PIECES

    return new ChessBoard(pieces);
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(pieces);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ChessBoard other = (ChessBoard) obj;
    if (!Arrays.deepEquals(pieces, other.pieces))
      return false;
    return true;
  }

}
