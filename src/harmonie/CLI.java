package harmonie;

public abstract class CLI {

    private static final String	ARG_NAME = "-name";
    private static final String	ARG_HELP = "-h";
    private static final String	ARG_MIDI = "-midi";
    private static final String	ARG_LILY = "-ly";
    private static final String	ARG_NOMBRE = "-nombre";
    private static final String	ARG_BEAUTE = "-beaute";
    private static final String	ARG_DOSSIER = "-w";

    private static final String	NAMES = "";
    private static final String	HELP = "";

    public static void	parse(String[] args) {
	Chant	c = null;

	for (int i = 0; i < args.length; ++i) {
	    if (args[i].equals(ARG_NAME))
		showNames();
	    if (args[i].equals(ARG_HELP))
		showHelp();
	    if (args[i].equals(ARG_MIDI));
	    //Midi.exec(Chant, &args[i += 2]);
	    if (args[i].equals(ARG_LILY));
	    //Lily.exec(Chant, &args[i += 2]);
	    if (args[i].equals(ARG_NOMBRE));
	    //Harmonisation(args[i++]);
	    if (args[i].equals(ARG_BEAUTE));
	    //Chant = Beaute(args[i++]);
	    if (args[i].equals(ARG_DOSSIER));
	    //Dossier(args);
	}
    }

    private static void	showNames() {
	System.out.print(NAMES);
    }
    private static void	showHelp() {
	System.out.print(HELP);
    }
   
}
