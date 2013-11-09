package san.concurrent.simula01;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

  public MainFrame() throws HeadlessException {
    super();

    setSize(800, 600);
    setLocation(100, 100);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    final int N = 100;
    final int SPEED = 20;
    final List<Particle> particles = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      particles.add(new Particle(Particle.random.nextInt(800), Particle.random
          .nextInt(600), Particle.random.nextInt(SPEED) + 1, Particle.random
          .nextInt(SPEED) + 1, Particle.random.nextInt(300)));
    }

    final ParticlesPane pane = new ParticlesPane(particles);
    for (Particle particle : particles) {
      particle.setPane(pane);
    }

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent e) {
        for (Particle particle : particles) {
          new Thread(particle).start();
        }
      }
    });

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(pane, BorderLayout.CENTER);
  }

}
