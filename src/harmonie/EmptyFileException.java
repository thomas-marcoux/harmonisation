package harmonie;

public class EmptyFileException extends Exception{
    private static final long	serialVersionUID = 2;
    
    public	EmptyFileException()	{
	super("Le fichier est vide.");
    }
}
