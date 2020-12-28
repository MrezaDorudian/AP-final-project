package network;

import java.io.*;
import java.net.Socket;

public abstract class ReceiveOrSend implements Runnable{
    boolean isUpdated = false;
    final int PORT_NUMBER = 8585;
    Socket socket;
    InputStream inputStream;
    ObjectInputStream inputObject;
    OutputStream outputStream;
    ObjectOutputStream outputObject;
    Message message;


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
