package Frame;

public class DepoOverflowException extends Exception {
   public DepoOverflowException(){
        super("No more space");
    }
}
