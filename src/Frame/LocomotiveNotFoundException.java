package Frame;

public class LocomotiveNotFoundException extends Exception {
    public LocomotiveNotFoundException(int i) {super("Locomotive not found " + i);}
}
