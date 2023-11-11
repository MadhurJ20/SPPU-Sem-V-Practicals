/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program to demonstrate Sub-netting and find subnet masks.
*/
import java.util.Scanner;
import java.net.InetAddress;
class Subnet
{
public static void main(String[] args)
{
Scanner sc= new Scanner(System.in);
System.out.print("Enter IP Address ");
String ip=sc.nextLine();
//----Split the Ip Address-------
String[] split_ip = ip.split("\\.");
//----- Converting the Ip Address to binary----
String[] split_bip = new String[4];
String bip = "";
for(int i=0;i<4;i++)
{
split_bip[i]=appendZeroes(Integer.toBinaryString(Integer.parseInt(split_ip[i])));
bip+=split_bip[i];
}
System.out.println("The binary IP Address is "+bip);
//----- Finding the Subent mask-----
System.out.println("Enter the number of addresses ");
int n=sc.nextInt();
int bits=(int)Math.ceil(Math.log(n)/Math.log(2));
System.out.println("The number of bits required "+bits);
int mask=32-bits;
int total_address=(int)Math.pow(2,bits);
System.out.println("Subnet mask is "+mask);
//---- Finding the first and last address----
//---- First address Calculation--------
int[] fbip =new int[32];
for(int i=0;i<32;i++)
{
//Convert to the character 1,0 to integer 1,0
fbip[i]=(int)bip.charAt(i)-48;
}
for(int i=31;i>31-bits;i--)
{
//Get first address by anding the last bits with 0
fbip[i] &=0;
}
String[] fip ={"","","",""};
for(int i=0;i<32;i++)
{
fip[i/8]= fip[i / 8] + fbip[i];
}
int first_offset=0;
int[] ipAddr =new int[4];
    System.out.println("Group 1 \nThe First Address is ");
for(int i=0;i<4;i++)
{
System.out.print(ipAddr[i]=first_offset=Integer.parseInt(fip[i],2));
if(i!=3)
System.out.print(".");
}
System.out.println();
//--- Last address Calculation----
int[] lbip =new int [32];
for(int i=0;i<32;i++)
{
// Convert the character 1,0 to integer 1,0
lbip[i]=(int)bip.charAt(i)-48;
}
for(int i=31;i>31-bits;i--)
{
// Get last address by oring with last bits with 1
lbip[i]|= 1;
}
String[] lip ={"","","",""};
for(int i=0;i<32;i++)
{
lip[i/8]= lip[i / 8] + lbip[i];
}
int[] ipLast =new int[4];
System.out.println("The Last Address is ");
for(int i=0;i<4;i++)
{
System.out.print(ipLast[i]=Integer.parseInt(lip[i],2));
if(i!=3)
System.out.print(".");
}
System.out.println();
System.out.println("How many subnets do you wish to form? ");
int scount=sc.nextInt();
for(int j=1;j<scount;j++)
{
System.out.println(" GROUP "+ (j+1)+" FIRST ADDRESS: ");
for(int i=0;i<4;i++)
{
if(i<3)
{
System.out.print(ipAddr[i]+".");
}
else
System.out.println(ipAddr[i]=ipAddr[i]+total_address);
}
System.out.println(" GROUP "+ (j+1)+" LAST ADDRESS: ");
for(int i=0;i<4;i++)
{
if(i<3)
{
System.out.print(ipLast[i]+".");
}
else
System.out.println(ipLast[i]=ipLast[i]+total_address);
}
System.out.println();
}
try
{
System.out.println("Enter an IP address to ping: ");
Scanner s=new Scanner(System.in);
String ip_add=s.nextLine();
InetAddress inet = InetAddress.getByName(ip_add);
if(inet.isReachable(5000))
{
System.out.println("This IP Address is reachable "+ip_add);
}
else
{
System.out.println("This IP Address is not reachable "+ip_add);
}
}
catch( Exception e)
{
System.out.println("Exception: "+e.getMessage());
}
}
static String appendZeroes(String s)
{
String temp= "00000000";
return temp.substring(s.length())+ s;
}
}