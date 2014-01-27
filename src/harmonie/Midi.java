package harmonie;

public class Midi {
	/*
	 * Cette classe contiendra tous les outils
	 * pour transformer le fichier chant donne
	 * en fichier au format Midi
	 */
	
	private Chant	c;
	
	public	Midi(String file_in, String file_out) {
		c = new Chant(file_in);
		}
	public void	writeHarmo() {
		/*
		 * ecrit le chant harmonise dans le fichier
		 * correspondant
		 */
	}
}
