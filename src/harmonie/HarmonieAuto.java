package harmonie;

import java.io.IOException;

public class HarmonieAuto {
	public static final int[] tabSoprano = { 21, 21, 21, 22, 23, 23, 22, 22,
			21, 23, 22, 22, 21, 21, 21, 21 };
	public static int k = 3; // Si k == 5 alors affiche le nombre

	// d'harmonisations, c'est le k rentrer par
	// l'utilisateur

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] tabFinalHarmonie;

		/*
		 * HarmonieRoot root = new HarmonieRoot();
		 * 
		 * if (args.length > 1) root.start(args); else root.showHelp();
		 */

		try {
			Chant c = new Chant("lune.chant");
			int[] s = c.getSoprano();

			for (int i = 0; i < s.length; ++i) {
				System.out.print("soprano[" + i + "] = " + s[i]);
				if (s[i] == Chant.REPEAT)
					System.out.print(" (Chant.REPEAT)");
				else if (s[i] == Chant.PAUSE)
					System.out.print("(Chant.PAUSE)");
				System.out.println();
			}
		} catch (IOException | ChantFormatException | EmptyFileException e) {
			System.out.println(e);
		}

		// Creation de l'harmonisation
		tabFinalHarmonie = Initialisations.convertionListTab(Initialisations
				.initialisationSuivant(
						Initialisations.initialisationDuGraphe(), k));

		// Affichage de l'harmonisation
		for (int i = 0; i < tabFinalHarmonie.length; i++) {
			for (int j = 0; j < tabFinalHarmonie[i].length; j++) {
				System.out.print(tabFinalHarmonie[i][j] + " ");
			}
			System.out.println("");
		}
	}
}