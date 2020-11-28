package Logics;

import java.awt.*;

import Additions.Horns;
import Additions.HornsRect;
import Additions.HornsOval;

public class ElLocomotive extends Locomotive {

    private Color dopColor;
    private boolean backLine;
    private boolean frontBumper;
    private boolean upperPipe;
    private IAdditions additions;

    public ElLocomotive(int maxSpeed, float weight, Color mainColor, Color dopColor,
                        boolean backLine, boolean frontBumper, boolean upperPipe, int addition, int digit) {
        super(maxSpeed, weight, mainColor, 200, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.backLine = backLine;
        this.frontBumper = frontBumper;
        this.upperPipe = upperPipe;
        switch (addition) {
            case 0:
                additions = new Horns(digit);
                break;
            case 1:
                additions = new HornsRect(digit);
                break;
            case 2:
                additions = new HornsOval(digit);
                break;
        }
    }

    public Color getOtherColor() {
        return dopColor;
    }

    public void setOtherColor(Color otherColor) {
        this.dopColor = otherColor;
    }

    public boolean getUpperPipe() {
        return upperPipe;
    }

    private void setUpperPipe(boolean upperPipe) {
        this.upperPipe = upperPipe;
    }

    public boolean getBackLine() {
        return backLine;
    }

    private void setBackLine(boolean backLine) {
        this.backLine = backLine;
    }

    public boolean getFrontBumper() {
        return frontBumper;
    }

    private void setFrontBumper(boolean frontBumper) {
        this.frontBumper = frontBumper;
    }


    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        if (backLine) {
            for (int i = 0; i < 30; i += 3) {
                g.fillRect(posX + 120 - i, posY + i, 20, 20);
            }
        }

        super.draw(g);

        if (frontBumper) {
            g.fillOval(posX - 12, posY + 18, 16, 20);
            g.drawLine(posX - 8, posY + 24, posX + 6, posY - 4);
        }

        if (upperPipe) {
            g.fillOval(posX + 20, posY - 4, 100, 6);
        }

        additions.draw(g, getOtherColor(), posX, posY);
    }
}
