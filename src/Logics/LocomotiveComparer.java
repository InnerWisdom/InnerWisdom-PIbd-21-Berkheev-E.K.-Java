package Logics;

import java.util.Comparator;

public class LocomotiveComparer implements Comparator<ITransport> {

    public int compare(ITransport x, ITransport y) {
        if (x instanceof ElLocomotive && y instanceof ElLocomotive) {
            return comparerElLocomotive((ElLocomotive) x, (ElLocomotive) y);
        }
        if (x instanceof ElLocomotive && y instanceof Locomotive) {
            return 1;
        }
        if (x instanceof Locomotive && y instanceof ElLocomotive) {
            return -1;
        }
        if (x instanceof Locomotive && y instanceof Locomotive) {
            return comparerLocomotive((Locomotive) x, (Locomotive) y);
        }
        return 0;
    }

    private int comparerLocomotive(Locomotive x, Locomotive y) {
        if (x.maxSpeed != y.maxSpeed) {
            return x.maxSpeed - y.maxSpeed;
        }
        if (x.weight != y.weight) {
            return x.weight - y.weight;
        }
        if (x.mainColor != y.mainColor) {
            return x.mainColor.getRGB() - y.mainColor.getRGB();
        }
        return 0;
    }

    private int comparerElLocomotive(ElLocomotive x, ElLocomotive y) {
        var res = comparerLocomotive(x, y);
        if (res != 0) {
            return res;
        }
        if (x.getOtherColor() != y.getOtherColor()) {
            return x.getOtherColor().getRGB() - y.getOtherColor().getRGB();
        }
        if (x.getUpperPipe() != y.getUpperPipe()) {
            if (x.getUpperPipe()) {
                return 1;
            }
            return -1;
        }
        if (x.getBackLine() != y.getBackLine()) {
            if (x.getBackLine()) {
                return 1;
            }
            return -1;
        }
        if (x.getFrontBumper() != y.getFrontBumper()) {
            if (x.getFrontBumper()) {
                return 1;
            }
            return -1;
        }
        if (x.getAddition() != y.getAddition()) {
            int firstElLocomotiveHornsID = -1;
            int secondElLocomotiveHornsID = -1;
            switch (x.getAddition()) {
                case "Rhombus":
                    firstElLocomotiveHornsID = 0;
                    break;
                case "Oval":
                    firstElLocomotiveHornsID = 1;
                    break;
                case "Rectangle":
                    firstElLocomotiveHornsID = 2;
                    break;
            }
            switch (y.getAddition()) {
                case "Rhombus":
                    secondElLocomotiveHornsID = 0;
                    break;
                case "Oval":
                    secondElLocomotiveHornsID = 1;
                    break;
                case "Rectangle":
                    secondElLocomotiveHornsID = 2;
                    break;
            }
            return firstElLocomotiveHornsID - secondElLocomotiveHornsID;
        }
        if (x.getDigit() != y.getDigit()) {
            return x.getDigit() - y.getDigit();
        }
        return 0;
    }

}
