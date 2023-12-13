package org.example;

import java.awt.*;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class ServerMain {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ip = localHost.getHostAddress();
            int port = 55;

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port + " and IP " + ip);

            Robot robot = new Robot();

            while (true) {
                Socket socket = null;

                try {
                    socket = serverSocket.accept();
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                    Message message = (Message) objectInputStream.readObject();

                    System.out.println("Client sent: " + message.getX() + ", " + message.getY());
                    robot.mouseMove((int)message.getX(), (int)message.getY());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
