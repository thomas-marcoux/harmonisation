package harmonie;

import	Exceptions.*;
import	java.util.ArrayList;

public class CLI {

    /**
     * Classe interface : elle parcours les arguments
     * et execute les options demandees par l'utilisateur
     */

    /**
     * Constantes private
     */
    private static final String	OPTION_NAME = "-name";
    private static final String	OPTION_HELP = "-h";

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
	parse(argstoStringTab(args));
    }

    public static void	parse(String[][] args) 
	throws OptionsFormatException {
	int	k = Chant.DEFAULT_BEAUTE;

	for (String[] row : args)
	    switch (row[0]) {
	    case OPTION_NAME :
		showNames(); break;
	    case OPTION_HELP :
		showHelp(); break;
	    case Midi.OPTION :
		Midi.exec(row, k); break;
	    case Lily.OPTION :
		Lily.exec(row, k); break;
	    case OPTION_NOMBRE :
		;//Affiche le nombre d'harmonisations;break;
	    case OPTION_BEAUTE :
		k = getBeaute(row); break;
	    case Dossiers.OPTION :
		Dossiers.exec(row); break;
	    default :
		throw new OptionsFormatException(row[0]);
	    }
    }

    private static int	getBeaute(String[] args) {
	if (args.length < 2)
	    return Chant.DEFAULT_BEAUTE;
	try {
	    return (Integer.parseInt(args[1]));
	}
	catch (NumberFormatException e) {
	}
	return Chant.DEFAULT_BEAUTE;
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

    /**
     * Formate un tableau de String en double tableau de String
     */
    private static String[][]	argstoStringTab(String[] args)
	throws OptionsFormatException {
	ArrayList<String[]>	r = new ArrayList<String[]>();
	ArrayList<String>	array = new ArrayList<String>();

	for (int i = 0; i < args.length;) {
	    if (!isOption(args[i]))
		throw new OptionsFormatException(args[i]);
	    r.add(extractOption(array, args, i++));
	    for (; i < args.length && !isOption(args[i]); ++i);
	}
	return r.toArray(new String[r.size()][]);
    }

    private static String[]	extractOption(ArrayList<String> array,
				      String[] args, int i) {
	array.clear();
	array.add(args[i++]);
	for (; i < args.length && !isOption(args[i]); ++i)
	    array.add(args[i]);
	return array.toArray(new String[array.size()]);
    }
}
