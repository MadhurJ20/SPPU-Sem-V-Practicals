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
Priority (Non-Preemptive) Scheduling Algorithm
*/

import java.util.Scanner;
public class Priority
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int[] ct;
        int[] a;
        int x;
        int n;
        int[] p;
        int[] pp;
        int[] bt;
        int[] w;
        int[] t;
        int i;
        int k=0;
        float atat,awt;
        p = new int[10];
        pp = new int[10];
        bt = new int[10];
        w = new int[10];
        t = new int[10];
        a= new int[10];
        ct=new int[10];
        System.out.print("Enter the number of processes: ");
        n = s.nextInt();
        for(i=0;i<n;i++)
        {
            System.out.print("\nProcess["+(i+1)+"]:");
            bt[i] = s.nextInt();
            pp[i] = s.nextInt();
            a[i]=s.nextInt();
            p[i]=i+1;
        }

        //SORT ON THE BASIS OF ARRIVAL TIME AND PRIORITY
        for(i=0;i<n-1;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(a[i]>=a[j] || pp[i]>pp[j])
                {
                    x=pp[i];
                    pp[i]=pp[j];
                    pp[j]=x;
                    x=bt[i];
                    bt[i]=bt[j];
                    bt[j]=x;
                    x=p[i];
                    p[i]=p[j];
                    p[j]=x;
                    x=a[i];
                    a[i]=a[j];
                    a[j]=x;
                }
            }
        }
        for(i=1;i<=n;i++)
        {
            if(i==1)
            {
                k=bt[0];
                ct[0]=k;

            }
            else
            {
                k=bt[i-1]+k;
                ct[i-1]=k;
            }
            for(int j=i+1;j<=n;j++)
            {
                if(pp[i]<pp[j] && a[j]<=k)
                {
                    x=pp[i];
                    pp[i]=pp[j];
                    pp[j]=x;
                    x=bt[i];
                    bt[i]=bt[j];
                    bt[j]=x;
                    x=p[i];
                    p[i]=p[j];
                    p[j]=x;
                    x=a[i];
                    a[i]=a[j];
                    a[j]=x;
                }
            }
        }
        w[0]=0;
        awt=0;
        t[0]=bt[0];
        atat=t[0];
        for(i=1;i<n;i++)
        {
            t[i]=ct[i]-a[i];
            w[i]=t[i]-bt[i];
            awt+=w[i];
            atat+=t[i];
        }
        System.out.println("\nProcess\tArrival  Burst  Wait Time Turn Around Time Priority\n");
        for(i=0;i<n;i++)
        {
            System.out.println("\n   "+p[i]+"\t    "+a[i]+"\t    "+bt[i]+"\t   "+w[i]+"\t      "+t[i]+"\t        "+pp[i]+"\n");
        }
        awt/=n;
        atat/=n;
        System.out.println("Average Wait Time: "+ awt);
        System.out.println("Average Turnaround Time : "+ atat);
    }
}