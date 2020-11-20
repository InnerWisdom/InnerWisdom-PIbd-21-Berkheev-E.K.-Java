package Frame;

import Logics.DepoCollection;
import Logics.IAdditions;
import Logics.Depo;
import Logics.Locomotive;

import javax.swing.*;
import java.awt.*;

public class DrawPanelDepo extends JPanel {
    private final DepoCollection depoCollection;
    private String selectedItem = null;

    public DrawPanelDepo(DepoCollection depoCollection) {
        this.depoCollection = depoCollection;
    }

    protected void paintComponent(Graphics g) {
        if (selectedItem != null) {
            Graphics2D g2 = (Graphics2D) g;
            if (depoCollection != null) {
                depoCollection.get(selectedItem).draw(g2);
            }
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
