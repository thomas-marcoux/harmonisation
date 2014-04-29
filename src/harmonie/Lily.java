package harmonie;

import Exceptions.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Lily {

	/**
	 * Cette classe contiens les outils pour ecrire un fichier Lilypond.
	 */

	public static final String OPTION = "-ly";
	public static final int NB_ARGUMENTS = 2;

	private static final String FILE_SUFFIX = ".ly";

	private static final String INSTRU_SOPRA = "Soprano";
	private static final String INSTRU_ALTO = "Alto";
	private static final String INSTRU_TENOR = "Tenor";
	private static final String INSTRU_BASSE = "Basse";

	private static final String TREBLE = "treble";
	private static final String BASSE = "bass";

	private static final String VERSION = "2.14.2";

	/**
	 * La methode appelee par la classe CLI
	 * @param args
	 *            Les arguments entrees en ligne de commande
	 */
	public static void exec(String[] args, int k) throws OptionsFormatException {
		if (args.length - 1 < NB_ARGUMENTS || CLI.isOption(args[1])
				|| CLI.isOption(args[2]))
			throw new OptionsFormatException(OPTION, NB_ARGUMENTS);
		write(args[1], args[2], k);
	}

	/**
	 * La methode appelee par la classe Dossier
	 * 
	 * @param parent
	 *            Le dossier qui contiendra les fichiers a ecrire
	 * @param c
	 *            Le chant a ecrire
	 */
	public static File exec(File parent, Chant c) {
		return write(c, new File(parent, c.getTitre() + FILE_SUFFIX));
	}

	/**
	 * Les methodes d'ecriture de fichiers Midi
	 */
	private static File write(String fileIn, String fileOut, int k) {
		try {
			return write(new Chant(fileIn, k), new File(fileOut));
		} catch (EmptyFileException | ChantFormatException | IOException e) {
			System.out.println(e);
		}
		return null;
	}

	private static File write(Chant c, File out) {
		try {
			BufferedWriter s_out = new BufferedWriter(new FileWriter(out));
			writeHeader(s_out, c.getTitre());
			writeStaff(s_out, c.getSoprano(), INSTRU_SOPRA, TREBLE, 2);
			writeStaff(s_out, c.getAlto(), INSTRU_ALTO, TREBLE, 2);
			writeStaff(s_out, c.getTenor(), INSTRU_TENOR, TREBLE, 1);
			writeStaff(s_out, c.getBasse(), INSTRU_BASSE, BASSE, 0);
			s_out.write(">>");
			s_out.close();
			return out;
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Ecriture d'un staff unique
	 */
	private static char getNote(char note, int last_note, int cur_note) {
		return (char) (note - (last_note - cur_note));
	}

	private static String writeNote(char note) {
		String ret = String.valueOf(note);

		while (note > 'g') {
			note -= ('g' - 'a' + 1);
			ret += "'";
		}
		while (note < 'a') {
			note += ('g' - 'a');
			ret += ",";
		}
		return String.valueOf(note) + ret.substring(1);
	}

	private static void writeStaff(BufferedWriter out, int[] t, String instru,
			String clef, int relat) throws IOException {
		char note;
		int last_note;
		int l;
		int last_l;

		note = firstNote(t);
		last_note = 0;
		last_l = 1;
		out.write(staffHead(instru, clef, relat));
		out.write("{");
		for (int i = 0; i < t.length;) {
			l = 1;
			if (Chant.isNote(t[i])) {
				if (i > 0)
					note = getNote(note, last_note, t[i]);
				last_note = t[i];
			} else
				note = 'r';
			out.write(writeNote(note));
			++i;
			for (; i < t.length && t[i] == Chant.REPEAT; ++i, ++l)
				;
			out.write(noteLength(last_l, l) + " ");
			last_l = l;
		}
		out.write("}}");
	}

	private static char firstNote(int[] track) {
		char base = 'c';

		base += Chant.getNoteBase(track[0]);
		return base;
	}

	private static String staffHead(String instru, String clef, int relat)
			throws IOException {
		String r = "\\new Staff {" + "\\set Staff.instrumentName = #\""
				+ instru + "\"" + "\\clef " + clef + "\\relative " + "c";
		for (int i = 0; i < relat; ++i)
			r += "'";
		return r;
	}

	private static void writeHeader(BufferedWriter out, String titre)
			throws IOException {
		out.write("\\version \"" + VERSION + "\"");
		out.write("\\header{");
		out.write("title = \"" + titre + "\"}");
		out.write("\\new ChoirStaff<<");
	}

	private static String noteLength(int last, int l) {
		if (l != last && l == 1)
			return String.valueOf(4);
		if (l != last && l == 3)
			return String.valueOf("2.");
		if (l != last)
			return String.valueOf(l);
		return "";
	}
}
