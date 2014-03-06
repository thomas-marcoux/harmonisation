package harmonie;

import	java.io.IOException;
import	java.io.File;

public class Dossiers {
    /**
     * cette classe contiens tous les outils 
     * pour traiter les fichiers contenus
     * dans le dossier donne en parametre.
     */
    
    public static final String	OPTION = "-w";
    public static final int	NB_ARGUMENTS = 2;

    public static int	exec(String[] args, int i)
	throws IOException, EmptyFileException, ChantFormatException,
	       OptionsFormatException {
	Chant	c;
	File	dirIn;
	File	dirOut;

	if (args.length < i + NB_ARGUMENTS
	    || CLI.isOption(args[i+1]) || CLI.isOption(args[i+2]))
	    throw new OptionsFormatException(OPTION, NB_ARGUMENTS);
	dirIn = new File(args[i+1]);
	dirOut = new File(args[i+2]);
	if (!dirIn.exists() || !dirIn.isDirectory())
	    throw new IOException(dirIn.getName()
				  + " n'est pas un dossier");
	if (!dirOut.exists() || !dirOut.isDirectory())
	    dirOut.mkdir();
	writeFiles(dirIn, dirOut);
	return i + NB_ARGUMENTS;
    }

    private static void	writeFiles(File dirIn, File dirOut)
	throws IOException, EmptyFileException, ChantFormatException {
	String	html = new String();

	for (File child : dirIn.listFiles()) {
	    if (child.isFile()) {
		/*
		 * Changer le contenu du tableau de Chant c
		 * au lieu d'en creer
		 * un nouveau oa chaque tour de boucle
		 */
		Midi.exec(dirOut, new Chant(child));
		//Lily.exec(dirOut, c);
	    }
	}
    }
}