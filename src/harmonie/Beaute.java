package harmonie;

public class Beaute {
	/*
	 * Cette classe calculera une plus belle
	 * harmonisation du chant passe en parametre
	 * suivant le crite crit
	 * 
	 */
	
	private	 int	crit;
	private Chant	c;
	
	public Beaute(int crit,
			String file_in, String file_out) {
		/*
		 * enregistre le critere crit et cree
		 * un object Chant
		 */
		c = new Chant(file_in);	
		this.crit = crit;
	}
}
