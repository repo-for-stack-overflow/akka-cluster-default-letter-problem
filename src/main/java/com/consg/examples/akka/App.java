package com.consg.examples.akka;

import com.consg.examples.akka.manager.Manager;
import com.consg.examples.akka.worker.Worker;

public class App {
    public static void main(String[] args) {
        Worker.main(new String[]{"22000"});
        Worker.main(new String[]{"22001"});
        Manager.main(new String[]{"2551"});
    }
}