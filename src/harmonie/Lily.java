package harmonie;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Lily {
    
    /**
     * Cette classe contiens les outils pour ecrire un fichier Lilypond.
     */

    /**
     * Constantes public : le nom de l'option et le nombre d'argument
     * qu'elle prend en parametre
     */
    public static final String	OPTION = "-ly";
    public static final int	NB_ARGUMENTS = 2;

    /**
     * Constantes private
     */
    private static final String	FILE_SUFFIX = ".ly";

    private static final String INSTRU_SOPRA = "Soprano";
    private static final String INSTRU_ALTO = "Alto";
    private static final String INSTRU_TENOR = "Tenor";
    private static final String INSTRU_BASSE = "Basse";

    private static final String TREBLE = "treble";
    private static final String BASSE = "bass";

    /**
     * La methode appelee par la classe CLI
     * @param	c	L'objet chant cree par Beaute. Null si Beaute n'a
     *			pas ete appelee
     * @param	args	Les arguments entrees en ligne de commande
     * @return		La valeur a ajoute a l'iterateur pour
     *			sauter les arguments de l'option
     */
    public static void	exec(Chant c, String[] args) 
	throws OptionsFormatException {
	if (args.length - 1 < NB_ARGUMENTS
	    || CLI.isOption(args[1]) || CLI.isOption(args[2]))
	    throw new OptionsFormatException(OPTION, NB_ARGUMENTS);
	if (c == null)
	    write(args[1], args[2]);
	else
	    write(c, new File(args[2]));
	c = null;
    }

    /**
     * La methode appelee par la classe Dossier
     * @param	parent	Le dossier qui contiendra les fichiers a ecrire
     * @param	c	Le chant a ecrire
     */
    public static File	exec(File parent, Chant c) {
	return write(c,
		     new File(parent, c.getTitre() + FILE_SUFFIX));
    }
    
    /**
     * Les methodes d'ecriture de fichiers Midi
     */
    private static File write(String fileIn, String fileOut) {
	try {
	    return write(new Chant(fileIn), new File(fileOut));
	}
	catch (EmptyFileException | ChantFormatException
	       | IOException e) {
	    System.out.println(e);
	}
	return null;
    }
    
    private static File write(Chant c, File out) {
	try {
	    BufferedWriter	s_out = new BufferedWriter
		(new FileWriter(out));
	    writeHeader(s_out, c.getTitre());
	    writeTrack(s_out, c.getSoprano(), INSTRU_SOPRA, TREBLE);
	    /*
	      writeTrack(s_out, c.getAlto(), INSTRU_ALTO, TREBLE);
	      writeTrack(s_out, c.getTenor(), INSTRU_TENOR, TREBLE);
	      writeTrack(s_out, c.getBasse(), INSTRU_BASSE, BASSE);
	    */
	    s_out.close();
		return out;
	}
	catch (IOException e) {
	    System.out.println(e);
	}
	return null;
    }

    /**
     * Ecriture d'un track unique
     */
    private static void	writeTrack(BufferedWriter out,
				   int[] t, String instru, String clef)
    throws IOException {
	String	note;

	out.write("% " + instru);
	out.write("\\new Staff {");
	out.write("\\set Staff.instrumentName = #\"" + instru + "\"");
	out.write("\\clef " + clef);
	out.write("\\relative " + "c" + "{");
	for (int i = 0; i < t.length;) {
	    /*
	    if (Chant.isNote(t[i])) {
		note = noteToMidi(t[i]);
		track.add(noteOn(note, i++, canal));
		for (; i < t.length && t[i] == Chant.REPEAT; ++i);
		track.add(noteOff(note, i, canal));
	    }
	    else
		++i;
	    */
	    if (Chant.isNote(t[i]))
		note = noteToLily(t[i++]);
	    else
		++i;
	}
	out.write("}}");
    }

    private static void	writeHeader(BufferedWriter out, String titre)
	throws IOException {
	out.write("\\header{");
	out.write("title = \"" + titre + "\"}");
	out.write("\\new ChoirStaff<<");
    }

    private static String	noteToLily(int note) {
	return "a";
    }

    private static boolean	isLowerThanFa(int note) {
	return Chant.getNoteBase(note) >= Chant.DO
	    && Chant.getNoteBase(note) <= Chant.MI;
    }
}
