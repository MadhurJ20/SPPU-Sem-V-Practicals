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
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class FileServer
{ 
 public static void main(String[] args) throws Exception 
{
 //Initialize Sockets
 ServerSocket ssock = new ServerSocket(5000);
 Socket socket = ssock.accept();
 //The InetAddress specification
 InetAddress IA = InetAddress.getByName("localhost"); 
 
 //Specify the file
 File file = new File("./Test.txt");
 FileInputStream fis = new FileInputStream(file);
 BufferedInputStream bis = new BufferedInputStream(fis); 
 //Get socket's output stream
 OutputStream os = socket.getOutputStream();
 //Read File Contents into contents array 
 byte[] contents;
 long fileLength = file.length(); 
 long current = 0;
 long start = System.nanoTime();
 while(current!=fileLength){ 
 int size = 10000;
 if(fileLength - current >= size)
 current += size; 
 else{ 
 size = (int)(fileLength - current); 
 current = fileLength;
 } 
 contents = new byte[size]; 
 bis.read(contents, 0, size); 
 os.write(contents);
 System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!");
 } 
 os.flush(); 
 //File transfer done. Close the socket connection
 socket.close();
 ssock.close();
 System.out.println("File sent succesfully!");
 }
}