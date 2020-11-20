package Additions;


import EnumCollections.CountOfHorns;
import Logics.IAdditions;

import java.awt.*;

public class HornsOval implements IAdditions {

    private CountOfHorns count;

    public HornsOval(int digit) {
        setDigit(digit);
    }

    public void setDigit(int digit) {
        this.count = CountOfHorns.getCount(digit);
    }

    public void draw(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.drawOval(x-14,y-25,35,26);
        if (count == CountOfHorns.two || count == CountOfHorns.three) {
            g.drawOval(x+44,y-25,35,26);
            if (count == CountOfHorns.three) {
                g.drawOval(x+94,y-25,35,26);
            }
        }

    }
}
