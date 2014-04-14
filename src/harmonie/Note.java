package harmonie;

import java.util.LinkedList;

public class Note {
	private String nom;
	private LinkedList<Integer> listeNotes;

	public Note(String nom, int note1, int note2, int note3, int note4) {
		this.nom = nom;
		listeNotes = new LinkedList<Integer>();
		listeNotes.add(note1);
		listeNotes.add(note2);
		listeNotes.add(note3);
		listeNotes.add(note4);
	}

	public boolean equals(Note a) {
		return (this.getNom().equals(a.getNom()));
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LinkedList<Integer> getListeNotes() {
		return listeNotes;
	}

	public void setListeNotes(LinkedList<Integer> listeNotes) {
		this.listeNotes = listeNotes;
	}
}
