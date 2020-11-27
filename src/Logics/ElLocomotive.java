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
    private int digit;
    private int addition;

    public ElLocomotive(int maxSpeed, int weight, Color mainColor, Color dopColor,
                        boolean backLine, boolean frontBumper, boolean upperPipe, int addition, int digit) {
        super(maxSpeed, weight, mainColor, 200, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.backLine = backLine;
        this.frontBumper = frontBumper;
        this.upperPipe = upperPipe;
        this.digit = digit;
        switch (addition) {
            case 0:
                this.additions = new Horns(digit);
                this.addition=addition;
                this.digit=digit;
                break;
            case 1:
                this.additions = new HornsRect(digit);
                this.addition=addition;
                this.digit=digit;
                break;
            case 2:
                this.additions = new HornsOval(digit);
                this.addition=addition;
                this.digit=digit;
                break;
        }
    }

    public ElLocomotive(String info) {
        super(info);
        String[] infoStrs = info.split(separator);
        if (infoStrs.length == 9) {
            maxSpeed = Integer.parseInt(infoStrs[0]);
            weight = Integer.parseInt(infoStrs[1]);
            String[] mainColorStrs = infoStrs[2].split(separatorForColor);
            mainColor = new Color(Integer.parseInt(mainColorStrs[0]), Integer.parseInt(mainColorStrs[1]), Integer.parseInt(mainColorStrs[2]));
            String[] dopColorStrs = infoStrs[3].split(separatorForColor);
            dopColor = new Color(Integer.parseInt(dopColorStrs[0]), Integer.parseInt(dopColorStrs[1]), Integer.parseInt(dopColorStrs[2]));
            backLine = Boolean.parseBoolean(infoStrs[4]);
            frontBumper = Boolean.parseBoolean(infoStrs[5]);
            upperPipe = Boolean.parseBoolean(infoStrs[6]);
            switch (infoStrs[8]) {
                case "0":
                    this.additions = new Horns(Integer.parseInt(infoStrs[7]));
                    break;
                case "1":
                    this.additions = new HornsRect(Integer.parseInt(infoStrs[7]));
                    break;
                case "2":
                    this.additions = new HornsOval(Integer.parseInt(infoStrs[7]));
                    break;
            }
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
    @Override
    public String toString() {
        return super.toString() + separator + dopColor.getRed() + separatorForColor + dopColor.getGreen() +
                separatorForColor + dopColor.getBlue() + separator + backLine + separator + frontBumper
        + separator + upperPipe + separator + digit + separator + addition ;
    }

}
