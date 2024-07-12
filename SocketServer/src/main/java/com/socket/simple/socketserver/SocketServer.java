package com.socket.simple.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Socket Server
 */
public class SocketServer {

    public void server() {
        ServerSocket server = null;
        Socket socket = null;
        BufferedReader br = null;
        PrintStream ps = null;

        try {
            System.out.println("Server is ready...");
            server = new ServerSocket(3000);

            while (true) { // Handle multiple clients
                socket = server.accept();
                System.out.println("Client connected.");

                // Read request from client
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = br.readLine();

                if (request != null) {
                    System.out.println("Received from client: " + request);

                    // Send response to client
                    ps = new PrintStream(socket.getOutputStream());
                    ps.println("connect duoc roi");
                } else {
                    System.out.println("Failed to receive message from client.");
                }

                // Close client connection
                socket.close();
                br.close();
                ps.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        SocketServer s = new SocketServer();
        s.server();
    }
}
