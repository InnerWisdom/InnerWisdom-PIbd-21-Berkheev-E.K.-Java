package Logics;

import EnumCollections.Direction;

import java.awt.*;

public class Locomotive extends Vehicle {

    protected int locomotiveWidth = 200;
    protected int locomotiveHeight = 100;

    public Locomotive(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected Locomotive(int maxSpeed, float weight, Color mainColor, int locomotiveWidth, int locomotiveHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.locomotiveWidth = locomotiveWidth;
        this.locomotiveHeight = locomotiveHeight;
    }

    public void moveTransport(Direction direction) {
        int step = (int) (maxSpeed * 100 / weight);
        switch (direction) {
            case Up:
                if (posY - step > 0) {
                    posY -= step;
                }
                break;
            case Right:
                if (posX + step < frameWidth - locomotiveWidth) {
                    posX += step;
                }
                break;
            case Down:
                if (posY + step < frameHeight - locomotiveHeight) {
                    posY += step;
                }
                break;
            case Left:
                if (posX - step > 0) {
                    posX -= step;
                }
                break;
        }
    }

    public void draw(Graphics g) {
        g.setColor(mainColor);
        g.fillRect(posX + 80, posY, 40, 40);
        g.fillRect(posX, posY, 40, 40);
        g.fillOval(posX + 20, posY, 80, 40);

        g.setColor(Color.BLACK);
        g.drawOval(posX + 20, posY, 80, 40);
        g.drawRect(posX, posY, 40, 40);
        g.drawRect(posX + 80, posY, 40, 40);

        //wheels
        g.fillOval(posX + 3, posY + 40, 10, 10);
        g.fillOval(posX + 13, posY + 40, 10, 10);
        g.fillOval(posX + 23, posY + 40, 10, 10);
        g.fillOval(posX + 33, posY + 40, 10, 10);
        g.fillOval(posX + 83, posY + 40, 10, 10);
        g.fillOval(posX + 93, posY + 40, 10, 10);
        g.fillOval(posX + 103, posY + 40, 10, 10);
        g.fillOval(posX + 113, posY + 40, 10, 10);
        g.fillOval(posX - 3, posY + 38, 135, 10);

    }
}
