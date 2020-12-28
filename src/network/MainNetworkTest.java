package network;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;

public class MainNetworkTest {


    public static void main(String[] args) throws IOException, InterruptedException {

//
//        FileInputStream f = new FileInputStream("E:\\2.mp3");
//        byte[] music = f.readAllBytes();
//
//        Message message = new Message("10", "arash", "arash", music, 10);


        System.out.println(InetAddress.getLocalHost());
        NetworkManager networkManager = new NetworkManager();
//        networkManager.startNetwork();
        System.out.println(networkManager.amIServer);

    }
}
