package ru.morozov.rabbitmq.client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ClientApp {
    public static void main(String[] args) {
        ClentHandler clentHandler = new ClentHandler();
        try {
            clentHandler.clientListener();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
