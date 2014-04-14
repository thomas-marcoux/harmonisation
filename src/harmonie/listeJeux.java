package harmonie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class listeJeux {
	private LinkedList<Integer> jeu;
	private HashMap<listeJeux, Integer> suivants;

	private HashMap<listeJeux, Integer> peres;
	private int indice;
	private Accord accord;

	// Attributs pour la "Beaute"
	private int valeurBeaute1;
	private listeJeux plusBeauPrec;
	private int valeurfinale;

	public listeJeux() {
		this.jeu = null;
		this.suivants = new HashMap<listeJeux, Integer>();
		this.peres = new HashMap<listeJeux, Integer>();
		this.indice = 0;
		this.accord = null;
		this.plusBeauPrec = null;
		this.valeurfinale = 0;
	}

	public listeJeux(LinkedList<Integer> jeu, int indice, Accord accord) {
		this.jeu = jeu;
		this.suivants = new HashMap<listeJeux, Integer>();
		this.peres = new HashMap<listeJeux, Integer>();
		this.indice = indice;
		this.accord = accord;
		this.plusBeauPrec = null;
		this.valeurfinale = 0;

	}

	public listeJeux getPlusBeauPrec() {
		return plusBeauPrec;
	}

	public void setPlusBeauPrec(listeJeux plusBeauPrec) {
		this.plusBeauPrec = plusBeauPrec;
	}

	public int getValeurfinale() {
		return valeurfinale;
	}

	public void setValeurfinale(int valeurfinale) {
		this.valeurfinale = valeurfinale;
	}

	public String toString() {
		String s = "";
		Iterator<Integer> it = jeu.iterator();
		while (it.hasNext()) {
			int actuel = it.next();
			s += actuel + " ";
		}
		return s += "\n";
	}

	public boolean regleAccord(listeJeux precedente,
			LinkedList<Note> listeGeneralNotes) {

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

	public int regleBeauteUne() {
		int alto, tenor;

		alto = this.getJeu().get(1);
		tenor = this.getJeu().get(2);

		return 20 - (alto - tenor);
	}

	public int regleBeauteDeux(listeJeux precedente) {
		int altoSuiv, tenorSuiv, altoPrec, tenorPrec;

		altoSuiv = this.getJeu().get(1);
		tenorSuiv = this.getJeu().get(2);

		altoPrec = precedente.getJeu().get(1);
		tenorPrec = precedente.getJeu().get(2);

		return (10 - (differenceRegle(altoPrec, altoSuiv)))
				+ (10 - (differenceRegle(tenorPrec, tenorSuiv)));

	}

	public int regleBeauteTrois(listeJeux precedente) {
		int basseSuiv, bassePrec;

		bassePrec = precedente.getJeu().get(3);
		basseSuiv = this.getJeu().get(3);

		return differenceRegle(bassePrec, basseSuiv);

	}

	public int regleBeauteQuatre(listeJeux precedente) {
		return regleBeauteDeux(precedente) + regleBeauteTrois(precedente);
	}

	public boolean regleDifferenceDeuxNotes(listeJeux precedente,
			LinkedList<Note> listeGeneralNotes) {
		Iterator<Integer> it1 = precedente.getJeu().iterator();
		Iterator<Integer> it2 = this.getJeu().iterator();

		while (it1.hasNext()) {
			int actuelPrec = it1.next();
			int actuelSuiv = it2.next();
			
			if ((!difference(actuelPrec, actuelSuiv))
					&& memeAccord(actuelPrec, actuelSuiv, precedente,
							listeGeneralNotes)) {
				return false;
			}

		}
		return true;
	}


	public boolean memeAccord(int prec, int suiv, listeJeux precedente,
			LinkedList<Note> listeGeneralNotes) {
		Note notePrec = trouverNote(prec, listeGeneralNotes);
		Note noteSuiv = trouverNote(suiv, listeGeneralNotes);

		Accord accordSuiv = this.getAccord();

		if (((notePrec.equals(accordSuiv.getQuinte())) && (!notePrec.equals(noteSuiv))) ||
			((notePrec.equals(accordSuiv.getTierce())) && (!notePrec.equals(noteSuiv))) || 
			((notePrec.equals(accordSuiv.getTonique())) && (!notePrec.equals(noteSuiv)))) {
			return false;
		}
		return true;
	}

	public Note trouverNote(int intNote, LinkedList<Note> listeGeneralNotes) {
		Note note = null;
		boolean continuer = true;

		Iterator<Note> it = listeGeneralNotes.iterator();
		while (it.hasNext() && continuer) {
			Note actuelle = it.next();
			if (actuelle.getListeNotes().contains(intNote)) {
				note = actuelle;
				continuer = false;
			}
		}
		return note;
	}

	public boolean difference(int a, int b) {
		int c;
		if (a < b) {
			c = b;
			b = a;
			a = c;
		}
		return ((a - b) <= 6);
	}

	public int differenceRegle(int a, int b) {
		int c;
		if (a < b) {
			c = b;
			b = a;
			a = c;
		}
		return a - b;
	}

	public LinkedList<Integer> getJeu() {
		return jeu;
	}

	public void setJeu(LinkedList<Integer> jeu) {
		this.jeu = jeu;
	}

	public HashMap<listeJeux, Integer> getSuivants() {
		return suivants;
	}

	public void setSuivants(HashMap<listeJeux, Integer> suivants) {
		this.suivants = suivants;
	}

	public HashMap<listeJeux, Integer> getPeres() {
		return peres;
	}

	public void setPeres(HashMap<listeJeux, Integer> peres) {
		this.peres = peres;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public Accord getAccord() {
		return accord;
	}

	public void setAccord(Accord accord) {
		this.accord = accord;
	}

	public int getValeurBeaute1() {
		return valeurBeaute1;
	}

	public void setValeurBeaute1(int valeurBeaute1) {
		this.valeurBeaute1 = valeurBeaute1;
	}

}
