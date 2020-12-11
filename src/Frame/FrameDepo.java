package Frame;

import Logics.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class FrameDepo {

    private static Logger logger;
    private final LinkedList<Locomotive> locomotiveQueue;
    private final DepoCollection depoCollection;
    private final DefaultListModel<String> deposList;
    private final JList<String> listBoxDepos;
    private final JTextField fieldDepoName;
    private final DrawPanelDepo drawPanelDepo;
    private JFrame frame;
    private JTextField fieldTakeIndex;

    public FrameDepo() {

        BasicConfigurator.configure();
        logger = Logger.getLogger("Default");

        locomotiveQueue = new LinkedList<>();

        frame = new JFrame("Depo");
        frame.setSize(1100, 850);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);


        JMenuBar depoBar = new JMenuBar();
        frame.setJMenuBar(depoBar);

        JMenu depoMenu = new JMenu("File");
        depoBar.add(depoMenu);

        JMenu saveDepo = new JMenu("Save");
        depoMenu.add(saveDepo);

        JMenu loadLocomotive = new JMenu("Load");
        depoMenu.add(loadLocomotive);

        JMenuItem saveAllDepos = new JMenuItem("Save all");
        saveDepo.add(saveAllDepos);

        JMenuItem loadAllDepos = new JMenuItem("Load all");
        loadLocomotive.add(loadAllDepos);

        JMenuItem saveChosenDepo = new JMenuItem("Save one depo");
        saveDepo.add(saveChosenDepo);

        JMenuItem loadChosenDepo = new JMenuItem("Load one depo");
        loadLocomotive.add(loadChosenDepo);

        depoCollection = new DepoCollection(900, 800);

        drawPanelDepo = new DrawPanelDepo(depoCollection);
        JButton buttonCreateLocomotive = new JButton("CreateLocomotive");

        JPanel takeGroupBox = new JPanel();
        Border centerBorder = BorderFactory.createTitledBorder("");
        takeGroupBox.setBorder(centerBorder);
        JLabel labelNumber = new JLabel("Location");
        fieldTakeIndex = new JFormattedTextField();
        JButton buttonMoveToLinkedList = new JButton("Move into List");
        JButton buttonGetFromLinkedList = new JButton("Get from List");

        JPanel depoGroupBox = new JPanel();
        centerBorder = BorderFactory.createTitledBorder("Depo");
        depoGroupBox.setBorder(centerBorder);
        JLabel labelDepoName = new JLabel("Depo name");
        deposList = new DefaultListModel<>();
        listBoxDepos = new JList<>(deposList);
        fieldDepoName = new JTextField();
        JButton buttonAddDepo = new JButton("Add depo");
        JButton buttonRemoveDepo = new JButton("Delete depo");

        frame.getContentPane().add(buttonCreateLocomotive);
        frame.getContentPane().add(buttonMoveToLinkedList);
        frame.getContentPane().add(drawPanelDepo);
        takeGroupBox.add(fieldTakeIndex);
        takeGroupBox.add(buttonMoveToLinkedList);
        takeGroupBox.add(buttonGetFromLinkedList);
        takeGroupBox.add(labelNumber);
        frame.getContentPane().add(takeGroupBox);
        depoGroupBox.add(labelDepoName);
        depoGroupBox.add(listBoxDepos);
        depoGroupBox.add(fieldDepoName);
        depoGroupBox.add(buttonAddDepo);
        depoGroupBox.add(buttonRemoveDepo);
        frame.getContentPane().add(depoGroupBox);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileReader fileReader = new FileReader("C:\\alteralt7\\log.log");
                    Scanner scanner = new Scanner(fileReader);
                    StringBuilder mailText = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.contains("ERROR")) {
                            mailText.append(line + "\n");
                        }
                    }
                    if (!mailText.toString().isEmpty()) {
                        logger.error(mailText.toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });

        drawPanelDepo.setBounds(0, 0, 1100, 850);
        buttonCreateLocomotive.setBounds(770, 20, 200, 30);

        takeGroupBox.setBounds(770, 100, 200, 140);
        takeGroupBox.setLayout(null);
        labelNumber.setBounds(40, 20, 75, 30);
        fieldTakeIndex.setBounds(105, 20, 75, 30);
        buttonMoveToLinkedList.setBounds(20, 60, 160, 30);
        buttonGetFromLinkedList.setBounds(20, 100, 160, 30);

        depoGroupBox.setBounds(770, 245, 200, 300);
        depoGroupBox.setLayout(null);
        labelDepoName.setBounds(30, 20, 75, 30);
        fieldDepoName.setBounds(105, 20, 75, 30);
        buttonAddDepo.setBounds(20, 60, 160, 30);
        buttonRemoveDepo.setBounds(20, 100, 160, 30);
        listBoxDepos.setBounds(20, 140, 160, 140);

        saveChosenDepo.addActionListener(e -> saveChosenDepo());
        loadChosenDepo.addActionListener(e -> loadChosenDepo());
        saveAllDepos.addActionListener(e -> saveAll());
        loadAllDepos.addActionListener(e ->loadAll());

        buttonCreateLocomotive.addActionListener(e -> createLocomotive());
        buttonMoveToLinkedList.addActionListener(e -> takeLocomotive());
        buttonGetFromLinkedList.addActionListener(e -> moveToFrame());
        buttonAddDepo.addActionListener(e -> addDepo());
        buttonRemoveDepo.addActionListener(e -> removeDepo());
        listBoxDepos.addListSelectionListener(e -> listListener());

        frame.repaint();
    }

    private void createLocomotive() {
        FormLocomotiveConfig formLocomotiveConfig = new FormLocomotiveConfig(this);
    }

    public void addLocomotive(Locomotive locomotive) {
        if (locomotive == null || listBoxDepos.getSelectedIndex() < 0) {
            logger.warn("No space for locomotive");
            JOptionPane.showMessageDialog(frame, "There is no locomotive or it cannot be added");
        } else {
            try {
                if (depoCollection.get(listBoxDepos.getSelectedValue()).add(locomotive)) {
                    logger.info("Added locomotive " + locomotive.toString());
                    frame.repaint();
                } else {
                    logger.info("Can't place locomotive");
                    JOptionPane.showMessageDialog(frame, "Can't place locomotive");
                }
            } catch (DepoOverflowException ex) {
                logger.error("Overflow");
                JOptionPane.showMessageDialog(frame, "Overflow");
            } catch (Exception ex) {
                logger.fatal("Unknown error");
                JOptionPane.showMessageDialog(frame, "Unknown error");
            }
        }
    }

    private void takeLocomotive() {
        if (listBoxDepos.getSelectedIndex() >= 0) {
            if (!fieldTakeIndex.getText().equals("")) {
                try {
                    Locomotive locomotive = depoCollection.get(listBoxDepos.getSelectedValue()).remove(Integer.parseInt(fieldTakeIndex.getText()));
                    logger.info("Added locomotive " + locomotive.toString());
                    locomotiveQueue.add(locomotive);
                    frame.repaint();
                }catch (LocomotiveNotFoundException ex) {
                    logger.error("Locomotive not found");
                    JOptionPane.showMessageDialog(frame, "Locomotive not found");
                } catch (Exception e) {
                    logger.fatal("Unknown error");
                    JOptionPane.showMessageDialog(frame, "No such locomotive!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                logger.warn("Index not inserted");
                JOptionPane.showMessageDialog(frame, "No such locomotive!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            logger.warn("Depo not chosen");
            JOptionPane.showMessageDialog(frame, "Depo is not specified", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moveToFrame() {
        if (!locomotiveQueue.isEmpty()) {
            logger.info("Locomotive taken from List " + locomotiveQueue.peek().toString());
            FrameLocomotive frameLocomotive = new FrameLocomotive();
            frameLocomotive.setLocomotive(Objects.requireNonNull(locomotiveQueue.poll()));
            frame.repaint();
        } else {
            logger.warn("List is empty");
            JOptionPane.showMessageDialog(frame, "List empty");
        }
    }

    private void saveAll(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Depo");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().toString();
                logger.info("Сохранено в файл " + filename);
                if (filename.contains(".txt")) {
                    depoCollection.saveAllData(filename);
                } else {
                    depoCollection.saveAllData(filename + ".txt");
                }
            } catch (Exception e) {
                logger.fatal("Unknown error");
                JOptionPane.showMessageDialog(frame, "Unknown error");
            }
        } else {
            logger.warn("Can't save file");
            JOptionPane.showMessageDialog(frame, "Can't save file");
        }
    }

    private void saveChosenDepo(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Depo");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().toString();
                logger.info("Сохранено в файл " + filename);
                if (filename.contains(".txt")) {
                    depoCollection.saveChosenDepoData(filename, listBoxDepos.getSelectedValue());
                } else {
                    depoCollection.saveChosenDepoData(filename + ".txt", listBoxDepos.getSelectedValue());
                }
            } catch (Exception e) {
                logger.fatal("Unknown error");
                JOptionPane.showMessageDialog(frame, "Unknown error");
            }
        }else {
                logger.warn("Can't save file");
                JOptionPane.showMessageDialog(frame, "Can't save file");
            }
    }

    private void loadAll() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
            String filename = fileChooser.getSelectedFile().toString();
            logger.info("Loaded from file " + filename);
            depoCollection.loadAllData(filename);
            reloadLevels();
            frame.repaint();
            } catch (Exception e) {
                logger.fatal("Unknown error");
                JOptionPane.showMessageDialog(frame, "Unknown error");
            }
        } else {

            logger.warn("Can't load file");
            JOptionPane.showMessageDialog(frame, "Can't load file");
        }
    }

    private void loadChosenDepo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
            String filename = fileChooser.getSelectedFile().toString();
            logger.info("Loaded from file " + filename);
            depoCollection.loadChosenDepoData(filename);
            reloadLevels();
            frame.repaint();
        } catch (Exception e) {
            logger.fatal("Unknown error");
            JOptionPane.showMessageDialog(frame, "Unknown error");
        }
        } else {
            logger.warn("Can't load file");
            JOptionPane.showMessageDialog(frame, "Can't load file");
        }
    }

    private void reloadLevels() {
        int index = listBoxDepos.getSelectedIndex();

        deposList.removeAllElements();
        int i = 0;
        for (String name : depoCollection.keySet()) {
            deposList.add(i, name);
            i++;
        }

        int itemsCount = deposList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxDepos.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxDepos.setSelectedIndex(index);
        }
    }

    private void addDepo() {
        if (!fieldDepoName.getText().equals("")) {
            logger.info("Added depo " + fieldDepoName.getText());
            depoCollection.addDepo(fieldDepoName.getText());
            reloadLevels();
            frame.repaint();
        } else {
            logger.info("Specify depo name");
            JOptionPane.showMessageDialog(frame, "Specify Depo name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeDepo() {
        if (listBoxDepos.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Delete Depo " + listBoxDepos.getSelectedValue() + "?", "Deletation",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                logger.info("Depo deleted");
                depoCollection.removeDepo(listBoxDepos.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            logger.info("Depo not chosen");
            JOptionPane.showMessageDialog(frame, "Depo is not specified", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listListener() {
        drawPanelDepo.setSelectedItem(listBoxDepos.getSelectedValue());
        frame.repaint();
    }

}
