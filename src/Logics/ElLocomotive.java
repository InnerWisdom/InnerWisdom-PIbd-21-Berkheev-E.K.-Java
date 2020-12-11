package Logics;

import java.awt.*;

import Additions.Horns;
import Additions.HornsRect;
import Additions.HornsOval;

import java.util.Iterator;
import java.util.LinkedList;

public class ElLocomotive extends Locomotive implements Iterable<Object>,Iterator<String>,Comparable<Locomotive> {
    public LinkedList<Object> objectProperties = new LinkedList<Object>();

    private Color dopColor;
    private boolean backLine;
    private boolean frontBumper;
    private boolean upperPipe;
    private IAdditions additions;
    private int digit;
    private String addition;
    private int currentIndex=0;

    public boolean hasNext(){
        return(currentIndex++<9);
    }

    public String next(){
        return objectProperties.get(currentIndex).toString();
    }

    public void remove(){
        objectProperties.remove(currentIndex);
    }

    public Iterator<Object> iterator() {
        return objectProperties.iterator();
    }
    public ElLocomotive(int maxSpeed, int weight, Color mainColor, Color dopColor,
                        boolean backLine, boolean frontBumper, boolean upperPipe, String addition, int digit) {
        super(maxSpeed, weight, mainColor, 200, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.backLine = backLine;
        this.frontBumper = frontBumper;
        this.upperPipe = upperPipe;
        this.digit = digit;
        objectProperties.add(maxSpeed);
        objectProperties.add(weight);
        objectProperties.add(mainColor);
        objectProperties.add(dopColor);
        objectProperties.add(backLine);
        objectProperties.add(frontBumper);
        objectProperties.add(upperPipe);
        objectProperties.add(digit);
        switch (addition) {
            case "Rhombus":
                this.additions = new Horns(digit);
                this.addition=addition;
                this.digit=digit;
                break;
            case "Oval":
                this.additions = new HornsRect(digit);
                this.addition=addition;
                this.digit=digit;
                break;
            case "Rectangle":
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
            objectProperties.add(maxSpeed);
            objectProperties.add(weight);
            objectProperties.add(mainColor);
            objectProperties.add(dopColor);
            objectProperties.add(backLine);
            objectProperties.add(frontBumper);
            objectProperties.add(upperPipe);
            objectProperties.add(digit);
            switch (infoStrs[8]) {
                case "Rhombus":
                    this.additions = new Horns(Integer.parseInt(infoStrs[7]));
                    break;
                case "Oval":
                    this.additions = new HornsRect(Integer.parseInt(infoStrs[7]));
                    break;
                case "Rectangle":
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

    public String getAddition() {
        return addition;
    }

    public int getDigit() {
        return digit;
    }

    public void setHornsForm(IAdditions additions) {
        this.additions = additions;
        if (additions instanceof Horns) {
            this.addition = "Rhombus";
        }
        if (additions instanceof HornsOval) {
            this.addition = "Oval";
        }
        if (additions instanceof HornsRect) {
            this.addition = "Rectangle";
        }
        this.digit = additions.getNumber();
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

    public boolean Equals(ElLocomotive otherElLocomotive) {
        if (otherElLocomotive == null) {
            return false;
        }
        if (this.getClass().getName() != otherElLocomotive.getClass().getName()) {
            return false;
        }
        if (this.maxSpeed != otherElLocomotive.maxSpeed) {
            return false;
        }
        if (this.weight != otherElLocomotive.weight) {
            return false;
        }
        if (this.mainColor != otherElLocomotive.mainColor) {
            return false;
        }
        if (this.dopColor != otherElLocomotive.dopColor) {
            return false;
        }
        if (this.backLine != otherElLocomotive.backLine) {
            return false;
        }
        if (this.frontBumper != otherElLocomotive.frontBumper) {
            return false;
        }
        if (this.upperPipe != otherElLocomotive.upperPipe) {
            return false;
        }
        if (this.addition != otherElLocomotive.addition) {
            return false;
        }
        if (this.digit != otherElLocomotive.digit) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ElLocomotive)) {
            return false;
        } else {
            return Equals((ElLocomotive) obj);
        }
    }

    public int compareTo(ElLocomotive anotherElLocomotive) {
        if (this.maxSpeed != anotherElLocomotive.maxSpeed) {
            return this.maxSpeed - anotherElLocomotive.maxSpeed;
        }
        if (this.weight != anotherElLocomotive.weight) {
            return this.weight - anotherElLocomotive.weight;
        }
        if (this.mainColor.getRGB() != anotherElLocomotive.mainColor.getRGB()) {
            return this.mainColor.getRGB() - anotherElLocomotive.mainColor.getRGB();
        }
        if (this.dopColor.getRGB() != anotherElLocomotive.dopColor.getRGB()) {
            return this.dopColor.getRGB() - anotherElLocomotive.dopColor.getRGB();
        }
        if (this.backLine != anotherElLocomotive.backLine) {
            if (this.backLine) {
                return 1;
            }
            return -1;
        }
        if (this.frontBumper != anotherElLocomotive.frontBumper) {
            if (this.frontBumper) {
                return 1;
            }
            return -1;
        }
        if (this.upperPipe != anotherElLocomotive.upperPipe) {
            if (this.upperPipe) {
                return 1;
            }
            return -1;
        }
        if (this.digit != anotherElLocomotive.digit) {
            return this.digit - anotherElLocomotive.digit;
        }
        if (this.addition != anotherElLocomotive.addition) {
            int firstLocomotiveHornsID = 0;
            int secondLocomotiveHornID = 0;
            switch (this.addition) {
                case "Rhombus":
                    firstLocomotiveHornsID = 0;
                    break;
                case "Oval":
                    firstLocomotiveHornsID = 1;
                    break;
                case "Rectangle":
                    firstLocomotiveHornsID = 2;
                    break;
            }
            switch (anotherElLocomotive.addition) {
                case "Rhombus":
                    secondLocomotiveHornID = 0;
                    break;
                case "Oval":
                    secondLocomotiveHornID = 1;
                    break;
                case "Rectangle":
                    secondLocomotiveHornID = 2;
                    break;
            }
            return firstLocomotiveHornsID - secondLocomotiveHornID;
        }
        return 0;
    }

}
