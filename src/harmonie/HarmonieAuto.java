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


	    /*
	    int[] tabSoprano = { 21, 21, 21, 22, 23, 23, 22, 22, 21, 23, 22, 22, 21, 21, 21, 21 };
	    int[][] tabFinalHarmonie;
	    int	k = 3;

	    tabFinalHarmonie = Initialisations.convertionListTab
		(Initialisations.initialisationSuivant
		 (Initialisations.initialisationDuGraphe(tabSoprano), k));

	    // Affichage de l'harmonisation
	    for (int i = 0; i < tabFinalHarmonie.length; i++) {
		for (int j = 0; j < tabFinalHarmonie[i].length; j++) {
		    System.out.print(tabFinalHarmonie[i][j] + " ");
		}
		System.out.println("");
	    }
	    */


	    //*
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
	    //*/
	}
}