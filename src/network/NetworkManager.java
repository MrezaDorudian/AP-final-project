package network;

import java.io.IOException;
import java.util.ArrayList;

/**
 * this class is used for managing network :
 * based on it is server or not(by default it is client
 * until we make one computer to server in run time) it
 * starts their threads and make them work
 */

public class NetworkManager{

    ArrayList<String> friendsIPs;
    boolean amIServer = false;
    public static ArrayList<Message> serverMessages = new ArrayList<>();
    public static ArrayList<Message> clientMessages = new ArrayList<>();
    /*we need access on this tow fields and we make them static and
    make them new them as soon as we declare them*/
    private Server server;
    private Client client;
    Thread serverThread;

    public NetworkManager() {
        friendsIPs = new ArrayList<>();
    }

    public void run() {
        if (amIServer) {
            try {
                server = new Server();
            } catch (IOException e) {
                e.printStackTrace();
            }
            serverThread = new Thread(server);
            serverThread.start();
        } else {
            client = new Client();
            Thread clientThread = new Thread(client);
            clientThread.start();
        }
    }
}