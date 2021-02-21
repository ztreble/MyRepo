#include "Util.h"
#include <fstream>
#include <iostream>
#include<string>
#include "Config.h"
#include<time.h>
#include<math.h>


static auto getRandom = [](int min, int max) {
	std::default_random_engine generator(time(NULL));
	std::uniform_int_distribution<int> distribution(min, max);
	int result = distribution(generator);
	return result;
};

static auto getItem = []() {
	vector<Item> tmpItem;
	tmpItem.push_back(Item("Book", "1", false));
	tmpItem.push_back(Item("Hammer", "2", true));
	tmpItem.push_back(Item("Shovel", "3", false));
	tmpItem.push_back(Item("Watch", "4", false));
	tmpItem.push_back(Item("Pen", "5", false));
	tmpItem.push_back(Item("Kettle", "6", false));
	tmpItem.push_back(Item("Scissor", "7", false));
	tmpItem.push_back(Item("Scarf", "8", false));
	tmpItem.push_back(Item("Rope", "9", false));
	tmpItem.push_back(Item("Coat", "10", false));
	tmpItem.push_back(Item("Screwdriver", "11", false));
	tmpItem.push_back(Item("Stick", "12", false));

	tmpItem.push_back(Item("Flowerpot", "13", false));
	tmpItem.push_back(Item("Mouse", "14", false));
	tmpItem.push_back(Item("Garage", "15", false));
	tmpItem.push_back(Item("Barstool", "16", false));
	tmpItem.push_back(Item("Knife", "17", false));

	tmpItem.push_back(Item("Clamp", "18", false));
	tmpItem.push_back(Item("Mirror", "19", false));
	tmpItem.push_back(Item("Chopsticks", "20", false));
	tmpItem.push_back(Item("Cup", "21", false));
	tmpItem.push_back(Item("Bowl", "22", false));
	tmpItem.push_back(Item("Pen", "23", false));
	tmpItem.push_back(Item("Car", "24", false));
	return tmpItem;
};

vector<Item> Util::allItem = getItem();


int Util::getRandomRoom(int seed){
	srand((unsigned)time(NULL) + seed);
	return rand() % (TOTALROOM) + 1;
}

int Util::getRandomItemNum() {
	return getRandom(1, 2);
}
string Util::getItemDescription(string name) {
	allItem = getItem();
	for (auto a : allItem) {
		if (name == a.getName())
			return a.getDescription();
	}
	 return "There is no such thing!";
}

Item Util::getRandomItem() {
	int subscript = getRandomNum(0, allItem.size()-1);
	auto it = allItem.begin();

	while (subscript--)
		it++;
	auto res = *it;
	allItem.erase(it);
	return res;
}
Item Util::findItem(string name) {
	allItem = getItem();
	for (auto a : allItem) {
		if (name == a.getName())
			return a;
	}
	throw "This should not be executed to";
}

int Util::getRandomNum(int min,int max) {
	return getRandom(min, max);
}


void Util::printFunction() {
	cout << "\tConsole" << endl;
	cout << "************************************************************************************************************************" << endl;
	cout << "\tMovement" << endl;
	cout << "************************************************************************************************************************" << endl;
	cout << "\tTo see the crime scene layout: MAP or M" << endl;
	cout << "\tTo move around the crime scene: U D L R" << endl;
	cout << "\tTo leave the case at any time:QUIT" << endl;
	cout << "************************************************************************************************************************" << endl;
	cout << "\tRooms and Items" << endl;
	cout << "\tTo examine the current room:SEARCH or S" << endl;
	cout << "\tTo examine an item in the room:EXAMINE or X item name" << endl;
	cout << "\tTo pick up the good in the room:PICK or P item name" << endl;
	cout << "\tTo drop an item you are carrying:DROP item name" << endl;
	cout << "\tTo see what you're carrying:INV or I Suspects" << endl;
	cout << "************************************************************************************************************************" << endl;
	cout << "\tSuspects" << endl;
	cout << "************************************************************************************************************************" << endl;
	cout << "\tTo displays the names of everyone in the room:Display" << endl;
	cout << "\tTo question a suspect:QUESTION suspect first name" << endl;
	cout << "\tTo accuse a suspect of murder:ACCUSE or A suspect first name" << endl;
	cout << endl;
	
}

int Util::getX(int lo) {
	int a = (lo/4);
	if (lo%4==0) a--;
	return a+1;
}
int Util::getY(int lo) {
	int a = lo % 4;
	if (a == 0) return 4;
	else return a;
}

int Util::getLocation(int x, int y) {
	return (x - 1) * 4 + y ;
}