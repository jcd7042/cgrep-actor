package edu.rit.swen342;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Grep provides static methods to search a sequence of lines for those that
 * match a given pattern.
 *
 */
public class Grep {
	public static List<String> grep(Pattern pattern, InputStream input_stream) {
		return grep(pattern, new BufferedReader(new InputStreamReader(input_stream)).lines());
	}

	public static List<String> grep(Pattern pattern, Stream<String> line_stream) {
		return grep(pattern, line_stream.iterator());
	}

	/**
	 * Return the list of lines, prefixed by the line number and a space, that
	 * match the given pattern.
	 * 
	 * @param pattern
	 *            Pattern that determines which lines match.
	 * @param line_iterator
	 *            Iterator of input lines
	 * @return List of lines that match the pattern prefixed by a line number
	 *         and space.
	 */
	public static List<String> grep(Pattern pattern, Iterator<String> line_iterator) {
		List<String> matches = new ArrayList<>();
		long line_number = 0;
		while (line_iterator.hasNext()) {
			line_number++;
			String line = line_iterator.next();

			if (pattern.matcher(line).matches()) {
				matches.add(Long.toString(line_number) + " " + line);
			}
		}

		return matches;
	}
}
