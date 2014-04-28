package harmonie;

import	Exceptions.*;
import	java.util.HashMap;
import	java.util.Scanner;
import	java.io.BufferedReader;
import	java.io.File;
import	java.io.FileReader;
import	java.io.IOException;

public class Chant {
	
    /**
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
	
    /**
     * Constantes public 
     */    
    public static final int	PAUSE = -1;
    public static final int	REPEAT = -2;
    public static final int	DEFAULT_BEAUTE = 1;

    /**
     * Notes
     */
    public static final int	DO = 0;
    public static final int	RE = 1;
    public static final int	MI = 2;
    public static final int	FA = 3;
    public static final int	SOL = 4;
    public static final int	LA = 5;
    public static final int	SI = 6;

    
    /**
     * Constantes private
     */	
    private static final int	SOPRANO = 0;
    private static final int	ALTO = 1;
    private static final int	TENOR = 2;
    private static final int	BASSE = 3;
    private static final int	CONST_OCTAVE = 7;
    private static final String	NOTE_FORMAT
	= "^((do|re|ré|mi|fa|sol|la|si|)[1-4]|-):[1-9]$";    

    /**
     * Tableau de notes soprano et titre du morceau
     */
    private int[][]	tracks;
    private String	titre;

    /**
     * Constructeur de la classe Chant.
     * @param	file	Le File .chant
     * @param	k	L'indice de beaute
     * @return			Un objet Chant
     */
    public Chant(File file, int k)
	throws IOException, EmptyFileException, ChantFormatException {
	this.titre = new String();
	
	tracks = Initialisations.convertionListTab
	    (Initialisations.initialisationSuivant
	     (Initialisations.initialisationDuGraphe
	      (initSoprano(chantFileToStringTab(file))), k));
    }

    /**
     * Constructeur de la classe Chant.
     * @param	file	Le File .chant
     * @return			Un objet Chant
     */
    public Chant(File file)
	throws IOException, EmptyFileException, ChantFormatException {
	this(file, DEFAULT_BEAUTE);
    }

    /**
     * Constructeur de la classe Chant.
     * @param	fileName	Le nom du fichier .chant
     * @param	k	L'indice de beaute
     * @return			Un objet Chant
     */    
    public Chant(String fileName, int k)
	throws IOException, EmptyFileException, ChantFormatException {
	this(new File(fileName), k);
    }

    /**
     * Constructeur de la classe Chant.
     * @param	fileName	Le nom du fichier .chant
     * @return			Un objet Chant
     */    
    public Chant(String fileName)
	throws IOException, EmptyFileException, ChantFormatException {
	this(new File(fileName));
    }

    /**
     * Getter du tableau de notes soprano du Chant.
     * @return	Un tableau d'int
     */
    public int[]	getSoprano() {
	return tracks[SOPRANO];
    }

    /**
     * Getter du tableau de notes alto du Chant.
     * @return	Un tableau d'int
     */
    public int[]	getAlto() {
	return tracks[ALTO];
    }

    /**
     * Getter du tableau de notes tenor du Chant.
     * @return	Un tableau d'int
     */
    public int[]	getTenor() {
	return tracks[TENOR];
    }

    /**
     * Getter du tableau de notes basse du Chant.
     * @return	Un tableau d'int
     */
    public int[]	getBasse() {
	//return tracks[BASSE];
	return tracks[TENOR];
    }

    /**
     * Getter du titre du Chant.
     * @return	Une String
     */
    public String	getTitre() {
	return titre;
    }

    /**
     * Diverses methodes public pour recuperer des infos sur les
     * notes au format int[].
     */

    /**
     * Renvoie true si l'int de format Chant est une note.
     * @param  note	L'int formate
     * @return		Un boolean
     */
    public static boolean	isNote(int note) {
	return note != REPEAT && note != PAUSE;
    }

    /**
     * Renvoie l'octave d'une note de format Chant.
     * @param  note	La note
     * @return		Son octave
     */
    public static int	getOctave(int note) {
	return note / CONST_OCTAVE;
    }

    /**
     * Renvoie la valeur d'une note a l'octave 1.
     * @param  note	La note
     * @return		Sa valeur a l'octave 1
     */
    public static int	getNoteBase(int note) {
	return note % CONST_OCTAVE;
    }


    /**
     * Methodes private pour lire les fichiers
     * et initialiser les tableaux.
     */
    private static int[]	initSoprano(String[] notes)	
	throws ChantFormatException {
	HashMap<String, Integer>	map
	    = new HashMap<String, Integer>();
	int	soprano[] = new int[getSongLength(notes)];
	String	key = new String();
	int	i = 0;
	int	l;
	
	checkChantFormat(notes);
	map.put("do", DO);
	map.put("ré", RE);
	map.put("re", RE);
	map.put("mi", MI);
	map.put("fa", FA);
	map.put("sol", SOL);
	map.put("la", LA);
	map.put("si", SI);
	map.put("-", PAUSE);
	for (String note : notes) {
	    key = getKey(note);
	    soprano[i] = map.get(key)
		+ CONST_OCTAVE * (getNoteOctave(note) - 1);
	    l = getNoteLength(note);
	    for (int j = 1; j < l; ++j)
		soprano[++i] = REPEAT;
	    ++i;
	}
	return soprano;
    }

    private String[]	chantFileToStringTab(File file)
	throws IOException, EmptyFileException {
	Scanner		fileIn = new Scanner
	    (new BufferedReader(new FileReader(file)));
	String		buff= new String();
	
	if (fileIn.hasNextLine())
	    this.titre = fileIn.nextLine();
	while (fileIn.hasNextLine())
	    buff += fileIn.nextLine() + " ";
	fileIn.close();
	if (buff.isEmpty())
	    throw new EmptyFileException(file.getName());
	return buff.split(" ");
    }
        
    /**
     * Diverses methode private pour recuperer
     * des informations sur les notes a partir du fichier chant
     */
    private static String	getKey(String note) {
	return (note.split(":"))[0].replaceAll("\\d","");
    }

    private static int	getNoteOctave(String note) {
	String	buff = note.split(":")[0];
	
	return (buff.equals("-"))
	    ? 1 : buff.charAt(buff.length() - 1) - 48;
    }

    private static int	getNoteLength(String note) {
	return Integer.parseInt((note.split(":"))[1]);
    }

    private static int	getSongLength(String[] notes) {
	int	r = 0;
	
	for (String s : notes)
	    r += getNoteLength(s);
	return r;
    }
    
    /**
     * Controle du format
     */
    private static void	checkChantFormat(String[] notes)
	throws ChantFormatException {
	for (String s : notes)
	    if (!s.matches(NOTE_FORMAT))
		throw new ChantFormatException(s);
    }
}
