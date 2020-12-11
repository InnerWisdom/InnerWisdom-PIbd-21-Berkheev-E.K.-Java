package Frame;

public class DepoAlreadyHaveThisLocomotiveException extends Exception {
    public DepoAlreadyHaveThisLocomotiveException() {
        super("Depo already have such locomotive");
    }
}
