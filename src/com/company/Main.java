package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // getting the ip address
            InetAddress ip = InetAddress.getByName("localhost");

            // making the connection
            Socket sock = new Socket(ip, 8040);

            System.out.println("\n----~~~~ JustChat Application ~~~~----");
            System.out.println("\t <<<Made by Preslav Kalinov>>>");
            randomQuotes();
            SendingThread st = new SendingThread(sock);
            ReceivingThread rt = new ReceivingThread(sock);
            st.start();
            rt.start();
            st.join();
            rt.join();
        } catch (IOException | InterruptedException ioe) {
            System.out.println("Disconnected.");
        }
    }

    private static void randomQuotes() throws IOException {
        FileInputStream fis= new FileInputStream("quotes.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        ArrayList<String> list = new ArrayList<>();
        Random rand = new Random();
        String line;
        while((line = br.readLine()) != null)
            list.add(line);
        int randomIndex = rand.nextInt(list.size());
        System.out.println("\n----------Quote of the Enter----------");
        System.out.println(list.get(randomIndex));
    }
}
