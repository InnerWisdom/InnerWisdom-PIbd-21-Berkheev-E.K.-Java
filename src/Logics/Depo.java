package Logics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Frame.LocomotiveNotFoundException;
import Frame.DepoOverflowException;

public class Depo<T extends Locomotive, I extends IAdditions> {
    private final List<T> places;
    private final int maxCount;
    private final int placeWidth = 250;
    private final int placeHeight = 150;
    private final int frameWidth;
    private final int frameHeight;

    public Depo(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        int columnsCount = frameWidth / placeWidth;
        int rowsCount = frameHeight / placeHeight;
        maxCount = columnsCount * rowsCount;
        places = new ArrayList<>();
    }

    public boolean add(T locomotive) throws DepoOverflowException {
        if (places.size() < maxCount) {
            places.add(locomotive);
            return true;
        }
        throw new DepoOverflowException();
    }

    public T remove(int index) throws LocomotiveNotFoundException {
        if (index >= 0 && index < maxCount && places.get(index) != null) {
            T locomotive = places.get(index);
            places.remove(index);
            return locomotive;
        }
        throw new LocomotiveNotFoundException(index);
    }

    public void draw(Graphics2D g) {
        int margin = 80;
        int rowsCount = frameHeight / placeHeight;

        drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            places.get(i).setPosition(margin + placeWidth * (i / rowsCount),
                    margin + placeHeight * (i % rowsCount), frameWidth, frameHeight);
            places.get(i).draw(g);
        }
    }

    public void deleteLocomotives() {
        places.clear();
    }

    public void drawMarking(Graphics2D g) {
        int margin = 15;
        int rowsCount = frameHeight / placeHeight;
        int columnsCount = frameWidth / placeWidth;
        g.setStroke(new BasicStroke(3));
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

    public T get(int index) {
        if (index >= 0 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }
}