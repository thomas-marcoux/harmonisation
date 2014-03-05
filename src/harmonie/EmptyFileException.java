package harmonie;

public class EmptyFileException extends Exception{
    private static final long	serialVersionUID = 3;
    
    public	EmptyFileException(String file)	{
	super("Le fichier " + file + " est vide.");
    }
}
