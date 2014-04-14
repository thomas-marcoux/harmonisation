package harmonie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HarmonieAuto {
	public static int k = 5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<LinkedList<Integer>> meilleurHarmonisation;
		ArrayList<LinkedList<listeJeux>> listeDesJeux = new ArrayList<LinkedList<listeJeux>>();

		// TODO Auto-generated method stub
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

		
		
		
		/*listeDesJeux = GrapheAccord.initialisationDuGrapheEtChoixDuK(k);*/
		
		
		// Si k = 5 on affiche le nombre d'harmonisations sinon on récupère la
		// LinkedList<LinkedList<Integer>>
		if (k == 5) {
			GrapheAccord.initialisationDuGrapheEtChoixDuK(k);
			
		} else {
			meilleurHarmonisation = new LinkedList<LinkedList<Integer>>();
			/* meilleurHarmonisation = */GrapheAccord
					.initialisationDuGrapheEtChoixDuK(k);
		}
	}
}