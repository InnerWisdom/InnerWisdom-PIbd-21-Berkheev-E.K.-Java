package Logics;

import EnumCollections.Direction;

import java.awt.*;
import java.lang.Object;
import java.util.Iterator;
import java.util.LinkedList;

public class Locomotive extends Vehicle implements Iterable<Object>,Iterator<String>,Comparable<Locomotive> {

    public LinkedList<Object> objectProperties= new LinkedList<Object>();

    protected int locomotiveWidth = 200;
    protected int locomotiveHeight = 100;

    protected final String separator = ";";

    protected final String separatorForColor = "-";

    private int currentIndex=0;

    public boolean hasNext(){
        return(currentIndex++<3);
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
    public Locomotive(int maxSpeed, int weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    public Locomotive(String info) {
        String[] infoStrs = info.split(separator);
        if (infoStrs.length == 3) {
            maxSpeed = Integer.parseInt(infoStrs[0]);
            weight = Integer.parseInt(infoStrs[1]);
            String[] colorStrs = infoStrs[2].split(separatorForColor);
            mainColor = new Color(Integer.parseInt(colorStrs[0]), Integer.parseInt(colorStrs[1]), Integer.parseInt(colorStrs[2]));
            objectProperties.add(maxSpeed);
            objectProperties.add(weight);
            objectProperties.add(mainColor);
        }
    }

    protected Locomotive(int maxSpeed, int weight, Color mainColor, int locomotiveWidth, int locomotiveHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.locomotiveWidth = locomotiveWidth;
        this.locomotiveHeight = locomotiveHeight;
        objectProperties.add(maxSpeed);
        objectProperties.add(weight);
        objectProperties.add(mainColor);
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

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRed() + separatorForColor + mainColor.getGreen() + separatorForColor + mainColor.getBlue();
    }

    public boolean Equals(Locomotive otherLocomotive) {
        if (otherLocomotive == null) {
            return false;
        }
        if (this.getClass().getName() != otherLocomotive.getClass().getName()) {
            return false;
        }
        if (this.maxSpeed != otherLocomotive.maxSpeed) {
            return false;
        }
        if (this.weight != otherLocomotive.weight) {
            return false;
        }
        if (this.mainColor != otherLocomotive.mainColor) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Locomotive)) {
            return false;
        } else {
            return Equals((Locomotive) obj);
        }
    }

    public int compareTo(Locomotive anotherLocomotive) {
        if(this.maxSpeed != anotherLocomotive.maxSpeed) {
            return this.maxSpeed - anotherLocomotive.maxSpeed;
        }
        if(this.weight != anotherLocomotive.weight){
            return this.weight - anotherLocomotive.weight;
        }
        if(this.mainColor.getRGB() != anotherLocomotive.mainColor.getRGB()){
            return this.mainColor.getRGB() - anotherLocomotive.mainColor.getRGB();
        }
        return 0;
    }

}
