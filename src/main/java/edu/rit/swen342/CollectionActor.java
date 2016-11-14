package edu.rit.swen342;

import akka.actor.UntypedActor;

public class CollectionActor extends UntypedActor {
	private int files_to_scan = 0;

	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof FileCount) {
			files_to_scan = ((FileCount) msg).file_count;
		} else if (msg instanceof Found) {
			files_to_scan--;

			Found found = (Found) msg;
			String file_name = (found.file_name == null) ? "STDIN" : found.file_name;

			System.out.println(file_name + ":");
			found.matching_lines.stream().forEach(System.out::println);
		} else {
			unhandled(msg);
		}

		if (files_to_scan <= 0) {
			getContext().system().terminate();
		}
	}

}
