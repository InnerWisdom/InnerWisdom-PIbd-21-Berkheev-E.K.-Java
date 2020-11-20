package Frame;

import Logics.IAdditions;
import Logics.Depo;
import Logics.Locomotive;

import javax.swing.*;
import java.awt.*;

public class DrawPanelDepo extends JPanel {
    private final Depo<Locomotive, IAdditions> depo;

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (depo != null) {
            depo.draw(g2);
        }
    }

    public Depo<Locomotive, IAdditions> getDepo() {
        return depo;
    }

    public DrawPanelDepo(Depo<Locomotive, IAdditions> depo) {
        this.depo = depo;
    }
}
