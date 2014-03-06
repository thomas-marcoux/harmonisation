package harmonie;

import	java.io.IOException;

public class HarmonieAuto {

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
	catch (IOException | OptionsFormatException
	       | EmptyFileException | ChantFormatException e) {
	    System.out.println(e);
	    CLI.showHelp();
	}

    }
}
