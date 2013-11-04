package san.concurrent.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

public class ParticlePlane extends JComponent {

  private static final int SIZE = 10;

  private final List<Particle> particles;

  private final Random random;

  public ParticlePlane() {
    super();
    setBackground(Color.WHITE);

    particles = new ArrayList<Particle>(SIZE);
    random = new Random();

    for (int i = 0; i < SIZE; i++) {
      particles.add(new Particle(random, this, 800, 600));
    }
  }

  public void start() {
    for (Particle p : particles) {
      new Thread(p).start();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (Particle p : particles) {
      p.draw(g2d);
    }
  }

}
