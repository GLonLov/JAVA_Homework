package com.company;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws DummyException, InterruptedException {
    Connection newConnection = new ConnectionImpl();
    Session newSession = newConnection.createSession(true);
    Destination queue = newSession.createDestination("Message Queue");
    Producer messageSender = newSession.createProducer(queue);

    ArrayList<String> messages = new ArrayList<>();

    messages.add("Раз");
    messages.add("Два");
    messages.add("Три");

        for (int i = 0; i < messages.size(); i++) {
            messageSender.send(messages.get(i));
            Thread.sleep(2000);
        }
    newSession.close();
    newConnection.close();
    }
}
