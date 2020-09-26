package com.company;

import org.jetbrains.annotations.NotNull;
import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String @NotNull [] args) throws DummyException, InterruptedException, IOException {
        Connection newConnection = new ConnectionImpl();
        Session newSession = newConnection.createSession(true);
        Destination queue = newSession.createDestination("Message Queue");
        Producer messageSender = newSession.createProducer(queue);

        while(true) {
            FileReader freader = new FileReader(args[0]);
            Scanner scan = new Scanner(freader);
            while (scan.hasNextLine()) {
                messageSender.send(scan.nextLine());
                Thread.sleep(2000);
            }
            freader.close();
        }
//        newSession.close();
//        newConnection.close();
    }
}

