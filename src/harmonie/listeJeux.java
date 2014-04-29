package harmonie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class listeJeux {
	// Attributes pour un "listeJeux"
	private LinkedList<Integer> jeu;
	private HashMap<listeJeux, Integer> suivants;

	private HashMap<listeJeux, Integer> peres;
	private int indice;
	private Accord accord;

	// Attributs pour la "Beaute"
	private int valeurBeaute1;
	private listeJeux plusBeauPrec;
	private int valeurfinale;

	/**
	 * Constructeurs d'un "listeJeux" vide
	 */
	public listeJeux() {
		this.suivants = new HashMap<listeJeux, Integer>();
		this.peres = new HashMap<listeJeux, Integer>();

		this.jeu = null;
		this.indice = 0;
		this.accord = null;
		this.plusBeauPrec = null;
		this.valeurfinale = 0;
	}

	/**
	 * Constructeurs d'un "listeJeux" qui aura pour valeur le jeu, l'indice et
	 * l'accord donné en entrée
	 * 
	 * @param jeu
	 *            LinkedList<Integer> correspondant aux notes du jeu
	 * @param indice
	 *            Integer correspondant à l'indice de listeJeux
	 * @param accord
	 *            Accord correspondant à l'accord de listeJeux
	 */
	public listeJeux(LinkedList<Integer> jeu, int indice, Accord accord) {
		this.suivants = new HashMap<listeJeux, Integer>();
		this.peres = new HashMap<listeJeux, Integer>();

		this.jeu = jeu;
		this.indice = indice;
		this.accord = accord;
		this.plusBeauPrec = null;
		this.valeurfinale = 0;

	}

	/**
	 * Retourne la plus belle listeJeux précédent
	 * 
	 * @return listeJeux correspondant à la plus belle des listeJeux précédentes
	 */
	public listeJeux getPlusBeauPrec() {
		return plusBeauPrec;
	}

	/**
	 * Permet de mettre une listeJeux comme plus belle listeJeux de this
	 * 
	 * @param plusBeauPrec
	 *            listeJeux qui sera la plus belle listeJeux de this
	 */
	public void setPlusBeauPrec(listeJeux plusBeauPrec) {
		this.plusBeauPrec = plusBeauPrec;
	}

	/**
	 * 
	 * @return valeur finale
	 */
	public int getValeurfinale() {
		return valeurfinale;
	}

	/**
	 * 
	 * @param valeurfinale
	 */
	public void setValeurfinale(int valeurfinale) {
		this.valeurfinale = valeurfinale;
	}

	/**
	 * Retourne un boolean pour savoir si listeJeux précedent et this suivent la
	 * regled des Enchainement d'Accords
	 * 
	 * @param precedente
	 *            listeJeux qui sera comparer à this
	 * @return Boolean si les deux listeJeux suivent la règles des enchainement
	 *         d'Accords
	 */
	public boolean regleAccord(listeJeux precedente) {
		String accordSuiv = this.getAccord().getNom();

		switch (precedente.getAccord().getNom()) {
		case "DO":
			if (!accordSuiv.equals("FAb"))
				return true;
			break;

		case "RE":
			if (accordSuiv.equals("SOL") || accordSuiv.equals("SI")
					|| accordSuiv.equals("RE"))
				return true;
			break;

		case "MI":
			if (!((accordSuiv.equals("FAb")) || (accordSuiv.equals("DO"))))
				return true;
			break;

		case "FA":
			if (!(this.getAccord().getNom().equals("FAb")))
				return true;
			break;

		case "FAb":
			if (accordSuiv.equals("DO") || accordSuiv.equals("FAb"))
				return true;
			break;

		case "SOL":
			if (accordSuiv.equals("DO") || accordSuiv.equals("MI")
					|| accordSuiv.equals("FAb") || accordSuiv.equals("SOL")
					|| accordSuiv.equals("LA"))
				return true;
			break;

		case "LA":
			if (accordSuiv.equals("RE") || accordSuiv.equals("MI")
					|| accordSuiv.equals("FA") || accordSuiv.equals("SOL")
					|| accordSuiv.equals("LA"))
				return true;
			break;

		case "SI":
			if (accordSuiv.equals("DO") || accordSuiv.equals("MI")
					|| accordSuiv.equals("SI"))
				return true;
			break;

		}

		return false;
	}

	/**
	 * 
	 * @return regle de beaute une
	 */
	public int regleBeauteUne() {
		int alto, tenor;

		alto = this.getJeu().get(1);
		tenor = this.getJeu().get(2);

		return 20 - (alto - tenor);
	}

	/**
	 * 
	 * @param precedente
	 * @return regle de beaute deux
	 */
	public int regleBeauteDeux(listeJeux precedente) {
		int altoSuiv, tenorSuiv, altoPrec, tenorPrec;

		altoSuiv = this.getJeu().get(1);
		tenorSuiv = this.getJeu().get(2);

		altoPrec = precedente.getJeu().get(1);
		tenorPrec = precedente.getJeu().get(2);

		return (10 - (Regles.differenceRegle(altoPrec, altoSuiv)))
				+ (10 - (Regles.differenceRegle(tenorPrec, tenorSuiv)));

	}

	/**
	 * 
	 * @param precedente
	 * @return regle de beaute trois
	 */
	public int regleBeauteTrois(listeJeux precedente) {
		int basseSuiv, bassePrec;

		bassePrec = precedente.getJeu().get(3);
		basseSuiv = this.getJeu().get(3);

		return Regles.differenceRegle(bassePrec, basseSuiv);

	}

	/**
	 * 
	 * @param precedente
	 * @return regle de beaute quatre
	 */
	public int regleBeauteQuatre(listeJeux precedente) {
		return regleBeauteDeux(precedente) + regleBeauteTrois(precedente);
	}

	/**
	 * 
	 * @param precedente
	 * @return est-ce qu'il y a une difference de regles entre deux notes
	 */
	public boolean regleDifferenceDeuxNotes(listeJeux precedente) {
		Iterator<Integer> it1 = precedente.getJeu().iterator();
		Iterator<Integer> it2 = this.getJeu().iterator();

		while (it1.hasNext()) {
			int actuelPrec = it1.next();
			int actuelSuiv = it2.next();

			if ((!Regles.difference(actuelPrec, actuelSuiv))
					&& memeAccord(actuelPrec, actuelSuiv, precedente)) {
				return false;
			}

		}
		return true;
	}

	/**
	 * 
	 * @param prec
	 * @param suiv
	 * @param precedente
	 * @return est-ce que les deux notes sont du meme accord
	 */
	public boolean memeAccord(int prec, int suiv, listeJeux precedente) {
		Note notePrec = Note.trouverNote(prec);
		Note noteSuiv = Note.trouverNote(suiv);

		Accord accordSuiv = this.getAccord();

		if (((notePrec.equals(accordSuiv.getQuinte())) && (!notePrec
				.equals(noteSuiv)))
				|| ((notePrec.equals(accordSuiv.getTierce())) && (!notePrec
						.equals(noteSuiv)))
				|| ((notePrec.equals(accordSuiv.getTonique())) && (!notePrec
						.equals(noteSuiv)))) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne la LinkdeList<Integer> correspondant au Jeu de this
	 * 
	 * @return Liste correspondant au Jeu de this
	 */
	public LinkedList<Integer> getJeu() {
		return jeu;
	}

	/**
	 * Permet de modifier "Jeu" de this
	 * @param jeu que prend this
	 */
	public void setJeu(LinkedList<Integer> jeu) {
		this.jeu = jeu;
	}

	/**
	 * Retourne la HashMap<listeJeux, Integer> correspondant aux listeJeux
	 * suivant de this
	 * @return hashmap des listeJeux suivant de this
	 */
	public HashMap<listeJeux, Integer> getSuivants() {
		return suivants;
	}

	/**
	 * Permet de modifier les listeJeux suivant de this
	 * 
	 * @param suivants des listeJeux suivant que prendra this
	 */
	public void setSuivants(HashMap<listeJeux, Integer> suivants) {
		this.suivants = suivants;
	}

	/**
	 * Retourne la HashMap des listeJeux peres de this
	 * 
	 * @return des listeJeux peres de this
	 */
	public HashMap<listeJeux, Integer> getPeres() {
		return peres;
	}

	/**
	 * Permet de modifier la HashMap des listeJeux peres de this
	 * 
	 * @param peres des listeJeux peres que prendra this
	 * 
	 */
	public void setPeres(HashMap<listeJeux, Integer> peres) {
		this.peres = peres;
	}

	/**
	 * Retourne l'indice de la listeJeux this
	 * 
	 * @return indice de this
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Permet de modifier l'indice de la listeJeux this
	 * 
	 * @param indice Integer qui sera l'indice de this
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}

	/**
	 * Retourne l'"Accord" de this
	 * 
	 * @return Accord qui est l'accord de la listeJeux this
	 */
	public Accord getAccord() {
		return accord;
	}

	/**
	 * Permet de modifier l'accord de la listeJeux this
	 * 
	 * @param accord
	 *            Accord qui sera le nouvel accord de la listeJeux de this
	 */
	public void setAccord(Accord accord) {
		this.accord = accord;
	}

	/**
	 * Retourne la valeur de Beauté n°1 de la listeJeux this
	 * 
	 * @return Integer représentant la valeur de Beauté 1
	 */
	public int getValeurBeaute1() {
		return valeurBeaute1;
	}

	/**
	 * Permet de modifeir la valeur de Beauté n°1 de la listeJeux this
	 * 
	 * @param valeurBeaute1
	 *            Integer que prendra la valeur de Beauté 1 de this
	 */
	public void setValeurBeaute1(int valeurBeaute1) {
		this.valeurBeaute1 = valeurBeaute1;
	}

}
