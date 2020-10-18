import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private Locomotive locomotive;

    public void paintComponent(Graphics g) {
        if (locomotive != null) locomotive.draw(g);
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }
}
