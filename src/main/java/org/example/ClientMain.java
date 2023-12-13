package org.example;

import java.awt.*;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        Message message = new Message(0, 0);

        while(true) {
            try {
                Socket socket = new Socket("10.0.0.110", 55);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());


                try {
                    Point location = MouseInfo.getPointerInfo().getLocation();
                    message.setX(location.getX());
                    message.setY(location.getY());
                } catch (Exception e) {
                    message.setX(0);
                    message.setY(0);
                }

                objectOutputStream.writeObject(message);

                System.out.println("Sent: " + message.getX() + ", " + message.getY());

                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
