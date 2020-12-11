package Additions;

import EnumCollections.CountOfHorns;
import Logics.IAdditions;

import java.awt.*;

public class HornsRect implements IAdditions {

    private CountOfHorns count;

    public int getNumber(){
        if (count == CountOfHorns.one){
            return 0;
        }
        if (count == CountOfHorns.two){
            return 1;
        }
        if (count == CountOfHorns.three){
            return 2;
        }
        return -1;
    }

    public HornsRect(int digit) {
        setDigit(digit);
    }

    public void setDigit(int digit) {
        this.count = CountOfHorns.getCount(digit);
    }

    public void draw(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.drawRect(x - 14, y - 25, 35, 26);
        if (count == CountOfHorns.two || count == CountOfHorns.three) {
            g.drawRect(x + 44, y - 25, 35, 26);
            if (count == CountOfHorns.three) {
                g.drawRect(x + 94, y - 25, 35, 26);
            }
        }
    }
}
