package matador.game;

public class SpaceNotFoundException extends Exception {

    // This can fuck the entire program up if serialized, so DO NOT SERIALIZE THIS CLASS, THIS VARIABLE WAS ONLY DEFINED TO SCILENCE A WARNING
    static final long serialVersionUID = 0xDEFEC8ED;

    public SpaceNotFoundException(String message) {
        super(message);
    }
}