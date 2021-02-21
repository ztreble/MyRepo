
#ifndef PSYCHOLOGIST_H
#define PSYCHOLOGIST_H
#include "Person.h"

class Psychologist:public Person
{
public:
	Psychologist() = default;
	Psychologist(string name, bool gender, string identity, int capacity);
	~Psychologist();
	int capacity;
	void printInfo();
private:
	
};


#endif