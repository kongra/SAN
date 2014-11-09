package san.chess.pieces;

import san.chess.Color;
import san.chess.Position;

public class Queen extends Piece {

  public Queen(Color color, Position position) {
    super(color, position);
  }

  @Override
  protected String whiteSymbol() {
    return "♕";
  }

  @Override
  protected String blackSymbol() {
    return "♛";
  }

}
