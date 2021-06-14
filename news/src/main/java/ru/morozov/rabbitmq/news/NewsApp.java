package ru.morozov.rabbitmq.news;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewsApp {
    public static void main(String[] args) {
        try {
            ScannerSender scannerSender = new ScannerSender();
            scannerSender.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
