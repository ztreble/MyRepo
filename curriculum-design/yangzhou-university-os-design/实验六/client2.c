#include <sys/msg.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include<stdlib.h>

struct msgbuf {
	long mType;
	char mText[50];
};

void *_write(void *arg){
	struct msgbuf msg;
	while(1){
		msg.mType = 1;
		fgets(msg.mText,50,stdin);
		if (msgsnd(*(int*)arg, &msg, sizeof msg.mText, 0) == -1) {
			perror("client: msgsnd failed:");
			exit(3);
	}

}
}

void *_read(void *arg){
     struct msgbuf msg;
     while(1)
     {
     	    int res = msgrcv(*(int*)arg,&msg,sizeof msg.mText,1,0);
	    if(res==-1){
	        perror("client: somthing wrong");
	        exit(-3);
	    }
	     printf("%s",msg.mText);
     }


}
int main() {
	char txtBuf[50];
	int qId,privateId,anotherId;
	key_t key;
	struct msgbuf msg, buf;
	struct msqid_ds msgCtlBuf;
	pthread_t id1,id2;

	if ((key = ftok("/tmp", 'C')) == -1) {
		perror("client: ftok failed:");
		exit(1);
	}
	
	printf("client: System V IPC key = %u\n", key);

	if ((qId = msgget(key, IPC_CREAT | 0666)) == -1) {
		perror("client: Failed to create message queue:");
		exit(2);
	}

	if ((privateId = msgget(IPC_PRIVATE, IPC_CREAT | 0666)) == -1) {
		perror("client: Failed to create message queue:");
		exit(2);
	}
	
	
	
	printf("client: Message queue id = %u\n", qId);

	printf("privateId_2 = %u\n",privateId);

	sprintf(txtBuf,"privateId_2 = %u\n",privateId);

	strcpy(msg.mText, txtBuf);

	msg.mType = 1;

	if (msgsnd(qId, &msg, sizeof msg.mText, 0) == -1) {
		perror("server: msgsnd failed:");
		exit(3);
	}


	printf("client: Message sent successfully\n");
	//
	// attempt read again and block on queue waiting for server to IPC_RMID
	//
	if (msgrcv(privateId, &buf, sizeof msg.mText, 1, 0) == -1)
		perror("client: msgrcv failed:");
	else{

		printf("client: Message received = %s\n", buf.mText);
		sscanf(buf.mText,"privateId_1 = %d\n",&anotherId);
	}
	
	printf("connect success!");
	
	int res1 = pthread_create(&id1,0,_write,&anotherId);
	int res2 = pthread_create(&id2,0,_read,&privateId);
	pthread_join(id1,0);
	pthread_join(id2,0);

	return 0;


}
