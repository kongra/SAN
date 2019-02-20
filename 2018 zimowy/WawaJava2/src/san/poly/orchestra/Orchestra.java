package san.poly.orchestra;

public class Orchestra {

  private Musician[] musicians = {
    new Violinist(),
    new GuitarPlayer(),
    new Pianist(),
    new Vocalist()
  };

  public void play() {
    for (int i = 0; i < musicians.length; i++) {
      musicians[i].play();
    }
  }

  public static void main(String[] args) {
    new Orchestra().play();
  }

}
