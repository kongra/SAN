package san.chess;

public final class Position {

  private final File file;

  private final Rank rank;

  public Position(File file, Rank rank) {
    this.file = file;
    this.rank = rank;
  }

  @Override
  public String toString() {
    return file.toString() + rank.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((file == null) ? 0 : file.hashCode());
    result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
    Position other = (Position) obj;
    if (file != other.file)
      return false;
    if (rank != other.rank)
      return false;
    return true;
  }

}
