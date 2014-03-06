package harmonie;

import	java.io.IOException;

public abstract class CLI {

    private static final String	OPTION_NAME = "-name";
    private static final String	OPTION_HELP = "-h";
    private static final String	ARG_LILY = "-ly";
    private static final String	ARG_NOMBRE = "-nombre";
    private static final String	ARG_BEAUTE = "-beaute";

    private static final char	OPTION_PREFIX = '-';

    private static final String	NAMES = "noms";
    private static final String	HELP = "help";

    public static void	parse(String[] args) 
	throws IOException, OptionsFormatException, EmptyFileException,
	       ChantFormatException {
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

    public static boolean	isOption(String arg) {
	return arg.charAt(0) == OPTION_PREFIX;
    }

    public static void	showNames() {
	System.out.print(NAMES);
    }
    
    public static void	showHelp() {
	System.out.print(HELP);
    }
}
