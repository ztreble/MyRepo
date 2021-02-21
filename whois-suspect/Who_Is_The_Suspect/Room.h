#pragma once
#include<string>
#include <vector>
#include "Item.h"
using namespace std;
class Room
{
public:
	Room() = default;
	Room(string, string, bool);

	std::string getName() const { return name; }
	void setName(std::string val) { name = val; }
	bool getMurder() const { return murder; }
	void setMurder(bool val) { murder = val; }

	void delGood();
	void addGood(string);
	bool findGoods(int);
	bool deleteGoods(string);
	std::vector<Item> getGoods() const { return goods; }
	void setGoods(std::vector<Item> val) { goods = val; }
private:
	string name;
	string description;
	bool murder;
	vector<Item> goods;
};

