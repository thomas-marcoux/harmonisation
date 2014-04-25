package harmonie;

import Exceptions.*;

public class HarmonieAuto {
    
    private static final String	AUTEUR1 = "William Malbos";
    private static final String	AUTEUR2 = "Thomas Marcoux";
    private static final String	AUTEUR3 = "Marine Maziarczyk";
    public static final String	AUTEURS[] = {AUTEUR1, AUTEUR2, AUTEUR3};
    
    //Si k == 5 alors affiche le nombre d'harmonisations

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    if (args.length > 1) {
		try {
		    CLI.parse(args);
		}
		catch (OptionsFormatException e) {
		    System.out.println(e);
		}
	    }
	    else
		CLI.showHelp();
	}
}