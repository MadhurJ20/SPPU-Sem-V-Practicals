/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program to simulate Go back N and Selective Repeat Modes of Sliding Window Protocol in Peer-to-Peer mode.
*/
import java.io.*;
import java.net.*;
import java.util.*;
class GoBackNARQClient
{
    public static void main(String[] args)throws IOException
    {
        InetAddress addr=InetAddress.getByName("LocalHost");
        System.out.println(addr);
        Socket connection=new Socket(addr,8080);
        BufferedInputStream in=new BufferedInputStream(connection.getInputStream());
        DataOutputStream out=new DataOutputStream(connection.getOutputStream());
        Scanner scr=new Scanner(System.in);
        System.out.println("....Client.....");
        System.out.println("Connect.....");
        System.out.println("Enter the number of frames to be requested by the Server ");
        int c =scr.nextInt();
        out.write(c);
        out.flush();
        System.out.println("Enter the type of trans. Error=1 | No Error=0");
        int choice=scr.nextInt();
        out.write(choice);
        int check=0;
        int i;
        int j;
        if(choice==0)
        {
            for(j=0;j<c;++j)
            {
                i=in.read();
                System.out.println("Received frame number "+i);
                System.out.println("Sending acknowledgement for frame number "+i);
                out.write(i);
                out.flush();
            }
            out.flush();
        }
        else
        {
            for(j=0;j<c;++j)
            {
                i=in.read();
                if(i==check)
                {
                    System.out.println("Received frame number "+i);
                    System.out.println("Sending acknowledgement for frame number "+i);
                    out.write(i);
                    ++check;
                }
                else
                {
                    --j;
                    System.out.println("Discarded frame number "+i);
                    System.out.println("Sending Negative Acknowledgement");
                    out.write(-1);
                }
                out.flush();
            }
        }
        in.close();
        out.close();
        System.out.println("Quitting");
    }
}