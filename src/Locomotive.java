import java.awt.*;

public class Locomotive {

    private final int locomotiveWidth = 200;
    private final int locomotiveHeight = 100;

    private int _startPosX;
    private int _startPosY;

    private Color mainColor;
    private Color dopColor;

    private int frameWidth;
    private int frameHeight;
    private int maxSpeed;
    private float weight;

    private boolean backLine;
    private boolean frontBumper;
    private boolean upperPipe;

    private final Horns horns;

    public Locomotive(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean backLine, boolean frontBumper, boolean upperPipe, int digit) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.backLine = backLine;
        this.frontBumper = frontBumper;
        this.upperPipe = upperPipe;

        this.horns = new Horns(digit);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    private void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getMainColor() {
        return mainColor;
    }

    private void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public Color getOtherColor() {
        return dopColor;
    }

    private void setOtherColor(Color otherColor) {
        this.dopColor = otherColor;
    }

    public boolean isBackLine() {
        return backLine;
    }

    private void setBackLine(boolean backLine) {
        this.backLine = backLine;
    }

    public boolean isFrontBumper() {
        return frontBumper;
    }

    private void setFrontBumper(boolean frontBumper) {
        this.frontBumper = frontBumper;
    }

    public boolean isUpperPipe() {
        return upperPipe;
    }

    private void setUpperPipe(boolean upperPipe) {
        this.upperPipe = upperPipe;
    }

    public void setPosition(int posX, int posY, int frameWidth, int frameHeight) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        if (posX >= 0 && posX + locomotiveWidth < frameWidth &&
                posY >= 0 && posY + locomotiveHeight < frameHeight) {
            this._startPosX = posX;
            this._startPosY = posY;
        }
    }

    public void moveLocomotive(Direction direction) {
        int step = (int) (maxSpeed * 100 / weight);
        switch (direction) {
            case Up:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                }
                break;
            case Right:
                if (_startPosX + step < frameWidth - locomotiveWidth) {
                    _startPosX += step;
                }
                break;
            case Down:
                if (_startPosY + step < frameHeight - locomotiveHeight) {
                    _startPosY += step;
                }
                break;
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
        }
    }

    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        if (backLine) {
            for (int i = 0; i < 30; i += 3) {
                g.fillRect(_startPosX + 120 - i, _startPosY + i, 20, 20);
            }
        }

        if (frontBumper) {
            g.fillOval(_startPosX - 12, _startPosY + 18, 16, 20);
            g.drawLine(_startPosX - 8, _startPosY + 24, _startPosX + 6, _startPosY - 4);
        }

        g.setColor(mainColor);
        g.fillRect(_startPosX + 80, _startPosY, 40, 40);
        g.fillRect(_startPosX, _startPosY, 40, 40);
        g.fillOval(_startPosX + 20, _startPosY, 80, 40);

        g.setColor(Color.BLACK);
        g.drawOval(_startPosX + 20, _startPosY, 80, 40);
        g.drawRect(_startPosX, _startPosY, 40, 40);
        g.drawRect(_startPosX + 80, _startPosY, 40, 40);

        //wheels
        g.fillOval(_startPosX + 3, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 13, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 23, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 33, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 83, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 93, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 103, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX + 113, _startPosY + 40, 10, 10);
        g.fillOval(_startPosX - 3, _startPosY + 38, 135, 10);

        if (upperPipe) {
            g.fillOval(_startPosX + 20, _startPosY - 4, 100, 6);
        }

        horns.draw(g, getOtherColor(), _startPosX, _startPosY);
    }
}
