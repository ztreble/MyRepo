#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include<sys/types.h>
#include<dirent.h>
#include <stdbool.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pwd.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#define NAMELEN 100

int main() {
	char *gpath;
	while (1) {
		char *username = getlogin();
		char *path;
		char mOper[NAMELEN];
		char mText[NAMELEN];
		
		char servername[NAMELEN], pathname[NAMELEN];
		
		if (gethostname(servername, NAMELEN) == -1) {
			printf("get hostname wrong");
			break;
		}

		path = getcwd(pathname, NAMELEN);

		printf(">>>");
		scanf("%s", mOper);

		if (strcmp(mOper, "cd") == 0) {
			scanf("%s", mText);
			if (chdir(mText) == -1) {
				printf("Catalog invalid!");
			}
		}
		else if (strcmp(mOper, "exit") == 0) {
			exit(EXIT_SUCCESS);
		}
		else{
			fflush(stdout);
        char buf[1024];
        ssize_t readsize = read(0,buf,sizeof(buf)-1);
        if(readsize>0)
            buf[readsize-1]=0;
        char *start=buf;
        char *_argv[32];
        _argv[0]=start;
        int i=1;
        int k=0;
        while(*start)
        {
            if(*start == ' ')
            {
                *start='\0';
                start++;
               _argv[i++] = start;
            }
            else
                start++;
        }
    _argv[i]=NULL;

    pid_t id=fork();
    if(id<0)
    {
        perror("fork");
        return 1;
    }
    if(id == 0)
    {
    for(k=0;k<i;k++)
    {
        if(strcmp(_argv[k],">") == 0)
        {
            char *file=_argv[k+1];
            _argv[k]=NULL;
            close(1);
            int fd  = open (file,O_WRONLY|O_CREAT);
            break;
        }
    }
        execvp(_argv[0],_argv);
        exit(2);
        fflush(stdout);
    }
	}
	}
	return 0;
}

