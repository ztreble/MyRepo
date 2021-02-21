#include <time.h>
#include <stdio.h>
#include <pthread.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

static void *hard_work(void *work) {
    int *amount = (int*) work;
    struct timespec t = { .tv_sec = 0, .tv_nsec = *amount * 100000 }; nanosleep( &t, NULL ); // **Really** hard work.
    return amount;
}

static void processes(int x, int y){
    while(x>0){
        x -= y;
        int status;
        pid_t pid;
        for(int i=0;i<y;i++){
            pid = fork();
            if(pid==0) {
                hard_work((void*)1);
                return;
            }
        }
        wait(&status);
    }
}
static void threads(int x, int y){
    while(x>0){
        x -= y;
        int rc;
        void* status;
        pthread_t array[10000];
        for(int i=0;i<y;i++){
            int arg = 1;
            int ret;
            ret = pthread_create(&array[i],NULL,hard_work,&arg);
            if(ret!=0){
                printf("Create thread error!");
                return;
            }
        }
        for(int i=0;i<y;i++){
            rc = pthread_join(array[i],&status);
            if(rc){
                printf("Join thread error!");
            }
        }
    }
}
struct timespec diff(struct timespec start,struct timespec end){
    struct timespec temp;
    if ((end.tv_nsec-start.tv_nsec)<0) {
        temp.tv_sec = end.tv_sec-start.tv_sec-1;
        temp.tv_nsec = 1000000000+end.tv_nsec-start.tv_nsec;
    } else {
        temp.tv_sec = end.tv_sec-start.tv_sec;
        temp.tv_nsec = end.tv_nsec-start.tv_nsec;
    }
    return temp;
}
int main(int argc,char *argv[]) {

    int x = atoi(argv[1]);
    int y = atoi(argv[2]);
    struct timespec time1, time2;

    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &time1);
    processes(x,y);
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &time2);
    printf("Process total time:");
    printf("%ld\n",(diff(time1, time2).tv_nsec));

    clock_gettime(CLOCK_THREAD_CPUTIME_ID, &time1);
    threads(x,y);
    clock_gettime(CLOCK_THREAD_CPUTIME_ID, &time2);
    printf("Thread total time:");
    printf("%ld\n",(diff(time1, time2).tv_nsec));

    return 0;
}
