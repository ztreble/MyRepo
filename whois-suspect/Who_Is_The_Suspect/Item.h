#pragma once
#include <string>
using namespace std;
class Item
{
private:
	string name;
	string description;
	bool muderWeapon;

public:
	Item(string, string, bool);
	const bool getMuderWeapon() const { return muderWeapon; }
	void setMuderWeapon(const bool val) { muderWeapon = val; }
	std::string getDescription() const { return description; }
	void setDescription(std::string val) { description = val; }
	std::string getName() const { return name; }
	void setName(std::string val) { name = val; }
};

