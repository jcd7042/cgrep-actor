package edu.rit.swen342;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class CGrep {
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.err.println("Usage: java CGrep PATTERN [FILE]...");
			System.err.println("Search for PATTERN in each FILE or standard input.");
			System.err.println(
					"Pattern is a regular expression defined in the documentation\n  for java.util.regex.Pattern");
			System.exit(1);
			return;
		}

		final String regex = args[0];
		final Pattern pattern;

		try {
			pattern = Pattern.compile(regex);
		} catch (PatternSyntaxException e) {
			System.err.println("Failed to compile the regular expression pattern \"" + regex + "\"");
			System.exit(1);
			return;
		}

		ActorSystem system = ActorSystem.create();

		// Start CollectionActor
		ActorRef collection_actor_ref = system.actorOf(Props.create(CollectionActor.class));

		// Send the CollectionActor the FileCount message
		collection_actor_ref.tell(new FileCount(args.length - 1), null);

		// for each file, create a ScanActor and send it a Configure message
		for (int i = 1; i < args.length; i++) {
			String file_name = args[i];
			ActorRef scanActorRef = system.actorOf(Props.create(ScanActor.class, pattern));
			scanActorRef.tell(new Configure(file_name, collection_actor_ref), null);
		}
	}
}