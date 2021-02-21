%{
	#include<stdio.h>
	#include<stdlib.h>
	int space=0;
%}

SPACE [ \n\r\t]
ANNOTATION \{.*\}|\(\*.*\*\)
STRING \'.*\'
OTHYER [^(?:(?!\{.*\}).)+$]

%%

{ANNOTATION} {printf("This is an annotation.\n");}
{SPACE} {++space;}
{STRING}	{printf("This is a string.\n");}
{OTHYER} {
	for(int i=0;i<yyleng;i++){
		if(*(yytext+i)>='A'&&*(yytext+i)<='Z')
				*(yytext+i)=*(yytext+i)+32;
	}
	ECHO;
}

%%
int yywrap(){
	return 1;
}
int main(int argc,char** argv){
  if(argc>1){
    if(!(yyin=fopen(argv[1],"r"))){
      perror(argv[1]);
      return 1; 
    }
  }
	yyout=fopen("out.txt","ra+");
	while(yylex());
	printf("Get space total: %d\n", space);
	return 0;
}



