package san.concurrent.particles;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class Frame extends JFrame {

  private final ParticlePlane plane;

  public Frame() {
    super();

    setLocation(100, 100);
    setSize(800, 600);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    getContentPane().setLayout(new BorderLayout());
    plane = new ParticlePlane();
    getContentPane().add(plane, BorderLayout.CENTER);

    addComponentListener(new ComponentAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void componentShown(ComponentEvent e) {
        plane.start();
      }
    });
  }

}
