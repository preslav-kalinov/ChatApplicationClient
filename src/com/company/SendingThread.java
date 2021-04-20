package com.company;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SendingThread extends Thread {

    private DataOutputStream dos;
    private Socket sock;
    private Scanner scn;

    public SendingThread(Socket sock) throws IOException {
        this.dos = new DataOutputStream(sock.getOutputStream());
        this.sock = sock;
        this.scn = new Scanner(System.in);
    }

    @Override
    public void run() {
        try {
            while(true) {
                // reading the message to deliver.
                String msg = scn.nextLine();
                if(msg.equals("/quit")) {
                    System.out.println("You have now been logged out. See you again :)");
                    System.exit(0);
                }
                sendToServer(msg);
            }
        } catch(IOException ioe) {
            System.out.println("Disconnected.");
            System.exit(0);
        }
    }

    private void sendToServer(String msg) throws IOException {
        this.dos.writeUTF(msg);
    }
}
