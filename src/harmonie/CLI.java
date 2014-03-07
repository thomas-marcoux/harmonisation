package harmonie;

public abstract class CLI {

    /**
     * Classe interface : elle parcours les arguments
     * et execute les options demandees par l'utilisateur
     */

    /**
     * Constantes private
     */
    private static final String	OPTION_NAME = "-name";
    private static final String	OPTION_HELP = "-h";
    private static final String	OPTION_LILY = "-ly";
    private static final String	OPTION_NOMBRE = "-nombre";
    private static final String	OPTION_BEAUTE = "-beaute";

    private static final char	OPTION_PREFIX = '-';

    private static final String	HELP = "help";

    /**
     * Methode parcourant et executant les options passees en parametres
     * @param	args	Les arguments de la methode main
     */
    public static void	parse(String[] args) 
	throws OptionsFormatException {
	Chant	c = null;

	for (int i = 0; i < args.length; ++i) {
	    switch (args[i]) {
	    case OPTION_NAME :
		showNames(); break;
	    case OPTION_HELP :
		showHelp(); break;
	    case Midi.OPTION :
		i = Midi.exec(c, args, i); break;
		/*
		  case Lily.OPTION :
		  i = Lily.exec(c, args, i); break;
		  case Harmonisations.OPTION :
		  Harmonisation.exec(args, i++); break;
		  case Beaute.OPTION :
		  Chant = Beaute.exec(args, i++); break;
		*/
	    case Dossiers.OPTION :
		i = Dossiers.exec(args, i); break;
	    default :
		throw new OptionsFormatException(args[i]);
	    }
	}
    }

    /**
     * Verifie si un argument est au format option
     * (precede du prefixe OPTION_PREFIX)
     * @param	arg	L'argument a controler
     * @return		true si l'arg est une option
     */    
    public static boolean	isOption(String arg) {
	return arg.charAt(0) == OPTION_PREFIX;
    }

    /**
     * Affiche les noms des auteurs
     */    
    public static void	showNames() {
	System.out.println("Auteurs :");
	for (String auteur : HarmonieAuto.AUTEURS)
	    System.out.println(auteur);
    }

    /**
     * Affiche l'aide
     */    
    public static void	showHelp() {
	System.out.println(HELP);
    }
}
