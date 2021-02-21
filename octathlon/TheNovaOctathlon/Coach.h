#pragma once
#include "Person.h"
class Coach :
	public Person
{
public:
	Coach() = default;
	Coach(string name, bool gender, string identity, int capacity);
	~Coach();
	int capacity;
	void printInfo();
};


