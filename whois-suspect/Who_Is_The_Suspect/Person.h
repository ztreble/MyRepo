#pragma once
#include<string>

using namespace std;
class Person
{

private:
	string name;
	string description;
	int location;

public:
	void move();
	Person() = default;
	Person(string,string);
	const std::string getName() const { return name; }
	const std::string getDescription() const { return description; }
	int getLocation() const { return location; }
	void setLocation(int val) { location = val; }
	void setName(const std::string val) { name = val; }
	void setDescription(const std::string val) { description = val; }
};

