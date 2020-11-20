package Frame;

import Logics.IAdditions;
import Logics.Depo;
import Logics.Locomotive;
import Logics.ElLocomotive;

import javax.swing.*;
import javax.swing.border.Border;

public class FrameDepo {

    private JFrame frame;
    private JTextField fieldTakeIndex;
    private JTextField fieldCompare;
    private Depo<Locomotive, IAdditions> depo;

    public void init() {
        frame = new JFrame("Депо");
        frame.setSize(1300, 950);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        depo = new Depo<>(1120, 800);

        DrawPanelDepo drawPanelDepo = new DrawPanelDepo(depo);
        JButton buttonCreateLocomotive = new JButton("Создать локомотив");
        JButton buttonCreateElLocomotive = new JButton("Создать электровоз");
        JButton buttonTakeLocomotive = new JButton("Забрать");
        JLabel labelNumber = new JLabel("Место");

        JPanel takeGroupBox = new JPanel();
        Border centerBorder = BorderFactory.createTitledBorder("Забрать локомотив");
        takeGroupBox.setBorder(centerBorder);
        fieldTakeIndex = new JFormattedTextField();

        JLabel labelCompare = new JLabel("Количество");
        fieldCompare = new JTextField("");
        JButton notEquals = new JButton("!=");
        JButton Equals = new JButton("==");
        JPanel compareGroupBox = new JPanel();
        centerBorder = BorderFactory.createTitledBorder("Сравнить");
        compareGroupBox.setBorder(centerBorder);

        frame.getContentPane().add(buttonCreateElLocomotive);
        frame.getContentPane().add(buttonCreateLocomotive);
        frame.getContentPane().add(buttonTakeLocomotive);
        frame.getContentPane().add(drawPanelDepo);
        takeGroupBox.add(fieldTakeIndex);
        takeGroupBox.add(buttonTakeLocomotive);
        takeGroupBox.add(labelNumber);
        frame.getContentPane().add(takeGroupBox);
        compareGroupBox.add(labelCompare);
        compareGroupBox.add(fieldCompare);
        compareGroupBox.add(notEquals);
        compareGroupBox.add(Equals);
        frame.getContentPane().add(compareGroupBox);


        drawPanelDepo.setBounds(0, 0, 1250, 850);
        buttonCreateLocomotive.setBounds(1070, 20, 200, 30);
        buttonCreateElLocomotive.setBounds(1070, 60, 200, 30);

        takeGroupBox.setBounds(1070, 120, 200, 100);
        labelNumber.setBounds(40, 20, 75, 30);
        fieldTakeIndex.setBounds(105, 20, 75, 30);
        buttonTakeLocomotive.setBounds(20, 60, 160, 30);

        compareGroupBox.setBounds(1070, 240, 200, 100);
        labelCompare.setBounds(30, 20, 75, 30);
        fieldCompare.setBounds(105, 20, 75, 30);
        Equals.setBounds(20, 60, 75, 30);
        notEquals.setBounds(105, 60, 75, 30);

        buttonCreateLocomotive.addActionListener(e -> createLocomotive());
        buttonCreateElLocomotive.addActionListener(e -> createElLocomotive());
        buttonTakeLocomotive.addActionListener(e -> takeLocomotive());
        Equals.addActionListener(e -> compareDepoCount(Equals.getText()));
        notEquals.addActionListener(e -> compareDepoCount(notEquals.getText()));

        frame.repaint();
    }

    private void createLocomotive() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            Locomotive locomotive = new Locomotive(100, 1000, colorDialog.getColor());
            if (depo.append(locomotive)) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Депо переполнено");
            }
        }
    }

    private void createElLocomotive() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            JColorChooser otherColorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, otherColorDialog);
            if (otherColorDialog.getColor() != null) {
                ElLocomotive locomotive = new ElLocomotive(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(), true, true, true, 0, 0);
                if (depo.append(locomotive)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Гараж переполнен");
                }
            }
        }
    }

    private void takeLocomotive() {
        if (!fieldTakeIndex.getText().equals("")) {
            try {
                Locomotive locomotive = depo.remove(Integer.parseInt(fieldTakeIndex.getText()));
                if (locomotive != null) {
                    FrameLocomotive frameLocomotive = new FrameLocomotive();
                    frameLocomotive.setLocomotive(locomotive);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Локомотива с таким индексом нет!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Локомотива с таким индексом нет!");
            }
        }
    }

    private void compareDepoCount(String operation) {
        int count = Integer.parseInt(fieldCompare.getText());
        if (operation.equals("==")) {
            if (depo.Equals(count)) {
                JOptionPane.showMessageDialog(frame, "The amount of locomotives is " + count);
            } else {
                JOptionPane.showMessageDialog(frame, "The amount of locomotives is more or less then " + count);
            }
        } else if (operation.equals("!=")) {
            if (depo.notEquals(count)) {
                JOptionPane.showMessageDialog(frame, "The amount of locomotives is not " + count);
            } else {
                JOptionPane.showMessageDialog(frame, "The amount of locomotives is" + count);
            }
        }
    }


}
