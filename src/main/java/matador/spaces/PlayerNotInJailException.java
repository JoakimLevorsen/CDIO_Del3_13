package matador.spaces;

public class PlayerNotInJailException extends Exception {

    // This can fuck the entire program up if serialized, so DO NOT SERIALIZE THIS CLASS, THIS VARIABLE WAS ONLY DEFINED TO SCILENCE A WARNING
    static final long serialVersionUID = 0xBADBEEF;

    public PlayerNotInJailException(String message) {
        super(message);
    }
}