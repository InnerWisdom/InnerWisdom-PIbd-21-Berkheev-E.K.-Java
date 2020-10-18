package Additions;

import EnumCollections.TypeOfCore;
import Logics.IAdditions;

import java.awt.*;
import java.util.Objects;

public class ShapeOfCore implements IAdditions {

    private TypeOfCore core;

    public ShapeOfCore(int digit) {
        setDigit(digit);
    }

    public void setDigit(int digit) {
        core = TypeOfCore.getType(digit);
    }

    public void draw(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        switch (Objects.requireNonNull(core)) {
            case rectangle:
                g.fillRect(x + 70, y, 60, 40);
                g.fillRect(x, y, 40, 40);
                g.fillRect(x + 20, y, 80, 40);

                g.setColor(Color.BLACK);
                g.drawRect(x + 70, y, 60, 40);
                g.drawRect(x, y, 40, 40);
                g.drawRect(x + 20, y, 80, 40);
                break;
            case oval:
                g.fillOval(x + 70, y, 60, 40);
                g.fillOval(x, y, 40, 40);
                g.fillOval(x + 10, y, 100, 40);

                g.setColor(Color.BLACK);
                g.drawOval(x + 70, y, 60, 40);
                g.drawOval(x, y, 40, 40);
                g.drawOval(x + 20, y, 80, 40);
                break;
            case combined:
                g.fillRect(x + 70, y, 60, 45);
                g.fillOval(x, y, 40, 45);
                g.fillOval(x + 20, y, 80, 40);

                g.setColor(Color.BLACK);
                g.drawRect(x + 70, y, 60, 45);
                g.drawOval(x, y, 40, 45);
                g.drawOval(x + 20, y, 80, 45);
                break;

        }
    }
}
