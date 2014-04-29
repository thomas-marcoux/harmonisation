package harmonie;


public class HarmonieAuto {
    
    private static final String	AUTEUR1 = "William Malbos";
    private static final String	AUTEUR2 = "Thomas Marcoux";
    private static final String	AUTEUR3 = "Marine Maziarczyk";
    public static final String	AUTEURS[] = {AUTEUR1, AUTEUR2, AUTEUR3};
    
    //Exemple de tableau soprano avec mes constantes
   private static final int[] soprano = {21, 21, 21, 22, 23, Chant.REPEAT, 22, Chant.REPEAT, 21, 23, 22, 22, 21, Chant.REPEAT, Chant.REPEAT, Chant.PAUSE};
    
    //Si nbHarmone = -1 alors c'est qu'il dépasse la taille d'un int
	/**
	 * @param args
	 */
	public static void main(String[] args) {


	    //*
	    int[][] tabFinalHarmonie;
	    int	k = 3;

	    tabFinalHarmonie = Initialisations.convertionListTab
		(Initialisations.initialisationSuivant
		 (Initialisations.initialisationDuGraphe(Initialisations.convertionTab(soprano)), k),soprano,k);

	    
	    // Affichage de l'harmonisation
	    for (int i = 0; i < tabFinalHarmonie.length; i++) {
		for (int j = 0; j < tabFinalHarmonie[i].length; j++) {
		    System.out.print(tabFinalHarmonie[i][j] + " ");
		}
		System.out.println("");
	    }
	    //*/


	    /*
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
	    /*/
	}
}
