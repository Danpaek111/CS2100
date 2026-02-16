import javax.swing.*;

public class BallAnimation extends JFrame {
    private BallPanel ballPanel;

    public BallAnimation() {
        ballPanel = new BallPanel();

        this.add(ballPanel);
        this.setTitle("Bouncing Balls - Press SPACE to add, C to clear");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Request focus for the panel so key bindings work
        ballPanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BallAnimation();
        });
    }
}

