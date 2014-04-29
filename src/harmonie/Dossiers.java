package harmonie;

import Exceptions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Dossiers {
	/**
	 * cette classe contiens tous les outils pour traiter les fichiers contenus
	 * dans le dossier donne en parametre.
	 */

	public static final String OPTION = "-w";
	public static final int NB_ARGUMENTS = 2;

	/**
	 * La methode appelee par la classe interface CLI
	 * 
	 * @param args
	 *            Les arguments entrees en ligne de commande
	 */
	public static void exec(String[] args) throws OptionsFormatException {
		File dirIn;
		File dirOut;

		if (args.length - 1 < NB_ARGUMENTS || CLI.isOption(args[1])
				|| CLI.isOption(args[2]))
			throw new OptionsFormatException(OPTION, NB_ARGUMENTS);
		dirIn = new File(args[1]);
		dirOut = new File(args[2]);
		try {
			controlDir(dirIn, dirOut);
			writeFiles(dirIn, dirOut);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Methode parcourant les fichiers contenus dans le dossier dirIn pour
	 * ecrire les resultats demandes dans dirOut
	 */
	private static void writeFiles(File dirIn, File dirOut) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(dirOut,
				HTML.FILE_NAME)));

		out.write(HTML.openHeaders());
		out.write(HTML.tabRow(HTML.headerRow()));
		for (File child : dirIn.listFiles()) {
			if (child.isFile()) {
				try {
					Chant c = new Chant(child);
					out.write(HTML.openRow());
					out.write(HTML.tabCell(c.getTitre()));
					out.write(HTML.tabCell(c.writeNbHarmo()));
					out.write(HTML.tabCell(HTML.hyperLink(Midi.exec(dirOut, c),
							"midi")));
					out.write(HTML.tabCell(HTML.hyperLink(Lily.exec(dirOut, c),
							"lily")));
				} catch (EmptyFileException | ChantFormatException
						| IOException e) {
					System.out.println(e);
				}
				out.write(HTML.closeRow());
			}
		}
		out.write(HTML.closeHeaders());
		out.close();
	}

	/**
	 * Controle de la validite des dossiers
	 */
	private static void controlDir(File dirIn, File dirOut) throws IOException {
		if (!dirIn.exists())
			throw new IOException(dirIn.getName() + " n'existe pas");
		if (!dirIn.isDirectory())
			throw new IOException(dirIn.getName() + " n'est pas un dossier");
		if (!dirOut.exists())
			dirOut.mkdir();
		if (!dirOut.isDirectory())
			throw new IOException(dirOut.getName() + " est un fichier");
	}
}