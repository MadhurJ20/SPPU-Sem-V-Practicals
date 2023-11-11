/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program for error detection and correction for 7/8 bits ASCII
codes using Hamming Codes or CRC.
*/
#include<iostream>
using namespace std;
int main()
{
    int datasend[10];
    int i;
    cout<<"Enter four bits of data one by one:"<<endl;
    cin>>datasend[7];
    cin>>datasend[6];
    cin>>datasend[5];
    cin>>datasend[3];

    datasend[4]=datasend[5]^datasend[6]^datasend[7];
    datasend[2]=datasend[3]^datasend[6]^datasend[7];
    datasend[1]=datasend[3]^datasend[5]^datasend[7];

    cout<<"Encoded data is:"<<endl;
    for(i=1;i<=7;i++)
    {
        cout<<datasend[i];
    }
    cout<<endl;
    return 0;
}