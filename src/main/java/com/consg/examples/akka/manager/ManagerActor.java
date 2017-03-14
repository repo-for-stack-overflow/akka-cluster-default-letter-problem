package com.consg.examples.akka.manager;

import akka.actor.*;
import akka.routing.FromConfig;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class ManagerActor extends UntypedActor {

    final Cancellable tickTask;
    final UntypedActorContext context = getContext();

    public ManagerActor() {
        FiniteDuration interval = Duration.create(2, TimeUnit.SECONDS);
        tickTask = context
                .system()
                .scheduler()
                .schedule(interval, interval, getSelf(), "tick", context.dispatcher(), null);
    }

    @Override
    public void onReceive(Object message) {
        if (!message.toString().contains("okay")) {
            System.out.println("Manager >> onReceive >> " + message);
            context.actorSelection("/user/workersRouter").tell("Do job!", self());
        }
    }
}
