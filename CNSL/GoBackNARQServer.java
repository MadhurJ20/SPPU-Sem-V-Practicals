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

class GoBackNARQServer
{
    public static void main(String[] args)throws IOException
    {
        System.out.println("....Server.....");
        System.out.println("Waiting for Connection.....");
        InetAddress addr=InetAddress.getByName("LocalHost");
        ServerSocket ss=new ServerSocket(8080);
        Socket client = new Socket();
        client=ss.accept();
        BufferedInputStream in=new BufferedInputStream(client.getInputStream());
        DataOutputStream out=new DataOutputStream(client.getOutputStream());
        System.out.println("Received request for sending frames");
        int p =in.read();
        boolean[] f =new boolean[p];
        int pc=in.read();
        System.out.println("Sending .....");
        if(pc==0)
        {
            for(int i=0;i<p;++i)
            {
                System.out.print("Sending frame number "+i); ////////
                out.write(i);
                out.flush();
                System.out.println("Waiting for acknowledgement ");
                try
                {
                    Thread.sleep(7000);
                }
                catch(Exception e){}
                int a=in.read();
                System.out.println("Received acknowledgement for frame "+i+" as "+a);
            }
            out.flush();
        }
        else
        {
            for(int i=0;i<p;++i)
            {
                if(i==2)
                {
                    System.out.println("Sending Frame number "+i);
                }
                else
                {
                    System.out.println("Sending Frame number "+i);
                    out.write(i);
                    out.flush();
                    System.out.println("Waiting for acknowledgement ");
                    try
                    {
                        Thread.sleep(7000);
                    }
                    catch(Exception e){}
                    int a=in.read();
                    if(a!=255)
                    {
                        System.out.println("Received acknowledgement for frame "+i+" as "+a);
                        f[i]=true;
                    }
                } // end of inner else
            } // end of for
            for(int a=0;a<p;++a)
            {
                if(!f[a])
                {
                    System.out.println("Resending frame "+a);
                    out.write(a);
                    out.flush();
                    System.out.println("Waiting for acknowledgement ");
                    try
                    {
                        Thread.sleep(5000);
                    }
                    catch(Exception e){}
                    int b=in.read();
                    if(a!=255)
                    {
                        System.out.println("Received acknowledgement for frame "+a+" as "+b);
                        f[a]=true;
                    }
                } // end of inner else
                out.flush();
            } // end of for
            in.close();
            out.close();
            client.close();
            ss.close();
            System.out.println("Quitting");
        }
    }
}