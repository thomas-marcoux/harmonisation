package harmonie;

import	java.util.HashMap;
import	java.util.Scanner;
import	java.io.BufferedReader;
import	java.io.FileReader;
import	java.io.IOException;

public class Chant {
	
    /*
     * Appeler le constructeur en lui donnant
     * en parametre le nom du fichier .chant
     * (i.e. "lune.chant")
     * puis appelez la methode
     * public int[]	getSoprano() pour recevoir
     * un tableau d'int contenant les notes soprano.
     * 
     * Dans le tableau, la constante public PAUSE indique un '-'.
     * REPEAT indique que la note precedente se prolonge.
     * 
     */

	
    /*
     * Constantes public 
     */
    
    public static final int	PAUSE = -1;
    public static final int	REPEAT = -2;

    /*
     * Notes
     */
    
    public static final int	DO = 0;
    public static final int	RE = 1;
    public static final int	MI = 2;
    public static final int	FA = 3;
    public static final int	SOL = 4;
    public static final int	LA = 5;
    public static final int	SI = 6;

    
    /*
     * Constantes private
     */
	
    private static final int	CONST = 7;
    private static final String	NOTE_FORMAT
	= "^((do|re|ré|mi|fa|sol|la|si|)[1-4]|-):[1-9]$";    

    /*
     * Tableau de notes soprano et titre du morceau
     */
    
    private int[]	soprano;
    private String	titre;

    /*
     * Constructeur
     */
    
    public Chant(String file_name)
	throws IOException, EmptyFileException, ChantFormatException	{
	this.titre = new String();
	soprano = initSoprano(chantFileToStringTab(file_name));
    }
    
    /*
     * Getters public. 
     */
    
    public int[]	getSoprano()    {
	return soprano;
    }
    public String	getTitre()	{
	return titre;
    }

    /*
     * Methodes private pour lire les fichiers
     * et initialiser les tableaux.
     */
    
    private int[]	initSoprano(String[] notes)	
	throws ChantFormatException	{
	HashMap<String, Integer>	map;
	int	soprano[];
	String	key;
	int	i;
	int	l;

	checkChantFormat(notes);
	map = new HashMap<String, Integer>();
	soprano = new int[getSongLength(notes)];
	key = new String();
	map.put("do", DO);
	map.put("ré", RE);
	map.put("re", RE);
	map.put("mi", MI);
	map.put("fa", FA);
	map.put("sol", SOL);
	map.put("la", LA);
	map.put("si", SI);
	map.put("-", PAUSE);
	i = 0;
	for (String note : notes)
	    {
		key = getKey(note);
		soprano[i] = map.get(key)
		    + CONST * (getNoteOctave(note) - 1);
		l = getNoteLength(note);
		for (int j = 1; j < l; ++j)
		    soprano[++i] = REPEAT;
		++i;
	    }
	return soprano;
    }
    private String[]	chantFileToStringTab(String file_name)
	throws IOException, EmptyFileException    {
	Scanner		file_in;
	String		buff;
	
	buff = new String();
	file_in = new Scanner
	    (new BufferedReader(new FileReader(file_name)));
	if (file_in.hasNextLine())
	    this.titre = file_in.nextLine();
	while (file_in.hasNextLine())
	    buff += file_in.nextLine() + " ";
	file_in.close();
	if (buff.isEmpty())
	    throw new EmptyFileException();
	return buff.split(" ");
    }
    
    
    /*
     * Diverses methode pour recuperer
     * des informations sur les notes
     */
    
    private String	getKey(String note)	{
	return (note.split(":"))[0].replaceAll("\\d","");
    }
    private int	getNoteOctave(String note)	{
	String	buff;

	buff = note.split(":")[0];
	return (buff.equals("-"))
	    ? 1 : buff.charAt(buff.length() - 1) - 48;
    }
    private int	getNoteLength(String note)	{
	return Integer.parseInt((note.split(":"))[1]);
    }
    private int	getSongLength(String[] notes)	{
	int	r;

	r = 0;
	for (String s : notes)
	    r += getNoteLength(s);
	return r;
    }
    
    /*
     * Controle du format
     */
    
    private void	checkChantFormat(String[] notes)
	throws ChantFormatException	{
	for (String s : notes)
	    if (!s.matches(NOTE_FORMAT))
		throw new ChantFormatException(s);
    }
}
