package edu.rit.swen342;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class CGrepActor extends UntypedActor {

	@Override
	public void preStart() {
		ActorRef dummy = getContext().actorOf(Props.create(DummyActor.class), "dummy");
		dummy.tell(DummyActor.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg == DummyActor.Msg.DONE) {
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}
	}

}
