package san.concurrent.simula01;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JComponent;

public class ParticlesPane extends JComponent {

  private final List<Particle> particles;

  public ParticlesPane(List<Particle> particles) {
    super();
    this.particles = particles;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics2D g2d = (Graphics2D) g;
    for (Particle p : particles) {
      p.paint(g2d);
    }    
  }
  

}
