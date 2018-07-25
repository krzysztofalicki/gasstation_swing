import java.awt.*;

import static java.lang.Thread.sleep;

public class Auto {

    private int x;
    private int y;
    private final int width = 50;
    private final static Color color = Color.BLUE;
    private int speed;

    public Auto(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public synchronized void incrementPositionX() {
        x++;
    }

    public synchronized void decrementPositionX() {
        x--;
    }

    public synchronized void decrementPositionY() {
        y--;
    }

    public synchronized void incrementPositionY() {
        y++;
    }

    public void droga1() throws InterruptedException {
        for (int i = 0; i < 150; i++) {
            sleep(this.getSpeed());
            this.incrementPositionY();
        }
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.incrementPositionX();
        }
    }

    public void droga2() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            sleep(this.getSpeed());
            this.incrementPositionY();
        }
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.incrementPositionX();
        }
    }

    public void droga3() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            sleep(this.getSpeed());
            this.decrementPositionY();
        }
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.incrementPositionX();
        }
    }

    public void droga4() throws InterruptedException {
        for (int i = 0; i < 150; i++) {
            sleep(this.getSpeed());
            this.decrementPositionY();
        }
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.incrementPositionX();
        }
    }

    public void powrot1() throws InterruptedException {
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.decrementPositionX();
        }
        for (int i = 0; i < 150; i++) {
            sleep(this.getSpeed());
            this.decrementPositionY();
        }
    }

    public void powrot2() throws InterruptedException {
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.decrementPositionX();
        }
        for (int i = 0; i < 50; i++) {
            sleep(this.getSpeed());
            this.decrementPositionY();
        }
    }

    public void powrot3() throws InterruptedException {
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.decrementPositionX();
        }
        for (int i = 0; i < 50; i++) {
            sleep(this.getSpeed());
            this.incrementPositionY();
        }
    }

    public void powrot4() throws InterruptedException {
        for (int i = 0; i < 250; i++) {
            sleep(this.getSpeed());
            this.decrementPositionX();
        }
        for (int i = 0; i < 150; i++) {
            sleep(this.getSpeed());
            this.incrementPositionY();
        }
    }

    public int getPosition() {
        return x;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
