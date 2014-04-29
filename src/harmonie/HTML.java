package harmonie;

import java.io.File;

public class HTML {

	public static final String HTML_FILE_SUFFIX = ".html";
	public static final String FILE_NAME = "index" + HTML_FILE_SUFFIX;

	private static final String TITLE = "Harmonisations";

	private static final String TAG_PREFIX = "<";
	private static final String TAG_SUFFIX = ">";
	private static final String TAG_CLOSING = "/";

	private static final String DOCTYPE = "!DOCTYPE html";
	private static final String HTML_TAG = "html";
	private static final String HEAD_TAG = "head";
	private static final String META_TAG = "meta";
	private static final String TITLE_TAG = "title";
	private static final String BODY_TAG = "body";
	private static final String TABLE_TAG = "table";
	private static final String ROW_TAG = "tr";
	private static final String TAB_HEAD_TAG = "th";
	private static final String CELL_TAG = "td";
	private static final String HYPERLINK_TAG = "a";

	private static final String[] HEADER_ROW = { "Nom", "Harmonisations",
			"Midi", "LilyPond" };

	public static String openHeaders() {
		return openTag(DOCTYPE)
				+ openTag(HTML_TAG, new String[][] { { "lang", "fr" } })
				+ openTag(HEAD_TAG)
				+ openTag(META_TAG, new String[][] { { "charset", "utf-8" } },
						true)
				+ openTag(TITLE_TAG)
				+ TITLE
				+ closeTag(TITLE_TAG)
				+ closeTag(HEAD_TAG)
				+ openTag(BODY_TAG)
				+ openTag(TABLE_TAG, new String[][] { { "style",
						"text-align:center;" } });
	}

	public static String closeHeaders() {
		return closeTag(TABLE_TAG) + closeTag(BODY_TAG) + closeTag(HTML_TAG);
	}

	public static String headerRow() {
		String ret = new String("");

		for (String h : HEADER_ROW) {
			ret += tabHeader(h);
		}
		return ret;
	}

	public static String tabRow(String inner) {
		return openTag(ROW_TAG) + inner + closeTag(ROW_TAG);
	}

	public static String openRow() {
		return openTag(ROW_TAG);
	}

	public static String closeRow() {
		return closeTag(ROW_TAG);
	}

	public static String tabHeader(String inner) {
		return openTag(TAB_HEAD_TAG) + inner + closeTag(TAB_HEAD_TAG);
	}

	public static String tabCell(String inner) {
		return openTag(CELL_TAG) + inner + closeTag(CELL_TAG);
	}

	public static String hyperLink(File file, String inner) {
		return openTag(HYPERLINK_TAG,
				new String[][] { { "href", file.getAbsolutePath() } })
				+ inner + closeTag(HYPERLINK_TAG);
	}

	private static String openTag(String tag) {
		return TAG_PREFIX + tag + TAG_SUFFIX;
	}

	private static String openTag(String tag, String[][] atrs) {
		return openTag(tag, atrs, false);
	}

	private static String openTag(String tag, String[][] atrs,
			boolean isSingleTag) {
		String r = new String();

		r = TAG_PREFIX + tag;
		for (String[] atr : atrs) {
			r += " " + atr[0] + "=\"" + atr[1] + "\"";
		}
		return r + ((isSingleTag) ? TAG_CLOSING : "") + TAG_SUFFIX;
	}

	private static String closeTag(String tag) {
		return TAG_PREFIX + TAG_CLOSING + tag + TAG_SUFFIX;
	}
}