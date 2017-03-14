package com.consg.examples.akka.manager;

import akka.actor.*;
import akka.cluster.Cluster;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


public class Manager {

    public static void main(String[] args) {
        final String port = args.length > 0 ? args[0] : "0";

        final Config config = ConfigFactory.parseString("akka.cluster.roles = [manager]")
                .withFallback(ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port))
                .withFallback(ConfigFactory.load("application.conf"));

        final ActorSystem system = ActorSystem.create("test-cluster", config);

        Cluster.get(system).registerOnMemberUp(() -> {
            system.actorOf(Props.create(ManagerActor.class), "managerActor");
            system.actorOf(FromConfig.getInstance().props(), "workersRouter");
        });


    }

}
