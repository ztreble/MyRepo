#include "Game.h"
#include "Detective.h"
#include <iostream>
#include "Item.h"
#include "location.h"
using namespace std;
#include <random>
#include <time.h>
int main() {
	//Location lo;
	//for(int i=0;i<12;i++)
	//	cout<<lo.getName(i);
	Game::startGame();
	//std::default_random_engine generator(time(NULL));
	//std::uniform_int_distribution<int> distribution(0, 10);
	//int test = 20;
	//while (test--) {
	//	cout << distribution(generator)<<endl;
	//}
/*
	vector<Item> tmpItem;
	tmpItem.push_back(Item("Book", "", false));
	tmpItem.push_back(Item("Hammer", "", true));
	tmpItem.push_back(Item("Shovel", "", false));
	tmpItem.push_back(Item("Watch", "", false));
	tmpItem.push_back(Item("Pen", "", false));
	tmpItem.push_back(Item("Kettle", "", false));
	tmpItem.push_back(Item("Scissor", "", false));
	tmpItem.push_back(Item("Scarf", "", false));
	tmpItem.push_back(Item("Rope", "", false));
	tmpItem.push_back(Item("Coat", "", false));
	tmpItem.push_back(Item("Screwdriver", "", false));
	tmpItem.push_back(Item("Stick", "", false));

	tmpItem.push_back(Item("Flowerpot", "", false));
	tmpItem.push_back(Item("Mouse", "", false));
	tmpItem.push_back(Item("Garage Kits", "", false));
	tmpItem.push_back(Item("Barstool", "", false));
	tmpItem.push_back(Item("Knife", "", false));

	tmpItem.push_back(Item("Clamp", "", false));
	tmpItem.push_back(Item("Mirror", "", false));
	tmpItem.push_back(Item("Chopsticks", "", false));
	tmpItem.push_back(Item("Cup", "", false));
	tmpItem.push_back(Item("Bowl", "", false));
	tmpItem.push_back(Item("Pen", "", false));
	tmpItem.push_back(Item("Car", "", false));

	auto it = tmpItem.begin();

	auto res = *it;
	
	it = tmpItem.erase(it);
	int i =24;
	it = tmpItem.begin();
	while (i--) {
		cout<<it->getName();
		auto a = *it;
		tmpItem.erase(it);
		cout << a.getName();
		cout << it->getName();
		it++;
	}


	return 0;*/



}