package com.consg.examples.akka.worker;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.consg.examples.akka.manager.ManagerActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Worker {

    public static void main(String[] args) {
        final String port = args.length > 0 ? args[0] : "0";
        final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
                withFallback(ConfigFactory.parseString("akka.cluster.roles = [worker]")).
                withFallback(ConfigFactory.load("application.conf"));

        ActorSystem system = ActorSystem.create("test-cluster", config);
        system.actorOf(Props.create(WorkerActor.class), "workerActor");
    }

}
