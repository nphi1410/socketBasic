package com.socket.simple.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Socket Client
 */
public class SocketClient {

    public void connect() {
        Socket socket = null;
        PrintStream ps = null;
        BufferedReader br = null;

        try {
            // Connect to the server
            socket = new Socket("localhost", 3000);
            System.out.println("Connected to the server.");

            // Send a message to the server
            ps = new PrintStream(socket.getOutputStream());
            ps.println("connect duoc chua?");

            // Read the response from the server
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = br.readLine();

            if (response != null) {
                System.out.println("Server answered: " + response);
            } else {
                System.out.println("No response from the server.");
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
                if (br != null) {
                    br.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        SocketClient client = new SocketClient();
        client.connect();
    }
}
