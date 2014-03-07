package harmonie;

public class HarmonieAuto {

    private static final String	AUTEUR1 = "William Malbos";
    private static final String	AUTEUR2 = "Thomas Marcoux";
    private static final String	AUTEUR3 = "Marine Maziarczyk";
    
    public static final String	AUTEURS[] = {AUTEUR1, AUTEUR2, AUTEUR3};

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	if (args.length == 0) {
	    CLI.showHelp();
	    return;
	}
	try {
	    CLI.parse(args);
	}
	catch (OptionsFormatException e) {
	    System.out.println(e);
	    CLI.showHelp();
	}

    }
}
