package harmonie;

import Exceptions.*;

public class HarmonieAuto {

	private static final String AUTEUR1 = "William Malbos";
	private static final String AUTEUR2 = "Thomas Marcoux";
	private static final String AUTEUR3 = "Marine Maziarczyk";
	public static final String AUTEURS[] = { AUTEUR1, AUTEUR2, AUTEUR3 };

	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				CLI.parse(args);
			} catch (OptionsFormatException e) {
				System.out.println(e);
			}
		} else
			CLI.showHelp();
	}
}
