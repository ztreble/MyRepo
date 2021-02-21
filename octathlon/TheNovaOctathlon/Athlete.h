#ifndef ATHLETE_H
#define ATHLETE_H
#include "Person.h"
#include"Config.h"
#include<iostream>
#include<string>
#include <cstdarg>
using namespace std;
class Athlete:public Person
{
public:
	Athlete() = default;
	Athlete(string,bool,string,int*,int*,int);
	~Athlete() =default;
	int capacity[8];
	int potential[8];
	double status;
	int characterstic;
	void printInfo();
	static string getTrait(int);
private:
};
#endif
