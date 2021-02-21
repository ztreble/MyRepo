#include "Room.h"
#include <iostream>
#include "Util.h"
Room::Room(string name, string description, bool murder)
	:name(name),description(description),murder(murder)
{

}


bool Room::deleteGoods(string good) {
	for (auto a = goods.begin();a!=goods.end();a++) {
		if (a->getName() == good) {
			goods.erase(a);
			return true;
		}
			
	}

	std::cout << "There is no such thing! You can't delete it in the room!" << endl;
	return false;
}

void Room::addGood(string good) {
	goods.push_back(Util::findItem(good));
	cout << "The item was successfully placed in the room!" << endl;
}