package harmonie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Initialisations {
	/**
	 * Permet d'initialiser la "listeGeneraleJeux"
	 * 
	 * @return LinkedList<listeJeu> La "listeGeneralJeux" initialisée
	 */
	public static ArrayList<LinkedList<listeJeux>> initialisationDuGraphe(int[] tabSoprano) {
		ArrayList<LinkedList<listeJeux>> listeGeneraleJeux = new ArrayList<LinkedList<listeJeux>>();
		LinkedList<listeJeux> liste = new LinkedList<listeJeux>();

		for (int i = 0; i < tabSoprano.length; i++) {
			Note note = Note.trouverNote(tabSoprano[i]);
			LinkedList<Accord> accord = Accord.trouverAccord(note, i, tabSoprano);

			listeJeuxNote(accord, note, tabSoprano[i], i, liste);

			LinkedList<listeJeux> listeCopie = ParcoursGraphe
					.copieListeJeux(liste);
			listeGeneraleJeux.add(listeCopie);
			liste.clear();

		}

		return listeGeneraleJeux;
	}

	/**
	 * Convertie le tableau int[] Chant en int[]tabSoprano qui sera utiliser pour les harmonisations
	 * 
	 * @param int [] tabChant contenant les notes de chant
	 * @return int[] tabSoprano contenant les notes formaté pour les methodes d'harmoinsations
	 */
	public static int [] convertionTab(int [] tabChant){
		int [] tabSoprano = new int [tabChant.length];
		int	last_note = Chant.DO;

		for(int i=0; i<tabChant.length; i++){
			if (tabChant[i] == Chant.REPEAT) {
			    tabSoprano[i] = last_note;
			} else if (tabChant[i] == Chant.PAUSE) {
			    tabSoprano [i] = last_note;
			} else {
			    tabSoprano[i] = tabChant[i];
			    last_note = tabSoprano[i];
			}
		}
		return tabSoprano;
	}
	
	
	/**
	 * Convertie une "LinkedList<listeJeux>" en un "int [][] tableau" comportant
	 * les notes sous format "Integer" de la meilleur harmonisation
	 * 
	 * @param listeFinalHarmonie
	 *            LinkedList<listeJeux> comprennant la meilleur harmonisation
	 * @return int [][] Retourne le tableau de la meilleur harmonisation
	 */
	public static int[][] convertionListTab(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux,
			int [] soprano,
			int k) {
		
		LinkedList<listeJeux> listeFinalHarmonie = new LinkedList<listeJeux>();
		
		
		
		if (k == 1)
			 listeFinalHarmonie = ParcoursGraphe.recherche(Regles
					.initialisationListesJeuxRegleUne(listeGeneraleJeux));
		if (k == 2 || k == 3)
			listeFinalHarmonie =  ParcoursGraphe.recherche(Regles
					.initialisationListesJeuxRegleDeux(listeGeneraleJeux));
		if (k == 4)
			listeFinalHarmonie =  ParcoursGraphe.recherche(Regles
					.initialisationListesJeuxRegleQuatre(listeGeneraleJeux));
			
		int[][] tableau = new int[listeFinalHarmonie.size()][5];

		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[i].length; j++) {
				switch (j) {
				case 1:
					tableau[i][j] = listeFinalHarmonie.get(i).getJeu().get(1);
					break;
				case 2:
					tableau[i][j] = listeFinalHarmonie.get(i).getJeu().get(2);
					break;
				case 3:
					tableau[i][j] = listeFinalHarmonie.get(i).getJeu().get(3);
					break;
				}
			}
		}
		 
		for(int i = 0; i < soprano.length; i++){
			tableau[i][0] = soprano[i];
		}
		
		tableau[0][4] = (int)(Regles.nombreHarmonisation(listeGeneraleJeux));
		
		int [][] tab = new int [5][tableau.length];
		
		for(int i = 0; i< tableau.length; i++){
			tab[0][i] = tableau[i][0];
			tab[1][i] = tableau[i][1];
			tab[2][i] = tableau[i][2];
			tab[3][i] = tableau[i][3];
			tab[4][i] = tableau[i][4];
			
		}
		
		
		return tab;
	}

	/**
	 * Trouve tous les jeux possibles pour la "Note" soprano d'indice "indice"
	 * et sa liste d'accord "listeAccord"
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
	 * Ajoute dans un tableau 4*2 les notes possibles pour une liste de note
	 * 
	 * @param listeNote
	 *            liste de note suivant l'accord accord
	 * @param noteSoprano
	 *            L'int au temps indice dans le chant soprano
	 * @param indice
	 *            L'indice de noteSoprano dans le chant soprano
	 * @param liste
	 *            Liste correspondant à tous les jeux possibles pour l'indice
	 *            indice
	 * 
	 * @param accord
	 *            Accord correspondant à la listeNote
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
				if (Regles.regleInterval(intActuel, ind + 1)) {
					jeu[ind][compteur] = intActuel;
					compteur++;
				}
			}
			ind++;
			compteur = 0;
		}
		ParcoursGraphe.parcoursTableauJeu(jeu, indice, liste, accord);
	}

	/**
	 * Prend une ArrayList de liste de listeJeux et le k donné en entrée et
	 * execute la methode correspondant au k
	 * 
	 * @param listeGeneraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @param listeGeneraleAccord
	 *            La liste de tous les Accords
	 * @param listeGeneraleNotes
	 *            La liste de toute les Notes
	 * @param k
	 *            int correspondant à quelle fonction de beauté on a choisit
	 * 
	 * */
	public static ArrayList<LinkedList<listeJeux>> initialisationSuivant(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux, int k) {

		for (int i = (listeGeneraleJeux.size() - 1); i > 0; i--) {
			ajouteSuivant(listeGeneraleJeux.get(i),
					listeGeneraleJeux.get(i - 1), k);
		}
		
		return listeGeneraleJeux;
	}



	/**
	 * Prend un deux listes de listeJeux correspondant à tous les jeux possible
	 * à un instant i et i+1 et en fonction des règles et du k donné en entrée,
	 * stocks dans la liste de suivant et de precedent de chaque liste les
	 * suivant et les precedent possible pour chaque listeJeux
	 * 
	 * @param suivante
	 *            liste de listeJeux correspondant à l'instant i+1
	 * @param precedente
	 *            liste de listeJeux correspondant à l'instant i
	 * @param listeGeneraleNotes
	 *            La liste de toute les Notes
	 * @param k
	 *            int correspondant à quelle fonction de beauté on a choisit
	 * 
	 * 
	 * */

	public static void ajouteSuivant(LinkedList<listeJeux> suivante,
			LinkedList<listeJeux> precedente, int k) {

		Iterator<listeJeux> it = suivante.iterator();
		while (it.hasNext()) {
			listeJeux actuelleSuivante = it.next();
			Iterator<listeJeux> it2 = precedente.iterator();
			while (it2.hasNext()) {
				listeJeux actuellePrecedente = it2.next();
				if ((actuelleSuivante.regleAccord(actuellePrecedente)
						&& (actuelleSuivante.regleDifferenceDeuxNotes(
								actuellePrecedente)))){
					if (k == 2 || k == 1 || k == 5) {
						actuelleSuivante.getPeres().put(
								actuellePrecedente,
								actuelleSuivante
										.regleBeauteDeux(actuellePrecedente));
						actuellePrecedente.getSuivants().put(
								actuelleSuivante,
								actuelleSuivante
										.regleBeauteDeux(actuellePrecedente));
					} else {
						if (k == 3) {
							actuelleSuivante
									.getPeres()
									.put(actuellePrecedente,
											actuelleSuivante
													.regleBeauteTrois(actuellePrecedente));
							actuellePrecedente
									.getSuivants()
									.put(actuelleSuivante,
											actuelleSuivante
													.regleBeauteTrois(actuellePrecedente));
						} else {
							if (k == 4) {
								actuelleSuivante
										.getPeres()
										.put(actuellePrecedente,
												actuelleSuivante
														.regleBeauteQuatre(actuellePrecedente));
								actuellePrecedente
										.getSuivants()
										.put(actuelleSuivante,
												actuelleSuivante
														.regleBeauteQuatre(actuellePrecedente));
							}
						}
					}
				}
			}
		}
	}

}
