package harmonie;

public class Nombre {
	/*
	 * Cette classe comptera le nombre
	 * d'harmonisation du chant contenu dans
	 * le fichier passe en parametre 
	 */
	
	private Chant	c;
	
	public	Nombre(String file_in) {
		c = new Chant(file_in);
	}
	public void	count() {
		/*
		 * compte et affiche le nombre
		 * d'harmonisation
		 */
	}
}
