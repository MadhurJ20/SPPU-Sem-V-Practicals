/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program to simulate CPU Scheduling Algorithms: FCFS, SJF (Preemptive),
Priority (Non-Preemptive) and Round Robin (Preemptive).
*/
/*
Round Robin (RR) Scheduling Algorithm
*/

import java.util.Scanner;
public class RoundRobin
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int[] wtime;
        int[] btime;
        int[] rtime;
        int num;
        int quantum;

        wtime = new int[10];
        btime = new int[10];
        rtime = new int[10];

        System.out.println("Enter number of processes: ");
        num = s.nextInt();
        for(int i=0;i<num;i++)
        {
            System.out.println("\nEnter burst time for process P["+(i+1)+"]: ");
            btime[i] = s.nextInt();
            rtime[i] = btime[i];
            wtime[i]=0;
        }
        System.out.println("\n\nEnter quantum: ");
        quantum = s.nextInt();
        int rp = num;
        int i=0;
        int time=0;
        System.out.print('0');
        wtime[0]=0;
        while(rp!=0)
        {
            if(rtime[i]>quantum)
            {
                rtime[i]=rtime[i]-quantum;
                System.out.print(" | P["+(i+1)+"] | " + "\n");
                time+=quantum;
                System.out.print(time);
            }
            else if(rtime[i]<=quantum && rtime[i]>0)
            {
                time+=rtime[i];
                rtime[i]= 0;
                System.out.print(" | P["+(i+1)+"] | " + "\n");
                rp--;
                System.out.print(time);
            }
            i++;
            if(i==num)
            {
                i=0;
            }
        }
        System.out.println();
    }
}