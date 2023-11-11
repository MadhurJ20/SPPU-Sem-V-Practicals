/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Write a program using UDP Sockets to enable file transfer (Script,
Text, Audio and Video one file each) between two machines.
*/
/*UDP Client*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>

int main() {
	int fd;
	char fileName[2000],afileName[2000],vfileName[2000],file_buffer[2000],c,caufile[70000],aufile[7000000],vfile[1000000];
	struct sockaddr_in	 servaddr;

	// Creating socket file descriptor
	if ( (fd = socket(AF_INET, SOCK_DGRAM, 0)) < 0 ) {
		perror("socket creation failed");
		exit(EXIT_FAILURE);
	}

	memset(&servaddr, 0, sizeof(servaddr));

	bzero(&servaddr,sizeof(servaddr));
	// Filling server information
	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(8000);
	servaddr.sin_addr.s_addr = INADDR_ANY;
// servaddr.sin_addr.s_addr=inet_addr("10.10.10.73");
	int choice = 1;
	
	while(choice!=4)
	{
		printf("ENTER \n 1.TEXT \n 2.AUDIO \n 3.VIDEO\n4.EXIT");
		scanf("%d",&choice);
		
		char num=choice;
		
		sendto(fd, &num, sizeof(num), 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr));
		
		switch(choice)
		{
			case 1:
				printf("Enter text file name to send : \n");
    				scanf("%s",fileName);
    				sendto(fd, fileName, strlen(fileName), 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr));

    				FILE *fp;
     				fp=fopen(fileName,"r");

     				if(fp)
     				{
    	 				printf("Reading file contents.\n");
     					fseek(fp,0,SEEK_END);
       					size_t file_size=ftell(fp);
       					fseek(fp,0,SEEK_SET);
      					if(fread(file_buffer,file_size,1,fp)<=0)
         				{
           					printf("Unable to copy file into buffer or empty file.\n");
           					exit(1);
         				}
        			}
        			else
        			{
    					printf("Cannot open file.\n");
    	 				exit(0);
    			 	}
    				printf("FILE CONTENTS TO SEND : %s\n",file_buffer);
    				if(sendto(fd, file_buffer,strlen(file_buffer), 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr))<0)
    				{
    	 				printf("FILE WAS NOT SENT\n");
    				}
    				else
    				{
    	 				printf("FILE SENT\n");
    				}
    				fclose(fp);
    				break;
    				
    			case 2:
    				printf("Enter audio file name to send : \n");
    				scanf("%s",afileName);
    				sendto(fd, afileName, strlen(afileName), 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr));
				FILE *afp;
				afp=fopen(afileName,"r");
				fseek(afp,0,SEEK_END);
				size_t  afsize=ftell(afp);
				fseek(afp,0,SEEK_SET);

				if(afp)
				{
	 			printf("Reading file contents.\n");
	 				if(fread(aufile,afsize,1,afp)<=0)
	         			{
	           				printf("Unable to copy file into buffer or empty file.\n");
	           				exit(1);
	         			}
				}
				else
				{
					printf("Could not read audio file.\n");
					exit(0);
				}
				
				if(sendto(fd, aufile, afsize, 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr))<0)
				{
	 				printf("FILE WAS NOT SENT\n");
	    			}
	    			else
	    			{
	    	 			printf("FILE SENT\n");
	    			}
				fclose(afp);
				break;
				
			case 3:
				printf("Enter video file name to send : \n");
    				scanf("%s",vfileName);
				sendto(fd, vfileName, strlen(vfileName), 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr));
				FILE *vfp;
				vfp=fopen(vfileName,"r");

				fseek(vfp, 0, SEEK_END);
				size_t vfsize = ftell(vfp);
				fseek(vfp, 0, SEEK_SET);

				if(vfp)
				{
					if(fread(vfile, 1, vfsize, vfp)<=0)
					{
						printf("No contents or error reading file \n");
					}
				}
				else
				{
					printf("Could not read audio file.\n");
					exit(0);
				}
				if(sendto(fd, vfile, vfsize, 0,(struct sockaddr *)&servaddr, sizeof(struct sockaddr))<0)
				{ 
				printf("FILE WAS NOT SENT\n");
				}
				else
				{
	 				printf("FILE SENT\n");
				}
				fclose(vfp);
				break;
		
			case 4:
				close(fd);
				break;
				
		
		
		}
		
	
	}
	

}
