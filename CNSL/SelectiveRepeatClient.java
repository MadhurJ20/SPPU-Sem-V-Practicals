/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program to simulate Go back N and Selective Repeat Modes of Sliding Window Protocol in Peer-to-Peer mode.
*/
import java.lang.System;
import java.net.*;
import java.io.*;
import java.util.Random;
public class SelectiveRepeatClient {
    static Socket connection;
    public static void main(String[] a) throws SocketException {
        try {
            int[] v = new int[10];
            int n = 0;
            Random rands = new Random();
            int rand = 0;
            InetAddress addr = InetAddress.getByName("Localhost");
            System.out.println(addr);
            connection = new Socket(addr, 8011);
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            DataInputStream in = new DataInputStream(
                    connection.getInputStream());
            int p = in.read();
            System.out.println("No of frame is:" + p);
            for (int i = 0; i < p; i++) {
                v[i] = in.read();
                System.out.println(v[i]);
//g[i] = v[i];
            }
            rand = rands.nextInt(p);//FRAME NO. IS RANDOMLY GENERATED
            v[rand] = -1;
            for (int i = 0; i < p; i++)
            {
                System.out.println("Received frame is " + v[i]);
            }
            for (int i = 0; i < p; i++)
                if (v[i] == -1) {
                    System.out.println("Request to retransmit from packet number "
                            + (i+1));
                    n = i;
                    out.write(n);
                    out.flush();
                }
            System.out.println();
            v[n] = in.read();
            System.out.println("Received frame is " + v[n]);
            System.out.println("Quitting");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}