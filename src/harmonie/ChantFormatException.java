package harmonie;

public class ChantFormatException extends Exception{
    private static final long	serialVersionUID = 1;

    public	ChantFormatException(String note)	{
	super("La note '" + note + "' est mal formatee.");
    }
}
