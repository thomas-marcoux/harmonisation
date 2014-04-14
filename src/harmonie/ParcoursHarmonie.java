package harmonie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class ParcoursHarmonie {
	public static HashMap<LinkedList<listeJeux>, Integer> HashMapHarmonies = new HashMap<LinkedList<listeJeux>, Integer>();

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
	public static void initialisationSuivant(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux,
			LinkedList<Accord> listeGeneraleAccords,
			LinkedList<Note> listeGeneraleNotes, int k) {

		for (int i = (listeGeneraleJeux.size() - 1); i > 0; i--) {
			ajouteSuivant(listeGeneraleJeux.get(i),
					listeGeneraleJeux.get(i - 1), listeGeneraleNotes, k);
		}

		if (k == 1)
			initialisationListesJeuxRegleUne(listeGeneraleJeux);
		if (k == 2 || k == 3)
			initialisationListesJeuxRegleDeux(listeGeneraleJeux);
		if (k == 4)
			initialisationListesJeuxRegleQuatre(listeGeneraleJeux);
		if (k == 5)
			nombreHarmonisation(listeGeneraleJeux);

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
			LinkedList<listeJeux> precedente,
			LinkedList<Note> listeGeneraleNotes, int k) {

		Iterator<listeJeux> it = suivante.iterator();
		while (it.hasNext()) {
			listeJeux actuelleSuivante = it.next();
			Iterator<listeJeux> it2 = precedente.iterator();
			while (it2.hasNext()) {
				listeJeux actuellePrecedente = it2.next();
				if ((actuelleSuivante.regleAccord(actuellePrecedente,
						listeGeneraleNotes))
						&& (actuelleSuivante.regleDifferenceDeuxNotes(
								actuellePrecedente, listeGeneraleNotes))) {
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

	/**
	 * Dans le cas ou k=1, parcours la listeGeneraleJeux et fait correspondre
	 * aux listes une valeur de beauté dependant d'elle même et des liste d'où
	 * elles peuvent venir
	 * 
	 * @param listeGenraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * 
	 * */

	public static void initialisationListesJeuxRegleUne(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {
		Iterator<listeJeux> itInit = listeGeneraleJeux.get(0).iterator();
		while (itInit.hasNext()) {
			listeJeux actuelInit = itInit.next();
			actuelInit.setValeurfinale(actuelInit.getValeurBeaute1());
		}

		for (int i = 0; i < listeGeneraleJeux.size(); i++) {
			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();
					if (suivActuel.getValeurfinale() < actuel.getValeurfinale()
							+ suivActuel.getValeurBeaute1()) {
						suivActuel.setValeurfinale(actuel.getValeurfinale()
								+ suivActuel.getValeurBeaute1());
						suivActuel.setPlusBeauPrec(actuel);
					}
				}
			}
		}

		LinkedList<listeJeux> chemin = new LinkedList<listeJeux>();

		Iterator<listeJeux> itFin = listeGeneraleJeux.get(15).iterator();
		listeJeux meilleur = null;
		int meilleurInt = 0;
		while (itFin.hasNext()) {
			listeJeux actuelFin = itFin.next();
			if (actuelFin.getValeurfinale() > meilleurInt) {
				meilleur = actuelFin;
				meilleurInt = actuelFin.getValeurfinale();
			}
		}
		rechercheChemin(listeGeneraleJeux, chemin, meilleur);

	}

	/**
	 * Dans le cas ou k=2 ou k=3, parcours la listeGeneraleJeux et fait
	 * correspondre aux listes une valeur de beauté dependant d'elle même et des
	 * liste d'où elles peuvent venir
	 * 
	 * @param listeGenraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * 
	 * 
	 * 
	 * */
	public static void initialisationListesJeuxRegleDeux(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {

		for (int i = 0; i < listeGeneraleJeux.size(); i++) {
			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();
					if (suivActuel.getValeurfinale() < actuel.getValeurfinale()
							+ suivActuelEnt.getValue()) {
						suivActuel.setValeurfinale(actuel.getValeurfinale()
								+ suivActuelEnt.getValue());
						suivActuel.setPlusBeauPrec(actuel);
					}
				}
			}
		}

		LinkedList<listeJeux> chemin = new LinkedList<listeJeux>();

		Iterator<listeJeux> itFin = listeGeneraleJeux.get(15).iterator();
		listeJeux meilleur = null;
		int meilleurInt = 0;
		while (itFin.hasNext()) {
			listeJeux actuelFin = itFin.next();
			if (actuelFin.getValeurfinale() > meilleurInt) {
				meilleur = actuelFin;
				meilleurInt = actuelFin.getValeurfinale();
			}
		}
		rechercheChemin(listeGeneraleJeux, chemin, meilleur);

	}

	/**
	 * Dans le cas ou k=4, parcours la listeGeneraleJeux fait correspondre aux
	 * listes une valeur de beauté dependant d'elle même et des liste d'où elles
	 * peuvent venir
	 * 
	 * @param listeGenraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * 
	 * 
	 * 
	 * */

	public static void initialisationListesJeuxRegleQuatre(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {
		Iterator<listeJeux> itInit = listeGeneraleJeux.get(0).iterator();
		while (itInit.hasNext()) {
			listeJeux actuelInit = itInit.next();
			actuelInit.setValeurfinale(actuelInit.getValeurBeaute1());
		}

		for (int i = 0; i < listeGeneraleJeux.size(); i++) {
			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();
					if (suivActuel.getValeurfinale() < actuel.getValeurfinale()
							+ suivActuel.getValeurBeaute1()
							+ suivActuelEnt.getValue()) {
						suivActuel.setValeurfinale(actuel.getValeurfinale()
								+ suivActuel.getValeurBeaute1()
								+ suivActuelEnt.getValue());
						suivActuel.setPlusBeauPrec(actuel);
					}
				}
			}
		}

		LinkedList<listeJeux> chemin = new LinkedList<listeJeux>();

		Iterator<listeJeux> itFin = listeGeneraleJeux.get(15).iterator();
		listeJeux meilleur = null;
		int meilleurInt = 0;
		while (itFin.hasNext()) {
			listeJeux actuelFin = itFin.next();
			if (actuelFin.getValeurfinale() > meilleurInt) {
				meilleur = actuelFin;
				meilleurInt = actuelFin.getValeurfinale();
			}
		}
		rechercheChemin(listeGeneraleJeux, chemin, meilleur);

	}

	/**
	 * Dans le cas où on cherche le nombre d'haonisation, parcours la
	 * listeGeneraleJeux et compte le nombre d'harmonisation possibles en
	 * partant de la fin et en additionnant tous les suivants possible de chaque
	 * jeux
	 * 
	 * @param listeGenraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * 
	 * 
	 * 
	 * */

	public static void nombreHarmonisation(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux) {
		int nbHarmonie = 0;

		Iterator<listeJeux> itInit = listeGeneraleJeux.get(15).iterator();
		while (itInit.hasNext()) {
			listeJeux actuelInit = itInit.next();
			actuelInit.setValeurfinale(1);
		}

		for (int i = listeGeneraleJeux.size() - 2; i >= 0; i--) {
			Iterator<listeJeux> it = listeGeneraleJeux.get(i).iterator();
			while (it.hasNext()) {
				listeJeux actuel = it.next();

				Iterator<Entry<listeJeux, Integer>> it2 = actuel.getSuivants()
						.entrySet().iterator();
				while (it2.hasNext()) {
					Entry<listeJeux, Integer> suivActuelEnt = it2.next();
					listeJeux suivActuel = suivActuelEnt.getKey();

					actuel.setValeurfinale(actuel.getValeurfinale()
							+ suivActuel.getValeurfinale());
				}
			}
		}

		Iterator<listeJeux> itFin = listeGeneraleJeux.get(0).iterator();
		while (itFin.hasNext()) {
			listeJeux actuelFin = itFin.next();
			nbHarmonie += actuelFin.getValeurfinale();
		}

		System.out.println("Nombre d'harmonie: " + nbHarmonie);

	}

	/**
	 * Prend la listeGeneraleJeux en entrée qui pour chaque liste, contient un
	 * int qui reprensente sa valeur de beauté, et un precedent qui correspond à
	 * la plus belle liste à l'instant i-1 et remonte les liste en les stockant
	 * au fur et à mesure dans chemin
	 * 
	 * @param listeGenraleJeux
	 *            La liste de tous les jeux possible à chaque instant pour un
	 *            chant soprano donné
	 * @param chemin
	 *            La liste finale qui contiendra la plus belle Harmonie
	 * @param meilleure
	 *            Une listeJeux qui va continir pour chaque instant, la plus
	 *            belle listeJeux possible
	 * 
	 * 
	 * */

	public static void rechercheChemin(
			ArrayList<LinkedList<listeJeux>> listeGeneraleJeux,
			LinkedList<listeJeux> chemin, listeJeux meilleur) {
		chemin.addFirst(meilleur);
		if (meilleur.getPlusBeauPrec() != null) {
			rechercheChemin(listeGeneraleJeux, chemin,
					meilleur.getPlusBeauPrec());
		} else {
			Iterator<listeJeux> it = chemin.iterator();
			System.out.println("Plus belle harmonie");
			while (it.hasNext()) {
				listeJeux actuelle = it.next();
				System.out.println(actuelle.toString());
			}
		}
	}
}
