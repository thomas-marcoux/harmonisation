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
	 * Constantes private
	 */
	
    private static final int	SOPRANO = 0;
    private static final int	ALTO = 1;
    private static final int	TENOR = 2;
    private static final int	BASSE = 3;
    private static final int	CONST = 7;
    private static final int	TAB_SIZE = 4;
    private static final String	NOTE_FORMAT
	= "^((do|re|ré|mi|fa|sol|la|si|)\\d|-):\\d$";    
    
    /*
     * Double tableau contenant la totalite des notes
     */
    
    private int[][]	chant_tab;

    /*
     * Constructeur
     */
    
    public Chant(String file_name) {
	chant_tab = new int[TAB_SIZE][];
	try {
	    setTab(chantFileToStringTab(file_name));
	}
	catch (IOException | ChantFormatException
	       | EmptyFileException e) {
	    System.out.println(e);
	}
    }
    
    /*
     * Getters public. Pour l'instant getSoprano est le seul a fonctionner
     */
    
    public int[][]	getTab()    {
	return chant_tab;
    }
    public int[]	getSoprano()    {
	return chant_tab[SOPRANO];
    }
    public int[]	getAlto()    {
	return chant_tab[ALTO];
    }
    public int[]	getTenor()    {
	return chant_tab[TENOR];
    }
    public int[]	getBasse()    {
	return chant_tab[BASSE];
    }

    /*
     * Methodes private pour lire les fichiers
     * et initialiser les tableaux.
     */
    
    private void	setTab(String[] notes)	
    throws ChantFormatException	{
	checkChantFormat(notes);
	setSoprano(initSoprano(notes));
	/*
	 * setAlto();
	 * setTenor();
	 * setBasse();
	 */
    }
    private int[]	initSoprano(String[] notes)	{
	int	soprano[];
	HashMap<String, Integer>	map;
	String	key;
	int	i;
	int	l;

	soprano = new int[getSongLength(notes)];
	map = new HashMap<String, Integer>();
	key = new String();
	map.put("do", 0);
	map.put("ré", 1);
	map.put("re", 1);
	map.put("mi", 2);
	map.put("fa", 3);
	map.put("sol", 4);
	map.put("la", 5);
	map.put("si", 6);
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
	while (file_in.hasNext())
	    buff += file_in.next() + " ";
	file_in.close();
	if (buff.isEmpty())
	    throw new EmptyFileException();
	return buff.split(" ");
    }
    
    /*
     * Setters private
     */
    
    private void	setSoprano(int[] soprano)	{
	chant_tab[SOPRANO] = soprano;
    }
    private void	setAlto(int[] alto)	{
	chant_tab[ALTO] = alto;
    }
    private void	setTenor(int[] tenor)	{
	chant_tab[TENOR] = tenor;
    }
    private void	setBasse(int[] basse)	{
	chant_tab[BASSE] = basse;
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
