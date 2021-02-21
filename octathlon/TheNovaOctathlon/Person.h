#ifndef PERSON_H
#define PERSON_H

#include<iostream>
#include<string>
#include <cstdarg>
#include<vector>
using namespace std;
class Person
{
public:
	Person();
	Person(string name, bool gender, string identity);
	virtual ~Person();
	virtual void printInfo(int Num, ...);
	void setName(string name);
	std::string Name() const { return name; }
	void Name(std::string val) { name = val; }
	bool Gender() const { return gender; }
	void Gender(bool val) { gender = val; }
	std::string Identity() const { return identity; }
	void Identity(std::string val) { identity = val; }
	static string getCharacterstic(int);
private:
	string name;
	bool gender;//true is man
	string identity;
};



#endif 
