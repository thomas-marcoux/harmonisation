package harmonie;

import	java.io.IOException;
import	java.io.File;

public class Dossiers {
    /**
     * cette classe contiens tous les outils 
     * pour traiter les fichiers contenus
     * dans le dossier donne en parametre.
     */
    
    /**
     * Constantes public : le nom de l'option et le nombre d'argument
     * qu'elle prend en parametre
     */
    public static final String	OPTION = "-w";
    public static final int	NB_ARGUMENTS = 2;

    /**
     * La methode appelee par la classe interface CLI
     * @param	args	Les arguments entrees en ligne de commande
     * @param	i	L'iterateur indiquant la position dans ce
     *			tableau d'arguments
     * @return		La valeur a ajoute a l'iterateur pour
     *			sauter les arguments de l'option
     */
    public static int	exec(String[] args, int i)
	throws OptionsFormatException {
	Chant	c;
	File	dirIn;
	File	dirOut;

	if (args.length < i + NB_ARGUMENTS
	    || CLI.isOption(args[i+1]) || CLI.isOption(args[i+2]))
	    throw new OptionsFormatException(OPTION, NB_ARGUMENTS);
	dirIn = new File(args[i+1]);
	dirOut = new File(args[i+2]);
	try {
	    controlDir(dirIn, dirOut);
	    writeFiles(dirIn, dirOut);
	}
	catch (IOException e) {
	    System.out.println(e);
	}
	return i + NB_ARGUMENTS;
    }

    /**
     * Methode controlant la validite des dossiers et
     * parcourant les fichiers contenus dans le dossier dirIn
     * pour ecrire les resultats demandes dans dirOut
     */
    private static void	writeFiles(File dirIn, File dirOut) {
	String	html = new String();

	for (File child : dirIn.listFiles()) {
	    if (child.isFile()) {
		/*
		 * Changer le contenu du tableau de Chant c
		 * au lieu d'en creer
		 * un nouveau a chaque tour de boucle
		 * et catch EmptyFileException et ChantFormatException
		 */
		try {
		    Midi.exec(dirOut, new Chant(child));
		    //Lily.exec(dirOut, c);
		}
		catch (EmptyFileException | ChantFormatException
		       | IOException e) {
		    System.out.println(e);
		}
	    }
	}
    }

    /**
     * Controle de la validite des dossiers
     */
    private static void	controlDir(File dirIn, File dirOut) 
	throws IOException {
	if (!dirIn.exists())
	    throw new IOException(dirIn.getName()
				  + " n'existe pas");
	if (!dirIn.isDirectory())
	    throw new IOException(dirIn.getName()
				  + " n'est pas un dossier");
	if (!dirOut.exists())
	    dirOut.mkdir();
	if (!dirOut.isDirectory())
	    throw new IOException(dirOut.getName()
				  + " est un fichier");
    }
}