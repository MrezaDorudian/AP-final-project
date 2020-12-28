package network;

import java.io.*;
import java.net.Socket;

/**
 * this class represents a client with field Socket and int for the port
 * and implements runnable, it try to reach a client server than given
 */

public class Client implements Runnable{
    private Socket socket;
    private final int PORT_NUMBER = 2222;
    private String SERVER_IP = "localhost";

    /**
     * override the method run of Runnable and making our socket to connect to the sever
     */

    @Override
    public void run() {
        try {
            socket = new Socket("", PORT_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
