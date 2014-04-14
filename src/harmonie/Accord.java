package harmonie;

public class Accord {
	private String nom;
	private Note tonique;
	private Note tierce;
	private Note quinte;

	public Accord(String nom, Note tonique, Note tierce, Note quinte) {
		this.nom = nom;
		this.tonique = tonique;
		this.tierce = tierce;
		this.quinte = quinte;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Note getTonique() {
		return tonique;
	}

	public void setTonique(Note tonique) {
		this.tonique = tonique;
	}

	public Note getTierce() {
		return tierce;
	}

	public void setTierce(Note tierce) {
		this.tierce = tierce;
	}

	public Note getQuinte() {
		return quinte;
	}

	public void setQuinte(Note quinte) {
		this.quinte = quinte;
	}
}
