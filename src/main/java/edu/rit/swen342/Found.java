package edu.rit.swen342;

import java.util.List;

public class Found {
	public final String file_name;
	public final List<String> matching_lines;

	public Found(String file_name, List<String> matching_lines) {
		this.file_name = file_name;
		this.matching_lines = matching_lines;
	}
}
