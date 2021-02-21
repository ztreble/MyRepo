#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include<stdio.h>
#include<sys/types.h>
#include<dirent.h>
#include<stdio.h>
#include<sys/types.h>
#include<dirent.h>

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


		printf("%s @ %s : %s :", username, servername, pathname);

		//fgets(mText, 100, stdin);
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
		else if (strcmp(mOper, "path") == 0) {
			strcpy(gpath, mText);
		}
		else if (strcmp(mOper, "ls") == 0) {
			DIR *dir_ptr;
			struct dirent *direntp;

			if ((dir_ptr = opendir(pathname)) == NULL)
				fprintf(stderr, "ls1:cannot open %s\n", pathname);
			else
			{
				while ((direntp = readdir(dir_ptr)) != NULL && strcmp(direntp->d_name, ".") != 0 \
					&& strcmp(direntp->d_name, "..") != 0)
					printf("%s\n", direntp->d_name);
				closedir(dir_ptr);
			}
		}
		else if (strcmp(mOper, "cp") == 0) {

		}
		else if (strcmp(mOper, "pwd") == 0) {

		}
		else if (access(mOper, X_OK) == 0) {

			int fd[2];
			pipe(fd);
			pid_t fpid = fork();
			if (fpid < 0)
				printf("Process creation failure");
			else if (fpid == 0) {
				char* envp = { pathname,0 };
				execve(mOper, NULL, envp);


				close(fd[1]);
				dup2(fd[0], 0);

				fid = open(mOper, O_WRONLY | O_CREAT);
				dup2(fid, 0);
				dup2(fid, 1);
			}
			else {
				close(fd[2]);
				dup2(fd[1], 1);
			}
		}
		else {
			printf("Invalid input.please input again.");
		}


	}
	return 0;
}
