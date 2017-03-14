package com.consg.examples.akka.worker;

import akka.actor.UntypedActor;

public class WorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Worker >> onReceive >> " + message);
        getSender().tell("okay!", self());
    }

}
