package Frame;

import Logics.ElLocomotive;
import Logics.Locomotive;
import Logics.Vehicle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

public class FormLocomotiveConfig {

    private final JFrame frame;
    private final DrawPanel drawPanel;
    private final JCheckBox checkBoxBackLine;
    private final JCheckBox checkBoxFBumper;
    private final JCheckBox checkBoxUPipe;
    Vehicle locomotive;
    FrameDepo frameDepo;


    public FormLocomotiveConfig(FrameDepo frameDepo) {

        this.frameDepo=frameDepo;

        frame = new JFrame("Конфигурация");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        checkBoxBackLine = new JCheckBox("BackLine");
        checkBoxFBumper = new JCheckBox("Front Bumper");
        checkBoxUPipe = new JCheckBox("Upper Pipe");

        JButton createLocomotive = new JButton("Создать");
        JButton cancelLocomotive = new JButton("Отмена");
        JLabel lblWeight = new JLabel("Вес");
        SpinnerModel spinnerWeight = new SpinnerNumberModel(1, 1, 600, 1);
        JSpinner spinWeight = new JSpinner(spinnerWeight);
        JLabel lblSpeed = new JLabel("Скорость");
        SpinnerModel spinnerSpeed = new SpinnerNumberModel(1, 1, 600, 1);
        JSpinner spinSpeed = new JSpinner(spinnerSpeed);
        JLabel lblPicture = new JLabel("");
        JLabel MainColor = new JLabel("Main color");
        JLabel DopColor = new JLabel("Dop color");
        JLabel lblLocomotive = new JLabel("Локомотив");
        JLabel lblElLocomotive = new JLabel("Электровоз");
        JLabel lblAdditions= new JLabel("Количество рогов");

        JLabel lblShapeAdditions= new JLabel("Форма рогов");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        lblShapeAdditions.setBorder(border);
        lblAdditions.setBorder(border);
        JLabel lblFirstAddition=new JLabel("Ромб");
        JLabel lblSecondAddition=new JLabel("Квадрат");
        JLabel lblThirdAddition=new JLabel("Овал");
        lblFirstAddition.setBorder(border);
        lblSecondAddition.setBorder(border);
        lblThirdAddition.setBorder(border);
        lblElLocomotive.setBorder(border);
        lblLocomotive.setBorder(border);
        MainColor.setBorder(border);
        DopColor.setBorder(border);

        //colors
        JPanel panelRed = new JPanel();
        panelRed.setBackground(Color.RED);
        panelRed.setBorder(border);
        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBorder(border);
        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBorder(border);
        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBorder(border);
        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBorder(border);
        JPanel panelOrange = new JPanel();
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBorder(border);
        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBorder(border);
        JPanel panelGray = new JPanel();
        panelGray.setBackground(Color.GRAY);
        panelGray.setBorder(border);
        //horns
        JLabel lblHrnOne=new JLabel("один");
        JLabel lblHrnTwo=new JLabel("два");
        JLabel lblHrnThree=new JLabel("три");
        lblHrnOne.setBorder(border);
        lblHrnTwo.setBorder(border);
        lblHrnThree.setBorder(border);

        var colorListener = new DragMouseAdapter();

        MainColor.addMouseListener(colorListener);
        MainColor.setTransferHandler(new TransferHandler("background"));
        DopColor.addMouseListener(colorListener);
        DopColor.setTransferHandler(new TransferHandler("background"));
        panelRed.addMouseListener(colorListener);
        panelRed.setTransferHandler(new TransferHandler("background"));
        panelBlue.addMouseListener(colorListener);
        panelBlue.setTransferHandler(new TransferHandler("background"));
        panelGreen.addMouseListener(colorListener);
        panelGreen.setTransferHandler(new TransferHandler("background"));
        panelBlack.addMouseListener(colorListener);
        panelBlack.setTransferHandler(new TransferHandler("background"));
        panelWhite.addMouseListener(colorListener);
        panelWhite.setTransferHandler(new TransferHandler("background"));
        panelOrange.addMouseListener(colorListener);
        panelOrange.setTransferHandler(new TransferHandler("background"));
        panelYellow.addMouseListener(colorListener);
        panelYellow.setTransferHandler(new TransferHandler("background"));
        panelGray.addMouseListener(colorListener);
        panelGray.setTransferHandler(new TransferHandler("background"));


        var textListener = new DragMouseAdapter();

        lblLocomotive.addMouseListener(textListener);
        lblElLocomotive.addMouseListener(textListener);
        lblLocomotive.setTransferHandler(new TransferHandler("text"));
        lblElLocomotive.setTransferHandler(new TransferHandler("text"));
        lblShapeAdditions.addMouseListener(textListener);
        lblShapeAdditions.setTransferHandler(new TransferHandler("text"));
        lblAdditions.addMouseListener(textListener);
        lblAdditions.setTransferHandler(new TransferHandler("text"));
        lblFirstAddition.addMouseListener(textListener);
        lblFirstAddition.setTransferHandler(new TransferHandler("text"));
        lblSecondAddition.addMouseListener(textListener);
        lblSecondAddition.setTransferHandler(new TransferHandler("text"));
        lblThirdAddition.addMouseListener(textListener);
        lblThirdAddition.setTransferHandler(new TransferHandler("text"));
        lblHrnOne.addMouseListener(textListener);
        lblHrnOne.setTransferHandler(new TransferHandler("text"));
        lblHrnTwo.addMouseListener(textListener);
        lblHrnTwo.setTransferHandler(new TransferHandler("text"));
        lblHrnThree.addMouseListener(textListener);
        lblHrnThree.setTransferHandler(new TransferHandler("text"));

        //core type
        JPanel coreTypeGroupBox = new JPanel();
        Border coreBorder = BorderFactory.createTitledBorder("Тип кузова");
        coreTypeGroupBox.setBorder(coreBorder);

        coreTypeGroupBox.add(lblLocomotive);
        coreTypeGroupBox.add(lblElLocomotive);

        coreTypeGroupBox.setBounds(30, 30, 220, 140);
        coreTypeGroupBox.setLayout(null);
        //param type
        JPanel paramGroupBox = new JPanel();
        Border paramBorder = BorderFactory.createTitledBorder("Параметры");
        paramGroupBox.setBorder(paramBorder);

        paramGroupBox.add(spinWeight);
        paramGroupBox.add(spinSpeed);
        paramGroupBox.add(checkBoxBackLine);
        paramGroupBox.add(checkBoxUPipe);
        paramGroupBox.add(checkBoxFBumper);

        paramGroupBox.setBounds(30, 230, 420, 140);
        paramGroupBox.setLayout(null);

        //color type
        JPanel colorGroupBox = new JPanel();
        Border colorBorder = BorderFactory.createTitledBorder("Цвета");
        colorGroupBox.setBorder(colorBorder);

        colorGroupBox.add(MainColor);
        colorGroupBox.add(DopColor);
        colorGroupBox.add(panelRed);
        colorGroupBox.add(panelBlue);
        colorGroupBox.add(panelGreen);
        colorGroupBox.add(panelBlack);
        colorGroupBox.add(panelWhite);
        colorGroupBox.add(panelOrange);
        colorGroupBox.add(panelYellow);
        colorGroupBox.add(panelGray);

        colorGroupBox.setBounds(600, 30, 300, 230);
        colorGroupBox.setLayout(null);
        //horn type
        JPanel hornsGroupBox = new JPanel();
        Border hornsBorder = BorderFactory.createTitledBorder("Рога");
        hornsGroupBox.setBorder(hornsBorder);

        hornsGroupBox.add(lblAdditions);
        hornsGroupBox.add(lblShapeAdditions);
        hornsGroupBox.add(lblFirstAddition);
        hornsGroupBox.add(lblSecondAddition);
        hornsGroupBox.add(lblThirdAddition);
        hornsGroupBox.add(lblHrnOne);
        hornsGroupBox.add(lblHrnTwo);
        hornsGroupBox.add(lblHrnThree);

        hornsGroupBox.setBounds(500, 350, 400, 150);
        hornsGroupBox.setLayout(null);
        //frame
        frame.getContentPane().add(checkBoxBackLine);
        frame.getContentPane().add(checkBoxFBumper);
        frame.getContentPane().add(checkBoxUPipe);
        frame.getContentPane().add(lblAdditions);
        frame.getContentPane().add(lblShapeAdditions);
        frame.getContentPane().add(lblFirstAddition);
        frame.getContentPane().add(lblSecondAddition);
        frame.getContentPane().add(lblThirdAddition);
        frame.getContentPane().add(lblHrnOne);
        frame.getContentPane().add(lblHrnTwo);
        frame.getContentPane().add(lblHrnThree);
        frame.getContentPane().add(lblLocomotive);
        frame.getContentPane().add(lblElLocomotive);
        frame.getContentPane().add(spinWeight);
        frame.getContentPane().add(lblWeight);
        frame.getContentPane().add(spinSpeed);
        frame.getContentPane().add(lblSpeed);
        frame.getContentPane().add(lblPicture);
        frame.getContentPane().add(MainColor);
        frame.getContentPane().add(DopColor);
        frame.getContentPane().add(panelRed);
        frame.getContentPane().add(panelBlue);
        frame.getContentPane().add(panelGreen);
        frame.getContentPane().add(panelBlack);
        frame.getContentPane().add(panelWhite);
        frame.getContentPane().add(panelOrange);
        frame.getContentPane().add(panelYellow);
        frame.getContentPane().add(panelGray);
        frame.getContentPane().add(createLocomotive);
        frame.getContentPane().add(cancelLocomotive);
        frame.getContentPane().add(coreTypeGroupBox);
        frame.getContentPane().add(paramGroupBox);
        frame.getContentPane().add(colorGroupBox);
        frame.getContentPane().add(hornsGroupBox);

        lblElLocomotive.setBounds(50, 60, 150, 30);
        lblLocomotive.setBounds(50, 100, 150, 30);

        lblAdditions.setBounds(510, 380, 120, 30);

        lblShapeAdditions.setBounds(510, 450, 120, 30);
        lblFirstAddition.setBounds(680, 455, 60, 30);
        lblSecondAddition.setBounds(760, 455, 60, 30);
        lblThirdAddition.setBounds(840, 455, 60, 30);
        lblHrnOne.setBounds(680, 380, 40, 30);
        lblHrnTwo.setBounds(730, 380, 40, 30);
        lblHrnThree.setBounds(780, 380, 40, 30);
        createLocomotive.setBounds(620,310,120,30);
        cancelLocomotive.setBounds(760,310,120,30);
        spinWeight.setBounds(50, 260, 100, 30);
        lblWeight.setBounds(50, 235, 100, 30);
        spinSpeed.setBounds(50, 320, 100, 30);
        lblSpeed.setBounds(50, 295, 100, 30);
        lblPicture.setBounds(270, 40, 280, 180);
        lblPicture.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        MainColor.setBounds(620, 50, 100, 30);
        DopColor.setBounds(770, 50, 100, 30);
        checkBoxBackLine.setBounds(250, 260, 150, 20);
        checkBoxFBumper.setBounds(250, 280, 150, 20);
        checkBoxUPipe.setBounds(250, 300, 150, 20);

        //red-blue-green-black-white-orange-yellow-gray
        panelRed.setBounds(620, 90, 80, 30);
        panelBlue.setBounds(710, 90, 80, 30);
        panelGreen.setBounds(800, 90, 80, 30);
        panelBlack.setBounds(620, 140, 80, 30);
        panelWhite.setBounds(710, 140, 80, 30);
        panelOrange.setBounds(800, 140, 80, 30);
        panelYellow.setBounds(620, 190, 80, 30);
        panelGray.setBounds(710, 190, 80, 30);

        drawPanel = new DrawPanel();
        frame.getContentPane().add(drawPanel);
        drawPanel.setBounds(270, 40, 280, 180);
        drawPanel.setBorder(border);
        lblPicture.addMouseListener(textListener);
        lblPicture.setTransferHandler(new TransferHandler("text"));

        PropertyChangeListener nameListener = propertyChangeEvent -> {
            if (lblPicture.getText() == "Локомотив") {
                setLocomotive(MainColor.getBackground());
            } else if (lblPicture.getText() == "Электровоз") {
                setElLocomotive(MainColor.getBackground(), DopColor.getBackground(), checkBoxBackLine.isSelected(),
                        checkBoxFBumper.isSelected(), checkBoxUPipe.isSelected(),additionShape(lblShapeAdditions.getText()),
                        additionCount(lblAdditions.getText()));
            }
            lblPicture.setText("");
        };

        PropertyChangeListener mainColorListener = propertyChangeEvent -> {
            locomotive.setMainColor(MainColor.getBackground());
            drawPanel.setLocomotive(locomotive);
            frame.repaint();
        };

        PropertyChangeListener dopColorListener = propertyChangeEvent -> {
                ElLocomotive thisLocomotive = (ElLocomotive)locomotive;
                thisLocomotive.setOtherColor(DopColor.getBackground());
                locomotive=thisLocomotive;
                drawPanel.setLocomotive(locomotive);
                frame.repaint();
        };

        lblPicture.addPropertyChangeListener("text", nameListener);
        MainColor.addPropertyChangeListener("background", mainColorListener);
        DopColor.addPropertyChangeListener("background", dopColorListener);

        createLocomotive.addActionListener(actionEvent -> addLocomotive());
        cancelLocomotive.addActionListener(actionEvent -> frame.dispose());
        frame.repaint();
        frame.setVisible(true);

    }
    private void addLocomotive() {
        if (locomotive == null) {
            frameDepo.addLocomotive(null);
        } else if (locomotive instanceof ElLocomotive) {
            ElLocomotive newPlane = (ElLocomotive) locomotive;
            frameDepo.addLocomotive(newPlane);
        } else {
            Locomotive newPlane = (Locomotive) locomotive;
            frameDepo.addLocomotive(newPlane);
        }
        frame.dispose();
    }

    private void setLocomotive(Color main) {
        locomotive= new Locomotive(150, 1500, main);
        drawPanel.setLocomotive(locomotive);
        drawPanel.getLocomotive().setPosition(80, 50, 850, 450);
        frame.repaint();
    }

    private void setElLocomotive(Color main, Color dop, boolean bLine, boolean fBumper, boolean uPipe, int shape,int addition) {
        locomotive =new ElLocomotive(150, 1500, main, dop, bLine, fBumper, uPipe, shape, addition);
        drawPanel.setLocomotive(locomotive);
        drawPanel.getLocomotive().setPosition(80, 50, 850, 450);
        frame.repaint();
    }

    private int additionCount(String str1){
        switch(str1) {
            case "один":
                return 0;
            case "два":
                return 1;
            case "три":
                return 2;
        }
        return 4;
    }

    private int additionShape(String str1){
        switch(str1) {
            case "Ромб":
                return 0;
            case "Квадрат":
                return 1;
            case "Овал":
                return 2;
        }
        return 0;
    }


    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

}
