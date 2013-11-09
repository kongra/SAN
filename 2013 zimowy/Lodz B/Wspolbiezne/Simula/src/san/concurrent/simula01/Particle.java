package san.concurrent.simula01;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Particle implements Runnable {

  public static final Random random = new Random();

  private int x;

  private int y;

  private ParticlesPane pane;

  private final int speedX;

  private final int speedY;

  private final long timeout;

  public Particle(int x, int y, int speedX, int speedY, long timeout) {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
    this.speedY = speedY;
    this.timeout = timeout;
  }

  @Override
  public void run() {
    while (true) {
      // 1. IDZIEMY SPAĆ
      try {
        Thread.sleep(timeout);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }

      // 2. MODYFIKACJA STANU
      double dx = random.nextInt(speedX);
      boolean sgnx = random.nextBoolean();

      double dy = random.nextInt(speedY);
      boolean sgny = random.nextBoolean();

      synchronized (this) {
        this.x += dx * (sgnx ? 1 : -1);
        this.y += dy * (sgny ? 1 : -1);
      }

      // 3. MALOWANIE
      final ParticlesPane pane;
      synchronized (this) {
        pane = this.pane;
      }
      pane.repaint();
    }
  }

  public void paint(Graphics2D g2d) {
    final int x, y;

    synchronized (this) {
      x = this.x;
      y = this.y;
    }

    final Color old = g2d.getColor();
    try {
      g2d.setColor(Color.RED);
      g2d.draw(new Ellipse2D.Double(x - 3, y - 3, 6, 6));
    }
    finally {
      g2d.setColor(old);
    }
  }

  public synchronized void setPane(ParticlesPane pane) {
    this.pane = pane;
  }
}
