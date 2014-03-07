package harmonie;

public class ChantFormatException extends Exception{

    /**
     * Gestion des fichier chants mal formates
     */

    private static final long	serialVersionUID = 1;

    /**
     * Constructeur
     * @param	note	La note dont le format est incorrect
     */
    public	ChantFormatException(String note)	{
	super("La note '" + note + "' est mal formatee.");
    }
}
