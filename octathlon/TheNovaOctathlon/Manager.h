
#ifndef MANAGER_H
#define MANAGER_H
#include "Person.h"
class Manager :
	public Person
{
public:
	Manager(string name, bool gender, string identity);
	~Manager();
	void printInfo();
};
#endif
