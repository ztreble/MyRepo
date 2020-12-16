%{
	#include<stdio.h>
	#include<stdlib.h>
	int space=0;
%}

SPACE [ \n\r\t]
IO read|write
DIGIT [0-9]
INTEGER [1-9]{DIGIT}*
FLOAT [0-9]*[.][0-9]+([eE][+-]?[0-9]*|[0])?f?
ADD_OP \+|\-
MULT_OP \*|\/
ASSIGMENT :=
USER_DEFINE [a-zA-Z][a-zA-Z0-9]*

%%

{SPACE} {++space;}
{IO} {printf("get IO: %s\n", yytext);}
{DIGIT} {printf("get DIGIT: %s\n", yytext);}
{INTEGER} {printf("get INTEGER: %s\n", yytext);}
{FLOAT} {printf("get FLOAT: %s\n", yytext);}
{ADD_OP} {printf("get ADD_OP: %s\n", yytext);}
{MULT_OP} {printf("get MULT_OP: %s\n", yytext);}
{ASSIGMENT} {printf("get ASSIGMENT: %s\n", yytext);}
{USER_DEFINE} {printf("get USER_DEFINE: %s\n", yytext);}

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
	while(yylex());
	printf("Get space total: %d\n", space);
	return 0;
}



