package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * this class represents the server , that has the same port of client to connect to each other
 * it has list of sockets to give it to ClientHandler to deal with them and it implements Runnable
 */

public class Server implements Runnable {

    private final int PORT_NUMBER = 2222;
    private ClientHandler ch = new ClientHandler();
    private Thread chThread;
    static ArrayList<Socket> sockets = new ArrayList<>();//need access in Client handler
    private ServerSocket server;
    private Socket socket;

    /**
     * the constructor makes the server ready for a client to accept to it
     * @throws IOException
     */

    public Server() throws IOException {
        super();
        server = new ServerSocket(PORT_NUMBER);
    }

    /**
     * this override run method is because the class implements Runnable
     * and in this method in a true while server waits for clients to connect
     * and saves their sockets in a list to give to client handler to deal with
     * them and extract data and get data from them
     * it also has a thread inside that its Runnable is ClientHandler
     * and it,s started as the first client reaches it
     */

    @Override
    public void run() {
        while (true) {
            try {
                socket = server.accept();
                Server.sockets.add(socket);
                chThread = new Thread(ch);
                chThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
