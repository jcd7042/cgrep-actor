package edu.rit.swen342;

import akka.actor.UntypedActor;

public class DummyActor extends UntypedActor {
	public static enum Msg {
		GREET, DONE;
	}

	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg == Msg.GREET) {
			System.out.println("Greetings from a DummyActor!");
			getSender().tell(Msg.DONE, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
