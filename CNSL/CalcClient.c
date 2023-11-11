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
/*Calculator Client*/
#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<netinet/in.h>
#include <unistd.h>
#include<string.h>
#include<strings.h>
#include <arpa/inet.h>
//#define buffsize  150
void main()
{
 int b,sockfd,sin_size,con,n,len;
 char operator;
 int op1,op2,result;
 if((sockfd=socket(AF_INET,SOCK_STREAM,0))>0)
 printf("socket created sucessfully\n");
  struct sockaddr_in servaddr;
 servaddr.sin_family=AF_INET;
 servaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
 servaddr.sin_port=6006;
 sin_size = sizeof(struct sockaddr_in);
 if((con=connect(sockfd,(struct sockaddr *) &servaddr, sin_size))==0); //initiate a connection on a socket
  printf("connect sucessful\n");
  printf("Enter operation:\n +:Addition \n -: Subtraction \n /: Division \n*:Multiplication \n");
 scanf("%c",&operator);
  printf("Enter operands:\n");
 scanf("%d %d", &op1, &op2);
 write(sockfd,&operator,1);
 write(sockfd,&op1,sizeof(op1));
 write(sockfd,&op2,sizeof(op2));
 read(sockfd,&result,sizeof(result));
 printf("Operation result from server=%d\n",result);
 close(sockfd);
}