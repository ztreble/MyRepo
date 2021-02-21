#include<sys/sem.h>
#include<sys/ipc.h>
#include<sys/types.h>
#include<errno.h>
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>

#include <stddef.h>
#include <sys/shm.h>
#include <string.h>

#define TEXT_SIZE 1024


union union_semun {
	int val;
	struct semid_ds *buf;
	unsigned short *array;
}ctl_arg;

/*struct sembuf {
unsigned short sem_num;
short sem_op;
short sem_flg;
};*/

struct databuf {
	int written;
	char text[TEXT_SIZE];
};

static int shmid, semid_full, semid_empty, semid_zuse;

void initshm(struct databuf**p) {
	//\u521b\u5efa\u5171\u4eab\u5185\u5b58
	shmid = shmget((key_t)4321, sizeof(struct databuf), 0600 | IPC_CREAT);
	if (shmid == -1) {
		printf("shmget failed!\n");
		return;
	}
	//\u8fde\u63a5\u5230\u5f53\u524d\u8fdb\u7a0b\uff0c\u8fd4\u56de\u6307\u5411\u7b2c\u4e00\u4e2a\u5b57\u8282\u7684\u6307\u9488
	void *shm = shmat(shmid, NULL, 0);
	if (shm == (void *)-1) {
		printf("shmat failed!\n");
		return;
	}
	printf("\nmemory attached at %d\n", (int)shm);

	shm = *p;

}

void setdata(struct databuf**p) {

}
int initsem(key_t semkey) {
	//\u521b\u5efa\u4fe1\u53f7\u91cf\u96c6\uff0c\u5e76\u4e14\u521d\u59cb\u5316
	printf("\ninitsem running...");
	int tmp = semget(semkey, 1, 0600 | IPC_CREAT | IPC_EXCL);
	if (tmp == -1) {
		printf("semget create failed!");
		return;
	}
	printf("semid %d created successful", tmp);
	return tmp;
}
void remobj(void) {
	if (semctl(semid_empty, IPC_RMID, 0) == -1)	printf("\nremctl remobj error\n");
	else printf("\nremctl delete success");
	if (semctl(semid_full, IPC_RMID, 0) == -1)	printf("\nremctl remobj error\n");
	else printf("\nremctl delete success");
}

int P(int semid) {
	struct sembuf p_buf;
	p_buf.sem_num = 0;
	p_buf.sem_op = -1;
	p_buf.sem_flg = SEM_UNDO;
	if (semop(semid, &p_buf, 1) == -1) {
		perror("p(semid) failed");
		exit(1);
	}
	return 1;
}

int V(int semid) {
	struct sembuf p_buf;
	p_buf.sem_num = 0;
	p_buf.sem_op = 1;
	p_buf.sem_flg = SEM_UNDO;
	if (semop(semid, &p_buf, 1) == -1) {
		perror("p(semid) failed");
		exit(1);
	}
	return 1;
}

void producter() {
	//while (1) {
	//if (P(semid)) {
	P(semid_empty);
	P(semid_zuse);
	struct databuf *p = NULL;
	//\u6d4b\u8bd5\uff0c\u653e\u5165\u6570\u636e
	void *shm = shmat(shmid, NULL, 0);
	if (shm == (void *)-1) {
		printf("producter shmat failed!\n");
	}
	printf("\nproducter get shm scuess!");

	p = (struct databuf *)shm;
	//printf("\nplease input something:");
	strcpy(p->text, "Apple");
	printf("\nGet data success!");
	sleep(10);
	V(semid_zuse);
	V(semid_full);
	//}
	//}
}

void filter() {
	while (1) {
		printf("\nfilter running...");
		sleep(10);
	}
}

void consumer() {
	/*while(1){

	}*/
	//\u6d4b\u8bd5\uff0c\u8bfb\u51fa\u6570\u636e
	while (1) {
		//if (P(semid)) {
		P(semid_full);
		P(semid_zuse);
		struct databuf *p = NULL;

		void *shm = shmat(shmid, 0, 0);
		if (shm == (void *)-1) {
			printf("consumer shmat failed!\n");
		}
		printf("\ncomsumer get shm scuess!");

		p = (struct databuf *)shm;
		printf("\nYou wirte is %s", p->text);
		V(semid_zuse);
		V(semid_empty);

	}
}

int main() {
	key_t semkey, shmkey;
	struct databuf *buf;
	initshm(&buf);
	semid_full = initsem(116);
	semid_empty = initsem(117);
	semid_zuse = initsem(118);


	pid_t fpid = fork();

	if (fpid < 0) {
		printf("error in fork!");
	}
	else if (fpid == 0) {
		producter();
	}
	else {
		sleep(5);
		pid_t fpid2 = fork();
		if (fpid2 < 0)	printf("error in fork!");
		else if (fpid2 == 0)	filter();
		else	consumer();
	}

	remobj();

}