package Frame;

import Logics.ITransport;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private ITransport locomotive;

    public void paintComponent(Graphics g) {
        if (locomotive != null) locomotive.draw(g);
    }

    public ITransport getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(ITransport locomotive) {
        this.locomotive = locomotive;
    }
}
