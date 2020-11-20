package Logics;

import java.awt.*;

public class Depo<T extends ITransport, A extends IAdditions> {
    private final Object[] places;
    private final int placeWidth = 250;
    private final int placeHeight = 150;
    private final int frameWidth;
    private final int frameHeight;

    public Depo(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        int columnsCount = frameWidth / placeWidth;
        int rowsCount = frameHeight / placeHeight;
        places = new Object[columnsCount * rowsCount];
    }

    public boolean append(T transport) {
        int margin = 85;
        int rowsCount = frameHeight / placeHeight;
        for (int i = 0; i < places.length; i++) {
            if (places[i] == null) {
                transport.setPosition(margin-45 + placeWidth * (i / rowsCount),
                        margin + placeHeight * (i % rowsCount), frameWidth, frameHeight);
                places[i] = transport;
                return true;
            }
        }
        return false;
    }

    public T remove(int index) {
        if (index >= 0 && index < places.length && places[index] != null) {
            Object temp = places[index];
            places[index] = null;
            return (T) (temp);
        } else {
            return null;
        }
    }

    public boolean Equals(int count) {
        int placesCount = 0;
        for (Object occupied : places) {
            if (occupied != null) {
                placesCount++;
            }
        }return placesCount == count;
    }

    public boolean notEquals(int count) {
        return !Equals(count);
    }

    public void draw(Graphics2D g) {
        drawMarking(g);
        for (Object place : places) {
            if (place != null) {
                T placeT = (T) place;
                placeT.draw(g);
            }
        }
    }

    public void drawMarking(Graphics2D g) {
        int margin = 15;
        int rowsCount = frameHeight / placeHeight;
        int columnsCount = frameWidth / placeWidth;
        g.setStroke(new BasicStroke(1));
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                g.drawLine(margin + j * placeWidth, margin + i * placeHeight, margin + (j + 1) * placeWidth, margin + i * placeHeight);
                if (j > 0) {
                    g.drawLine(margin + j * placeWidth, margin + i * placeHeight, margin + j * placeWidth, margin + (i + 1) * placeHeight);
                }
            }
        }
        for (int j = 0; j < columnsCount; j++) {
            g.drawLine(margin + j * placeWidth, margin + rowsCount * placeHeight, margin + (j + 1) * placeWidth, margin + rowsCount * placeHeight);
        }
    }
}
