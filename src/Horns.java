import java.awt.*;
import java.util.Objects;

public class Horns {

    private CountOfHorns count;

    public Horns(int digit) {
        setDigit(digit);
    }

    public void setDigit(int digit) {
        this.count = CountOfHorns.getCount(digit);
    }

    public void draw(Graphics g, Color color, int x, int y) {

        g.setColor(color);
        g.drawLine(x + 5, y + 3, x - 14, y - 16);
        g.drawLine(x - 14, y - 16, x + 5, y - 32);

        g.drawLine(x + 5, y + 3, x + 24, y - 16);
        g.drawLine(x + 24, y - 16, x + 5, y - 32);

        if(count ==CountOfHorns.two || count ==CountOfHorns.three){

            g.drawLine(x + 5 + 50, y + 3, x - 14 + 50, y - 16);
            g.drawLine(x - 14 + 50, y - 16, x + 5 + 50, y - 32);

            g.drawLine(x + 5 + 50, y + 3, x + 24 + 50, y - 16);
            g.drawLine(x + 24 + 50, y - 16, x + 5 + 50, y - 32);

            if(count ==CountOfHorns.three){

                g.drawLine(x + 5 + 100, y + 3, x - 14 + 100, y - 16);
                g.drawLine(x - 14 + 100, y - 16, x + 5 + 100, y - 32);

                g.drawLine(x + 5 + 100, y + 3, x + 24 + 100, y - 16);
                g.drawLine(x + 24 + 100, y - 16, x + 5 + 100, y - 32);
            }
        }


    }
}
