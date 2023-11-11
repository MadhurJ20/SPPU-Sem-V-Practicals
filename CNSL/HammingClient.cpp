/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program for error detection and correction for 7/8 bits ASCII codes
using Hamming Codes or CRC.
*/
#include<iostream>
using namespace std;

int main()
{
    int datarec[10],c,c1,c2,c3,i;
    cout<<"Enter received data bits one by one:"<<endl;
    for(i=1;i<=7;i++)
    {
        cin>>datarec[i];
    }

    c1=datarec[1]^datarec[3]^datarec[5]^datarec[7];
    c2=datarec[2]^datarec[3]^datarec[6]^datarec[7];
    c3=datarec[4]^datarec[5]^datarec[6]^datarec[7];
    c=c3*4+c2*2+c1;
    if(c==0)
    {
        cout<<"There is no error.";
    }
    else
    {
        cout<<"Error encountered on position: "<<c<<endl;
        cout<<"Correct message is:";
        if(datarec[c]==0)
        {
            datarec[c]=1;
        }
        else
        {
            datarec[c]=0;
        }
        for(i=1;i<=7;i++)
        {
            cout<<datarec[i];
        }
    }
    cout<<endl;
    return 0;
}