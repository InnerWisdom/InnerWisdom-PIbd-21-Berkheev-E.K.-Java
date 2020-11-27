package Frame;

import Logics.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class FrameDepo {

    private final LinkedList<Locomotive> locomotiveQueue;
    private final DepoCollection depoCollection;
    private final DefaultListModel<String> deposList;
    private final JList<String> listBoxDepos;
    private final JTextField fieldDepoName;
    private final DrawPanelDepo drawPanelDepo;
    private JFrame frame;
    private JTextField fieldTakeIndex;

    public FrameDepo() {

        locomotiveQueue = new LinkedList<>();

        frame = new JFrame("Депо");
        frame.setSize(1100, 850);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);


        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        JMenu saveMenu = new JMenu("Сохранить");
        fileMenu.add(saveMenu);

        JMenu loadMenu = new JMenu("Загрузить");
        fileMenu.add(loadMenu);

        JMenuItem saveAllDataItem = new JMenuItem("Сохранить целиком");
        saveMenu.add(saveAllDataItem);

        JMenuItem loadAllDataItem = new JMenuItem("Загрузить целиком");
        loadMenu.add(loadAllDataItem);

        JMenuItem saveChosenAirfieldItem = new JMenuItem("Сохранить один аэродром");
        saveMenu.add(saveChosenAirfieldItem);

        JMenuItem loadChosenAirfieldItem = new JMenuItem("Загрузить один аэродром");
        loadMenu.add(loadChosenAirfieldItem);

        depoCollection = new DepoCollection(900, 800);

        drawPanelDepo = new DrawPanelDepo(depoCollection);
        JButton buttonCreateLocomotive = new JButton("Создать локомотив");
        JButton buttonAppendLocomotive = new JButton("Вставить");

        JPanel takeGroupBox = new JPanel();
        Border centerBorder = BorderFactory.createTitledBorder("Забрать локомотив");
        takeGroupBox.setBorder(centerBorder);
        JLabel labelNumber = new JLabel("Место");
        fieldTakeIndex = new JFormattedTextField();
        JButton buttonMoveToLinkedList = new JButton("Поместить в очередь");
        JButton buttonGetFromLinkedList = new JButton("Получить из очереди");

        JPanel depoGroupBox = new JPanel();
        centerBorder = BorderFactory.createTitledBorder("Депо");
        depoGroupBox.setBorder(centerBorder);
        JLabel labelDepoName = new JLabel("Название депо");
        deposList = new DefaultListModel<>();
        listBoxDepos = new JList<>(deposList);
        fieldDepoName = new JTextField();
        JButton buttonAddDepo = new JButton("Добавить депо");
        JButton buttonRemoveDepo = new JButton("Удалить депо");

        frame.getContentPane().add(buttonCreateLocomotive);
        frame.getContentPane().add(buttonMoveToLinkedList);
        frame.getContentPane().add(drawPanelDepo);
        frame.getContentPane().add(buttonAppendLocomotive);
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

        drawPanelDepo.setBounds(0, 0, 1100, 850);
        buttonCreateLocomotive.setBounds(770, 20, 200, 30);
        buttonAppendLocomotive.setBounds(770, 60, 200, 30);

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

        saveChosenAirfieldItem.addActionListener(e -> {
            try {
                saveChosenDepo();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        loadChosenAirfieldItem.addActionListener(e -> {
            try {
                loadChosenDepo();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        saveAllDataItem.addActionListener(e -> {
            try {
                saveAll();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        loadAllDataItem.addActionListener(e -> {
            try {
                loadAll();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

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
        if (locomotive != null && listBoxDepos.getSelectedIndex() >= 0) {
            if (((depoCollection.get(listBoxDepos.getSelectedValue()).add(locomotive)))) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Локомотив не удалось поставить");
            }
        }
    }

    private void takeLocomotive() {
        if (listBoxDepos.getSelectedIndex() >= 0) {
            if (!fieldTakeIndex.getText().equals("")) {
                try {
                    Locomotive locomotive = depoCollection.get(listBoxDepos.getSelectedValue()).remove(Integer.parseInt(fieldTakeIndex.getText()));
                    if (locomotive != null) {
                        locomotiveQueue.add(locomotive);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Локомотива с таким индексом нет!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Локомотива с таким индексом нет!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moveToFrame() {
        if (!locomotiveQueue.isEmpty()) {
            FrameLocomotive frameLocomotive = new FrameLocomotive();
            frameLocomotive.setLocomotive(Objects.requireNonNull(locomotiveQueue.poll()));
            frame.repaint();
        }
    }

    private void saveAll() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Depo");
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            if (filename.contains(".txt")) {
                depoCollection.saveAllData(filename);
            } else {
                depoCollection.saveAllData(filename + ".txt");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
        }
    }

    private void saveChosenDepo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Depo");
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            if (filename.contains(".txt")) {
                depoCollection.saveChosenDepoData(filename, listBoxDepos.getSelectedValue());
            } else {
                depoCollection.saveChosenDepoData(filename + ".txt", listBoxDepos.getSelectedValue());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
        }
    }

    private void loadAll() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            depoCollection.loadAllData(filename);
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
        }
    }

    private void loadChosenDepo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            depoCollection.loadChosenDepoData(filename);
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
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
            depoCollection.addDepo(fieldDepoName.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название депо", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeDepo() {
        if (listBoxDepos.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить депо " + listBoxDepos.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                depoCollection.removeDepo(listBoxDepos.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Депо не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listListener() {
        drawPanelDepo.setSelectedItem(listBoxDepos.getSelectedValue());
        frame.repaint();
    }

}
