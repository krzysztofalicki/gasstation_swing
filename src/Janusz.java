import java.awt.*;

public class Janusz {

    private int x;
    private int y;
    private final int width = 10;
    private final static Color color = Color.MAGENTA;
    private final static int speed = 15;

    public Janusz(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public synchronized void incrementPosition() {
        y++;
    }

    public synchronized void decrementPosition() {
        y--;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
