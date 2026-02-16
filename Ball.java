import java.awt.Graphics;
import java.awt.Color;
public class Ball {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int radius;
    private Color color;
    private int screenWidth;
    private int screenHeight;

    public Ball(double x, double y, double vx, double vy, int radius, Color color, int screenWidth, int screenHeight) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.color = color;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    //getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    //setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update() {
        x += vx;
        y += vy;

        // left/right walls
        if (x - radius <= 0) {
            x = radius;
            vx = -vx;
        } else if (x + radius >= screenWidth) {
            x = screenWidth - radius;
            vx = -vx;
        }
        // top/bottom walls
        if (y - radius <= 0) {
            y = radius;
            vy = -vy;
        } else if (y + radius >= screenHeight) {
            y = screenHeight - radius;
            vy = -vy;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
    }

    public void updateBoundaries(int newWidth, int newHeight) {
        screenWidth = newWidth;
        screenHeight = newHeight;
    }
}

