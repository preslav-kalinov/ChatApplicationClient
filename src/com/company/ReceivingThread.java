package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceivingThread extends Thread {

    private DataInputStream dis;
    private Socket sock;

    public ReceivingThread(Socket sock) throws IOException {
        this.sock = sock;
        this.dis = new DataInputStream(sock.getInputStream());
    }

    @Override
    public void run() {
        try {
            while(true) {
                System.out.println(readFromServer());
            }
        } catch(IOException e) {
            System.out.println("Disconnected.");
            System.exit(0);
        }
    }

    private String readFromServer() throws IOException {
        return this.dis.readUTF();
    }

}
