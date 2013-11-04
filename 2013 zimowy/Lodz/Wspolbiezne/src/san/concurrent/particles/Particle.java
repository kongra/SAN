package san.concurrent.particles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import san.concurrent.Th;

public class Particle implements Runnable {

  private static final int RADIUS = 3;

  private final Random random;

  private final ParticlePlane plane;

  private int x;

  private int y;

  public Particle(Random random, ParticlePlane plane, int width, int height) {
    this.random = random;
    this.plane = plane;
    this.x = this.random.nextInt(width);
    this.y = this.random.nextInt(height);
  }

  @Override
  public void run() {
    while (true) {
      Th.sleep(300);
      move();
      plane.repaint();
    }
  }

  private void move() {
    int w = random.nextInt(5) * (random.nextBoolean() ? 1 : -1);
    int h = random.nextInt(5) * (random.nextBoolean() ? 1 : -1);

    synchronized (this) {
      this.x += w;
      this.y += h;
    }
  }

  public void draw(Graphics2D g) {
    final int x, y;

    synchronized (this) {
      x = this.x;
      y = this.y;
    }

    final Color color = g.getColor();
    try {
      g.setColor(Color.RED);
      g.draw(new Ellipse2D.Double(x - RADIUS, y - RADIUS, RADIUS * 2,
          RADIUS * 2));
    }
    finally {
      g.setColor(color);
    }
  }

}
