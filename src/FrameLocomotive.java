import javax.swing.*;
import java.awt.*;

public class FrameLocomotive {
    private final JFrame frame;
    private final JComboBox<String> list;
    private DrawPanel drawPanel;

    public FrameLocomotive() {
        frame = new JFrame("Электровоз");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        Icon left = new ImageIcon("Rec\\Left.png");
        Icon right = new ImageIcon("Rec\\Right.png");
        Icon up = new ImageIcon("Rec\\Up.png");
        Icon down = new ImageIcon("Rec\\Down.png");

        JButton btnCreate = new JButton("Создать");
        JButton btnUp = new JButton(up);
        btnUp.setName("up");
        JButton btnDown = new JButton(down);
        btnDown.setName("down");
        JButton btnLeft = new JButton(left);
        btnLeft.setName("left");
        JButton btnRight = new JButton(right);
        btnRight.setName("right");

        frame.getContentPane().add(btnCreate);
        frame.getContentPane().add(btnUp);
        frame.getContentPane().add(btnDown);
        frame.getContentPane().add(btnLeft);
        frame.getContentPane().add(btnRight);

        btnCreate.setBounds(10, 10, 90, 30);
        btnUp.setBounds(805, 375, 30, 30);
        btnDown.setBounds(805, 410, 30, 30);
        btnLeft.setBounds(770, 410, 30, 30);
        btnRight.setBounds(840, 410, 30, 30);

        btnCreate.addActionListener(e -> setLocomotive());
        btnUp.addActionListener(e -> setDirection(btnUp));
        btnDown.addActionListener(e -> setDirection(btnDown));
        btnLeft.addActionListener(e -> setDirection(btnLeft));
        btnRight.addActionListener(e -> setDirection(btnRight));

        list = new JComboBox<>(new String[]{"1 рог", "2 рога", "3 рога"});
        frame.getContentPane().add(list);
        list.setBounds(10, 45, 90, 30);
    }

    public void addDrawPanel(DrawPanel panel) {
        drawPanel = panel;
        frame.getContentPane().add(drawPanel);
        drawPanel.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    private void setDirection(JButton button) {
        String name = button.getName();
        switch (name) {
            case "up":
                drawPanel.getLocomotive().moveLocomotive(Direction.Up);
                break;
            case "down":
                drawPanel.getLocomotive().moveLocomotive(Direction.Down);
                break;
            case "left":
                drawPanel.getLocomotive().moveLocomotive(Direction.Left);
                break;
            case "right":
                drawPanel.getLocomotive().moveLocomotive(Direction.Right);
                break;
        }
        frame.repaint();

    }

    private void setLocomotive() {
        drawPanel.setLocomotive(new Locomotive(150, 1500, new Color(34, 139, 34), Color.RED, true,  true, true, list.getSelectedIndex() + 1));
        drawPanel.getLocomotive().setPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 10), 850, 450);
        frame.repaint();
    }
}
