/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program of using TCP socket for wired network following:
a. Say Hello to Each other
b. File transfer
c. Calculator(Arithmetic)
*/
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
public class FileClient
{ 
 public static void main(String[] args) throws Exception{
 //Initialize socket
 Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
 byte[] contents = new byte[10000];
 //Initialize the FileOutputStream to the output file's full path.
 FileOutputStream fos = new FileOutputStream("d:\\Test1.txt");
 BufferedOutputStream bos = new BufferedOutputStream(fos);
 InputStream is = socket.getInputStream();
 //No of bytes read in one read() call
 int bytesRead = 0; 
 while((bytesRead=is.read(contents))!=-1)
 bos.write(contents, 0, bytesRead); 
 bos.flush(); 
 socket.close(); 
 System.out.println("File saved successfully!");
 }
}