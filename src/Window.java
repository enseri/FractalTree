import java.awt.Dimension;
import javax.swing.*;

public class Window extends JFrame {
    public Window(int width, int height, String title, Tree tree) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(tree);
        frame.setVisible(true);
        tree.start();
    }
}