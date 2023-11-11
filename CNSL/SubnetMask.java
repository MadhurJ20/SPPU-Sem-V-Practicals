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
public class SubnetMask {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String ip;
        int mask,defaultmask=0;
        System.out.println("Enter IP Address ");
        ip = scan.nextLine();
        System.out.println("Enter Mask ");
        mask = scan.nextInt();
        String[] splitip = ip.split("\\.");
        System.out.println(splitip[0]+" "+splitip[1]+" "+splitip[2]+" "+splitip[3]+" ");
        int first = Integer.parseInt(splitip[0]);
        if(first>=0&&first<=127){
            System.out.println("CLASS A");
            defaultmask = 8;
        }
        else if(first>127&&first<=191){
            System.out.println("CLASS B");
            defaultmask=16;
        }
        else if(first>191&&first<=223){
            System.out.println("CLASS C");
            defaultmask=24;
        }
        else if(first>223){
            System.out.println("CLASS D");
            defaultmask=32;
        }
        String binip = "";
        String defmask = "";
        for(int i=0;i<4;i++){
            binip = binip +
                    appendZeroes(Integer.toBinaryString(Integer.parseInt(splitip[i])));
        }
        System.out.println("IP Address in binary : "+binip);
        System.out.println("Default Mask : "+defaultmask);
        for(int i=0;i<32;i++){
            if(i<mask){
                defmask = defmask + "1";
            }
            else{
                defmask = defmask + "0";
            }
        }
        System.out.println(defmask);
        String netid = "";
        for(int i=0;i<32;i++){
            netid = netid +
                    (Integer.parseInt(""+binip.charAt(i))&Integer.parseInt(""+defmask.charAt(i)));
        }
        int p=-1;
        System.out.println(netid);
        String[] net = new String[4];
        String[] def = new String[4];
        for(int i=0;i<32;i++){
            if(i%8==0){
                p++;
                net[p] = "";
                def[p]="";
            }
            net[p] = net[p] + netid.charAt(i);
            def[p] = def[p] + defmask.charAt(i);
        }
        System.out.println("Given IP: "+ip);
        System.out.print("Subnet Mask:");
        for(int i=0;i<4;i++){
            System.out.print(Integer.parseInt(def[i],2));
            if(i!=3)
                System.out.print(".");
        }
        System.out.println();
        System.out.print("NetId: ");
        for(int i=0;i<4;i++){
            System.out.print(Integer.parseInt(net[i],2));
            if(i!=3)
                System.out.print(".");
        }
        System.out.println();
    }
    private static String appendZeroes(String binaryString) {
// TODO Auto-generated method stub
        if(binaryString.length()<8){
            for(int i=0;i<8-binaryString.length();i++){
                binaryString = "0" + binaryString;
            }
        }
        String temp = "00000000";
        return temp.substring(binaryString.length())+ binaryString;
    }
}