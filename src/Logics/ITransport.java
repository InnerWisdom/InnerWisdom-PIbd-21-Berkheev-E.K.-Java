package Logics;

import EnumCollections.Direction;

import java.awt.*;

public interface ITransport {

    void setPosition(int posX, int posY, int frameWidth, int frameHeight);

    void moveTransport(Direction direction);

    void draw(Graphics g);
}
