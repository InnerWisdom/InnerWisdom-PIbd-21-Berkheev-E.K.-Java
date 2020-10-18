package Frame;

public class Main {

    public static void main(String[] args) {
        FrameLocomotive frameLocomotive = new FrameLocomotive();
        DrawPanel drawPanel = new DrawPanel();
        frameLocomotive.addDrawPanel(drawPanel);
    }
}
