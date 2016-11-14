package edu.rit.swen342;

import akka.actor.ActorRef;

public class Configure {
	public final String file_name;
	public final ActorRef collection_actor_ref;

	public Configure(String file_name, ActorRef collection_actor_ref) {
		this.file_name = file_name;
		this.collection_actor_ref = collection_actor_ref;
	}
}
