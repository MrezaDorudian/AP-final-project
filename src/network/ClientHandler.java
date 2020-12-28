package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * this class is a client handler that iterate over sockets
 * that get from the server and extract the date from them
 * in a true while and then wait for one minute then clear
 * the list and update it.
 * this class also implements Runnable
 */
public class ClientHandler implements Runnable {
    private Message inputMessage;
    static Message outputMessage;//need access all over here
    private ObjectInputStream inputObject;
    private ObjectOutputStream outputObject;

    /**
     * we override the run method of Runnable and with information given on top
     */

    @Override
    public void run() {
        while (true) {
            for (int i = 0 ; i < Server.sockets.size(); i++) {
                try {
                    inputObject = new ObjectInputStream(Server.sockets.get(i).getInputStream());
                    inputMessage = (Message) inputObject.readObject();
                    NetworkManager.clientMessages.add(inputMessage);
                    outputObject = new ObjectOutputStream(Server.sockets.get(i).getOutputStream());
                    if (outputMessage != null) {
                        outputObject.writeObject(outputMessage);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.currentThread().wait(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            NetworkManager.clientMessages.clear();
            //then messages will be updated
        }
    }
}
