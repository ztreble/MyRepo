#pragma once
#include "Person.h"
class Suspect :
	public Person
{
private:
	string alibi;
	bool murder;

public:
	Suspect(string ,string);
	const std::string getAlibi() const { return alibi; }
	void setAlibi(const std::string val) { alibi = val; }
	void randomWalk();
	bool getMurder() const { return murder; }
	void setMurder(bool val) { murder = val; }
};

