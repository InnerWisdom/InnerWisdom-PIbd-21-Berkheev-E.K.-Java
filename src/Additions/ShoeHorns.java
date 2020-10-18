package Additions;


import EnumCollections.CountOfShoeHorns;
import Logics.IAdditions;

import java.awt.*;
import java.util.Objects;

public class ShoeHorns implements IAdditions {

    private CountOfShoeHorns count;

    public ShoeHorns(int digit) {
        setDigit(digit);
    }

    public void setDigit(int digit) {
        count = CountOfShoeHorns.getCount(digit);
    }

    public void draw(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        switch (Objects.requireNonNull(count)) {
            case one:
                g.drawOval(x - 10, y + 5, 10, 4);
                g.drawOval(x - 15, y + 6, 5, 2);
                break;
            case two:
                g.drawOval(x - 10, y + 5, 10, 4);
                g.drawOval(x - 15, y + 6, 5, 2);

                g.drawOval(x - 10, y + 9, 10, 4);
                g.drawOval(x - 15, y + 10, 5, 2);
                break;
            case three:
                g.drawOval(x - 10, y + 5, 10, 4);
                g.drawOval(x - 15, y + 6, 5, 2);

                g.drawOval(x - 10, y + 9, 10, 4);
                g.drawOval(x - 15, y + 10, 5, 2);

                g.drawOval(x - 10, y + 1, 10, 4);
                g.drawOval(x - 15, y + 2, 5, 2);
                break;

        }
    }
}
