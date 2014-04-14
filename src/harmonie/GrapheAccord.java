package harmonie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GrapheAccord {

	public static Note DO = new Note("DO", 0, 7, 14, 21);
	public static Note RE = new Note("RE", 1, 8, 15, 22);
	public static Note MI = new Note("MI", 2, 9, 16, 23);
	public static Note FA = new Note("FA", 3, 10, 17, 24);
	public static Note SOL = new Note("SOL", 4, 11, 18, 25);
	public static Note LA = new Note("LA", 5, 12, 19, 26);
	public static Note SI = new Note("SI", 6, 13, 20, 27);

	public static LinkedList<Note> listeGeneralNotes = new LinkedList<Note>(
			Arrays.asList(DO, RE, MI, FA, SOL, LA, SI, DO));

	public static Accord I = new Accord("DO", DO, MI, SOL);
	public static Accord II = new Accord("RE", RE, FA, LA);
	public static Accord III = new Accord("MI", MI, SOL, SI);
	public static Accord IV = new Accord("FA", FA, LA, DO);
	public static Accord IVb = new Accord("FAb", FA, LA, DO);
	public static Accord V = new Accord("SOL", SOL, SI, RE);
	public static Accord VI = new Accord("LA", LA, DO, MI);
	public static Accord VII = new Accord("SI", SI, RE, FA);

	public static LinkedList<Accord> listeGeneralAccords = new LinkedList<Accord>(
			Arrays.asList(I, II, III, IV, IVb, V, VI, VII));

	public static final int[] tabSoprano = { 21, 21, 21, 22, 23, 23, 22, 22,
			21, 23, 22, 22, 21, 21, 21, 21 };

	public static ArrayList<LinkedList<listeJeux>> listeGeneraleJeux = new ArrayList<LinkedList<listeJeux>>();

	public static void initialisationDuGrapheEtChoixDuK(int k) {
		LinkedList<listeJeux> liste = new LinkedList<listeJeux>();
		for (int i = 0; i < tabSoprano.length; i++) {
			Note note = trouverNote(tabSoprano[i]);
			LinkedList<Accord> accord = trouverAccord(note, i);

			listeJeuxNote(accord, note, tabSoprano[i], i, liste);

			LinkedList<listeJeux> listeCopie = copieListeJeux(liste);
			listeGeneraleJeux.add(listeCopie);
			liste.clear();

		}

		// return listeGeneraleJeux;
		ParcoursHarmonie.initialisationSuivant(listeGeneraleJeux,
				listeGeneralAccords, listeGeneralNotes, k);

	}

	/**
	 * Prend un int et retourne la note correspondante.
	 * 
	 * @param noteSoprano
	 *            L'int dans le chant soprano

	 * @return Note correspondant à l'int noteSoprano
	 * 
	 * */
	public static Note trouverNote(int noteSoprano) {
		
		Note soprano = null;
		boolean continuer = true;

		Iterator<Note> it = listeGeneralNotes.iterator();
		while (it.hasNext() && continuer) {
			Note actuelle = it.next();
			if (actuelle.getListeNotes().contains(noteSoprano)) {
				soprano = actuelle;
				continuer = false;
			}
		}

		return soprano;
	}

	/**
	 * Retourne les trois accords correspondants à la note Soprano
	 * 
	 * @param soprano
	 *            Note du soprano à l'instant indice

	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 *            
	 * @return liste des trois accords où se trouve la note soprano
	 * */
	public static LinkedList<Accord> trouverAccord(Note soprano, int indice) {
		
		LinkedList<Accord> listeAccord = new LinkedList<Accord>();

		Iterator<Accord> it = listeGeneralAccords.iterator();
		while (it.hasNext()) {
			Accord actuel = it.next();
			if ((soprano.equals(actuel.getTonique()))
					|| (soprano.equals(actuel.getTierce()))
					|| (soprano.equals(actuel.getQuinte()))) {

				if (!((indice == 0) && (actuel.getNom().equals("FAb")))
						|| ((indice == tabSoprano.length - 1) && (actuel
								.getNom().equals("IVb")))) {
					listeAccord.add(actuel);
				}
			}
		}

		return listeAccord;
	
	}

	/**
	 * Trouve tous les jeux possible pour la note soprano d'indice indice et sa
	 * liste d'accord listeAccord
	 * 
	 * @param listeAccord
	 *            liste des trois accords possible pour la note soprano
	 * @param soprano
	 *            Note du soprano à l'instant indice
	 * @param noteSoprano
	 *            L'int au temps indice dans le chant soprano
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant a tous les jeux possible pour l'indice
	 *            indice
	 * 
	 * */
	public static void listeJeuxNote(LinkedList<Accord> listeAccord,
			Note soprano, int noteSoprano, int indice,
			LinkedList<listeJeux> liste) {
		LinkedList<Note> listeNote = null, listeNote2 = null;
		
		listeNote = new LinkedList<Note>();
		
		Iterator<Accord> it = listeAccord.iterator();
		while (it.hasNext()) {
			Accord actuel = it.next();

			if (soprano == actuel.getTonique()) {
				listeNote = new LinkedList<Note>(Arrays.asList(soprano,
						actuel.getTierce(), actuel.getQuinte(),
						actuel.getTonique()));
				listeNote2 = new LinkedList<Note>(Arrays.asList(soprano,
						actuel.getQuinte(), actuel.getTierce(),
						actuel.getTonique()));
			} else {
				if (soprano == actuel.getTierce()) {
					listeNote = new LinkedList<Note>(Arrays.asList(soprano,
							actuel.getTonique(), actuel.getQuinte(),
							actuel.getTonique()));
					listeNote2 = new LinkedList<Note>(Arrays.asList(soprano,
							actuel.getQuinte(), actuel.getTonique(),
							actuel.getTonique()));
				} else {
					if (soprano == actuel.getQuinte()) {
						listeNote = new LinkedList<Note>(Arrays.asList(soprano,
								actuel.getTonique(), actuel.getTierce(),
								actuel.getTonique()));
						listeNote2 = new LinkedList<Note>(Arrays.asList(
								soprano, actuel.getTierce(),
								actuel.getTonique(), actuel.getTonique()));
					}
				}
			}

		
			trouverTableauJeux(listeNote, noteSoprano, indice, liste, actuel);
			trouverTableauJeux(listeNote2, noteSoprano, indice, liste, actuel);
			
		}

	}

	/**
	 * Ajoute dans un tableau 4*2 les notes possible pour une liste de note
	 * 
	 * @param listeNote
	 *            liste de note suivant l'accord accord
	 * @param noteSoprano
	 *            L'int au temps indice dans le chant soprano
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant a tous les jeux possible pour l'indice
	 *            indice
	 * 
	 * @param accord
	 *            Accord correspondant a la listeNote
	 * 
	 * 
	 * */
	public static void trouverTableauJeux(LinkedList<Note> listeNote,
			int noteSoprano, int indice, LinkedList<listeJeux> liste,
			Accord accord) {
		int[][] jeu = new int[4][2];
		Note noteActuelle;
		int intActuel;
		int ind = 1;
		int compteur = 0;

		Iterator<Note> it = listeNote.iterator();
		jeu[0][0] = noteSoprano;
		it.next();

		while (it.hasNext()) {
			noteActuelle = it.next();
			Iterator<Integer> it2 = noteActuelle.getListeNotes().iterator();
			while (it2.hasNext()) {
				intActuel = it2.next();
				if (regleInterval(intActuel, ind + 1)) {
					jeu[ind][compteur] = intActuel;
					compteur++;
				}
			}
			ind++;
			compteur = 0;
		}
		parcoursTableauJeu(jeu, indice, liste, accord);
	}

	/**
	 * Prend un int correspondant à une note, et un autre corresondant à une
	 * voix, et test si l'int de la note peut appartenir à la voix en fonction
	 * de la règle des intervales
	 * 
	 * @param note
	 *            int correspondant à une note
	 * @param voix
	 *            int correspondant à une voix (2:alto,3:tenor,4:basse)
	 * @return true si l'int note compris dans l'interval correspondant à la
	 *         voix, false sinon
	 */
	public static boolean regleInterval(int note, int voix) {
		switch (voix) {
		case 2: // alto
			if (note >= 11 && note <= 22)
				return true;
			break;

		case 3: // tenor
			if (note >= 7 && note <= 19)
				return true;
			break;

		case 4: // basse
			if (note >= 3 && note <= 15)
				return true;
			break;

		}
		return false;
	}

	/**
	 * Prend un tableau à double entrée correspondant à un jeu de note possible
	 * et l'envois a getPath pour trouver toutes les listes de jeux possible à
	 * partir de cette série de note
	 * 
	 * @param jeu
	 *            Tableau contenant tout les differente note possible pour
	 *            chaque voix pour une série de note
	 * 
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant a tous les jeux possible pour l'indice
	 *            indice
	 * 
	 * @param accord
	 *            Accord correspondant a la listeNote
	 * */
	public static void parcoursTableauJeu(int[][] jeu, int indice,
			LinkedList<listeJeux> liste, Accord accord) {
		LinkedList<Integer> chemin = new LinkedList<Integer>();

		getPath(jeu, jeu[0][0], chemin, indice, liste, accord);
	}

	/**
	 * Prend le tableau des notes et stocke tous les jeux possibles dans la
	 * liste qui correspond à l'indice du chant que l'on parcours
	 * 
	 * @param jeu
	 *            Tableau contenant tout les differente note possible pour
	 *            chaque voix pour une série de note
	 * @param sommetSource
	 *            La note du chant soprano donné entrée d'indice indice
	 * @param chemin
	 *            Une liste actuelle de note possible
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant a tous les jeux possible pour l'indice
	 *            indice
	 * 
	 * @param accord
	 *            Accord correspondant a la listeNote
	 * 
	 * */

	public static void getPath(int[][] jeu, int sommetSource,
			LinkedList<Integer> chemin, int indice,
			LinkedList<listeJeux> liste, Accord accord) {
		listeJeux listeJeu;
		LinkedList<Integer> copieChemin = new LinkedList<Integer>();

		chemin.add(sommetSource);

		if (chemin.size() < jeu.length) {

			for (int i = 0; i < jeu[0].length; i++) {
				if ((jeu[chemin.size()][i] != 0)
						&& (jeu[chemin.size()][i] < sommetSource)) {
					copieChemin = copieChemin(chemin);

					getPath(jeu, jeu[chemin.size()][i], copieChemin, indice,
							liste, accord);
				}
			}
		} else {
			listeJeu = new listeJeux(chemin, indice, accord);
			listeJeu.setValeurBeaute1(listeJeu.regleBeauteUne());
			liste.add(listeJeu);
		}
	}

	/**
	 * Prend une liste d'int et renvois une copie de cette liste
	 * 
	 * @param chemin
	 *            liste d'int à copier
	 * @return copie de chemin
	 * 
	 * */

	public static LinkedList<Integer> copieChemin(LinkedList<Integer> chemin) {
		LinkedList<Integer> newChemin = new LinkedList<Integer>();
		int actuel;
		Iterator<Integer> it = chemin.iterator();
		while (it.hasNext()) {
			actuel = it.next();
			newChemin.add(actuel);
		}
		return newChemin;
	}
	/**
	 * Prend une liste de listeJeux et renvois une copie de cette liste
	 * 
	 * @param listeJeux
	 *            liste de listeJeux à copier
	 * @return copie de listeJeux
	 * */

	public static LinkedList<listeJeux> copieListeJeux(
			LinkedList<listeJeux> listeJeux) {
		LinkedList<listeJeux> newListeJeux = new LinkedList<listeJeux>();
		listeJeux actuel;
		Iterator<listeJeux> it = listeJeux.iterator();
		while (it.hasNext()) {
			actuel = it.next();
			newListeJeux.add(actuel);
		}
		return newListeJeux;
	}
}
