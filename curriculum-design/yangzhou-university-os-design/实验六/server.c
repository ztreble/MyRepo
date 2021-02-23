#include <sys/msg.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct msgbuf {
	long mType;
	char mText[50];
};

int main() {
	char txtBuf[50];
	int qId,pid_1,pid_2;
	key_t key;
	struct msgbuf msg, buf_1,buf_2;
	struct msqid_ds msgCtlBuf;

	if ((key = ftok("/tmp", 'C')) == -1) {
		perror("server: ftok failed:");
		exit(1);
	}

	printf("server: System V IPC key = %u\n", key);

	if ((qId = msgget(key, IPC_CREAT | 0666)) == -1) {
		perror("server: Failed to create message queue:");
		exit(2);
	}

	printf("server: Message queue id = %u\n", qId);

	if (msgrcv(qId, &buf_1, sizeof msg.mText, 1, 0) == -1)
		perror("client: msgrcv failed:");
	else{
		printf("client: Message received = %s\n", buf_1.mText);
		sscanf(buf_1.mText,"privateId_1 = %d\n",&pid_1);
	}

	if (msgrcv(qId, &buf_2, sizeof msg.mText, 1, 0) == -1)
		perror("client: msgrcv failed:");
	else{
		printf("client: Message received = %s\n", buf_2.mText);
		sscanf(buf_2.mText,"privateId_2 = %d\n",&pid_2);
	}

	printf("server: waiting...\n");
	printf("%d,%d\n",pid_1,pid_2);

	if (msgsnd(pid_1, &buf_2, sizeof msg.mText, 0) == -1) {
		perror("server: msgsnd failed:");
		exit(3);
	}
	if (msgsnd(pid_2, &buf_1, sizeof msg.mText, 0) == -1) {
		perror("server: msgsnd failed:");
		exit(3);
	}
	printf("server: Message sent successfully\n");


	sleep(15);
	printf("server: done waiting. removing message queue...\n");

	if (msgctl(qId, IPC_RMID, &msgCtlBuf) == -1) {
		perror("server: msgctl failed:");
		exit(4);
	}

	printf("server: Message queue removed OK\n");
}



