package Logics;

import EnumCollections.Direction;

import java.awt.*;

public abstract class Vehicle implements ITransport {

    protected int posX;
    protected int posY;
    protected int frameWidth;
    protected int frameHeight;

    protected int maxSpeed;
    protected float weight;
    protected Color mainColor;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    protected void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    protected void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public void setPosition(int posX, int posY, int frameWidth, int frameHeight) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        if (posX >= 0 && posX < frameWidth &&
                posY >= 0 && posY < frameHeight) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    public abstract void moveTransport(Direction direction);

    public abstract void draw(Graphics g);
}
