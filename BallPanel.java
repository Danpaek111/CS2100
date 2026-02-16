import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BallPanel extends JPanel implements ActionListener {
    private ArrayList<Ball> balls;
    private Timer timer;
    private Random random;
    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 600;
    private final int DELAY = 16; // ~60 FPS

    public BallPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        balls = new ArrayList<>();
        random = new Random();

        // Create some initial balls
        createRandomBalls(5);

        // Setup key bindings
        setupKeyBindings();

        // Start the animation timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void setupKeyBindings() {
        // Get the input map for when the component has focus
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        // Bind SPACE key to add ball action
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "addBall");
        actionMap.put("addBall", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRandomBall();
            }
        });

        // Bind multiple variations of C key to clear balls action
        inputMap.put(KeyStroke.getKeyStroke("C"), "clearBalls");
        inputMap.put(KeyStroke.getKeyStroke("c"), "clearBalls");
        inputMap.put(KeyStroke.getKeyStroke("pressed C"), "clearBalls");
        inputMap.put(KeyStroke.getKeyStroke("pressed c"), "clearBalls");
        inputMap.put(KeyStroke.getKeyStroke("typed c"), "clearBalls");
        inputMap.put(KeyStroke.getKeyStroke("typed C"), "clearBalls");

        actionMap.put("clearBalls", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBalls();
            }
        });

        // Alternative: also bind ESCAPE key to clear (as backup)
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "clearBalls");
    }
    // Create random balls
    private void createRandomBalls(int numBalls) {
        for (int i = 0; i < numBalls; i++) {
            double x = random.nextInt(PANEL_WIDTH - 100) + 50;
            double y = random.nextInt(PANEL_HEIGHT - 100) + 50;
            double vx = random.nextDouble() * 6 - 3; // Random velocity between -3 and 3
            double vy = random.nextDouble() * 6 - 3;
            int radius = random.nextInt(20) + 10; // Random radius between 10 and 30
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            Ball ball = new Ball(x, y, vx, vy, radius, color, PANEL_WIDTH, PANEL_HEIGHT);
            balls.add(ball);
        }
    }

    // Add a new ball at random position
    public void addRandomBall() {
        double x = random.nextInt(PANEL_WIDTH - 100) + 50;
        double y = random.nextInt(PANEL_HEIGHT - 100) + 50;
        double vx = random.nextDouble() * 8 - 4;
        double vy = random.nextDouble() * 8 - 4;
        int radius = random.nextInt(25) + 5;
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        Ball ball = new Ball(x, y, vx, vy, radius, color, PANEL_WIDTH, PANEL_HEIGHT);
        balls.add(ball);
        System.out.println("Ball added! Total balls: " + balls.size()); // Debug output
    }

    // Remove all balls
    public void clearBalls() {
        balls.clear();
        System.out.println("All balls cleared!"); // Debug output
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable anti-aliasing for smoother graphics
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw all balls
        for (Ball ball : balls) {
            ball.draw(g2d);
        }

        // Draw instructions
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Balls: " + balls.size(), 10, 20);
        g2d.drawString("Press SPACE to add ball, C to clear", 10, 35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update all balls
        for (Ball ball : balls) {
            ball.update();
        }
        repaint();
    }
}

