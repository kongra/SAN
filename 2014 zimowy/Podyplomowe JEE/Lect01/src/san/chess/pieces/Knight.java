package san.chess.pieces;

import san.chess.Color;
import san.chess.Position;

public final class Knight extends Piece {

  public Knight(Color color, Position position) {
    super(color, position);
  }

  @Override
  protected String whiteSymbol() {
    return "♘";
  }

  @Override
  protected String blackSymbol() {
    return "♞";
  }

}
