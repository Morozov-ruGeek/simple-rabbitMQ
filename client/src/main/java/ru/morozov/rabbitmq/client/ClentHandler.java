package ru.morozov.rabbitmq.client;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ClentHandler {
    private static final String PREFIX = "set_topic ";
    private static final String EXCHANGE_NAME = "news";


    public static void clientListener() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = getTopic();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueChannelName = channel.queueDeclare().getQueue();
        System.out.println("Queue name: " + queueChannelName);

        channel.queueBind(queueChannelName, EXCHANGE_NAME, queueName);

        System.out.println("You`r joining: "+queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] News: '" + message + "'");
        };

        channel.basicConsume(queueChannelName, true, deliverCallback, consumerTag ->
        {
        });
    }

    public static String getTopic() {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        System.out.println("Enter the command: ");
        if (scanner.hasNextLine()) {
            message = scanner.nextLine();
            if (message.startsWith(PREFIX)) {
                message = message.substring(PREFIX.length());
            } else {
                System.out.println("Unknown command");
                getTopic();
            }
        }
        return message;
    }
}
