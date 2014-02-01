package san.prog.species;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okienko extends JFrame {

  private final JPanel panelZKreską = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.BLACK);

      g.drawLine(10, 10, 200, 200);
    }
  };

  public Okienko() throws HeadlessException {
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelZKreską, BorderLayout.CENTER);

    setSize(800, 600);
    setLocation(20, 20);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String... args) {
    new Okienko().setVisible(true);
  }
}
