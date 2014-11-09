package san.chess.pieces;

import san.chess.Color;
import san.chess.Position;

public abstract class Piece {

  private final Color color;

  private final Position position;

  public Piece(Color color, Position position) {
    this.color = color;
    this.position = position;
  }

  protected abstract String whiteSymbol();

  protected abstract String blackSymbol();

  @Override
  public final String toString() {
    return color == Color.WHITE ? whiteSymbol() : blackSymbol();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((color == null) ? 0 : color.hashCode());
    result = prime * result + ((position == null) ? 0 : position.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Piece))
      return false;
    Piece other = (Piece) obj;
    if (color != other.color)
      return false;
    if (position == null) {
      if (other.position != null)
        return false;
    }
    else if (!position.equals(other.position))
      return false;
    return true;
  }

}
