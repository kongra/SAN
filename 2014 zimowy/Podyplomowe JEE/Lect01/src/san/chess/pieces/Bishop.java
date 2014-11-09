package san.chess.pieces;

import san.chess.Color;
import san.chess.Position;

public class Bishop extends Piece {

  public Bishop(Color color, Position position) {
    super(color, position);
  }

  @Override
  protected String whiteSymbol() {
    return "♗";
  }

  @Override
  protected String blackSymbol() {
    return "♝";
  }


}
