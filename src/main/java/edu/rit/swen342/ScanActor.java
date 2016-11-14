package edu.rit.swen342;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class ScanActor extends UntypedActor {
	private final Pattern pattern;

	public ScanActor(Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof Configure) {
			// Scan the file
			String file_name = ((Configure) msg).file_name;
			ActorRef collection_actor_ref = ((Configure) msg).collection_actor_ref;
			InputStream input = (file_name == null) ? System.in : new FileInputStream(file_name);
			List<String> matching_lines = Grep.grep(pattern, input);

			// Send the Found message
			collection_actor_ref.tell(new Found(file_name, matching_lines), getContext().self());

			getContext().stop(getContext().self());
		} else {
			unhandled(msg);
		}
	}

}
