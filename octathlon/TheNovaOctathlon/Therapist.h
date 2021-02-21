
#include "Person.h"
#ifndef THERAPIST_H
#define THERAPIST_H

class Therapist:public Person
{
public:
	Therapist() = default;
	Therapist(string name, bool gender, string identity, int capacity);
	~Therapist();
	int capacity;
	void printInfo();
};

#endif