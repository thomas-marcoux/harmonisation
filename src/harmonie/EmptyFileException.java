package harmonie;

public class EmptyFileException extends Exception{
    private static final long	serialVersionUID = 3;
    
    public	EmptyFileException()	{
	super("Le fichier est vide.");
    }
}
