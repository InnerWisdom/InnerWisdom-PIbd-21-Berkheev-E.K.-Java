package Frame;

import EnumCollections.Direction;
import Logics.ElLocomotive;
import Logics.Locomotive;
import Logics.ITransport;

import javax.swing.*;
import java.awt.*;

public class FrameLocomotive {
    private final JFrame frame;
    private final JComboBox<String> listOfCount;
    private final JComboBox<String> listOfAdditions;
    private final DrawPanel drawPanel;

    public void setLocomotive(ITransport locomotive) {
        locomotive.setPosition((int) (10 + Math.random() * 90), (int) (100 + Math.random() * 100), frame.getWidth(), frame.getHeight());
        drawPanel.setLocomotive(locomotive);
        frame.repaint();
    }
    public FrameLocomotive() {
        frame = new JFrame("ElLocomotive");
        frame.setSize(900, 500);
        frame.setVisible(true);
        frame.setResizable(false);

        Icon left = new ImageIcon("Rec\\Left.png");
        Icon right = new ImageIcon("Rec\\Right.png");
        Icon up = new ImageIcon("Rec\\Up.png");
        Icon down = new ImageIcon("Rec\\Down.png");

        JButton btnCreateLocomotive = new JButton("Create Locomotive");
        JButton btnCreateElLocomotive = new JButton("Create ElLocomotive");
        JButton btnUp = new JButton(up);
        btnUp.setName("up");
        JButton btnDown = new JButton(down);
        btnDown.setName("down");
        JButton btnLeft = new JButton(left);
        btnLeft.setName("left");
        JButton btnRight = new JButton(right);
        btnRight.setName("right");

        frame.getContentPane().add(btnCreateLocomotive);
        frame.getContentPane().add(btnCreateElLocomotive);
        frame.getContentPane().add(btnUp);
        frame.getContentPane().add(btnDown);
        frame.getContentPane().add(btnLeft);
        frame.getContentPane().add(btnRight);

        btnCreateLocomotive.setBounds(10, 10, 180, 30);
        btnCreateElLocomotive.setBounds(200, 10, 180, 30);
        btnUp.setBounds(805, 375, 30, 30);
        btnDown.setBounds(805, 410, 30, 30);
        btnLeft.setBounds(770, 410, 30, 30);
        btnRight.setBounds(840, 410, 30, 30);

        btnCreateLocomotive.addActionListener(e -> setLocomotive());
        btnCreateElLocomotive.addActionListener(e -> setElLocomotive());
        btnUp.addActionListener(e -> setDirection(btnUp));
        btnDown.addActionListener(e -> setDirection(btnDown));
        btnLeft.addActionListener(e -> setDirection(btnLeft));
        btnRight.addActionListener(e -> setDirection(btnRight));

        listOfAdditions = new JComboBox<>(new String[]{"Rhombus", "Square", "Oval"});
        frame.getContentPane().add(listOfAdditions);
        listOfAdditions.setBounds(10, 45, 180, 30);
        listOfAdditions.addActionListener(e -> changeCounts());

        listOfCount = new JComboBox<>(new String[]{"1 horn-rhombus", "2 horn-rhombus", "3 horn-rhombus"});
        frame.getContentPane().add(listOfCount);
        listOfCount.setBounds(200, 45, 180, 30);

        drawPanel = new DrawPanel();
        frame.getContentPane().add(drawPanel);
        drawPanel.setBounds(0, 0, 900, 500);
        frame.repaint();

    }


    private void setDirection(JButton button) {
        String name = button.getName();
        switch (name) {
            case "up":
                drawPanel.getLocomotive().moveTransport(Direction.Up);
                break;
            case "down":
                drawPanel.getLocomotive().moveTransport(Direction.Down);
                break;
            case "left":
                drawPanel.getLocomotive().moveTransport(Direction.Left);
                break;
            case "right":
                drawPanel.getLocomotive().moveTransport(Direction.Right);
                break;
        }
        frame.repaint();
    }

    private void setLocomotive() {
        drawPanel.setLocomotive(new Locomotive(150, 1500, Color.YELLOW));
        drawPanel.getLocomotive().setPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 100), 850, 450);
        frame.repaint();
    }

    private void setElLocomotive() {
        drawPanel.setLocomotive(new ElLocomotive(150, 1500, Color.GREEN, Color.RED, true, true, true, listOfAdditions.getSelectedIndex(), listOfCount.getSelectedIndex()));
        drawPanel.getLocomotive().setPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 100), 850, 450);
        frame.repaint();
    }

    private void changeCounts() {
        listOfCount.removeAllItems();
        switch (listOfAdditions.getSelectedIndex()) {
            case 0:
                listOfCount.addItem("1 horn-rhombus");
                listOfCount.addItem("2 horns-rhombus");
                listOfCount.addItem("3 horns-rhombus");
                break;
            case 1:
                listOfCount.addItem("1 horn-square");
                listOfCount.addItem("2 horns-square");
                listOfCount.addItem("3 horns-square");
                break;
            case 2:
                listOfCount.addItem("1 horn-oval");
                listOfCount.addItem("2 horns-oval");
                listOfCount.addItem("3 horns-oval");
                break;
        }
    }
}
