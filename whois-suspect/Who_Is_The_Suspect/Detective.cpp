#include "Detective.h"
#include<string.h>
#include "Util.h"

vector<Item> Detective::Inventory =  *(new vector<Item>) ;
Detective* Detective::uniqueDetective = nullptr;
Detective::Detective()
{}

Detective* Detective::getInstance(string name, string description) {
	
	if (uniqueDetective == nullptr) {
		uniqueDetective = new Detective();
		uniqueDetective->setName(name);
		uniqueDetective->setDescription(description);
	}
	
	return uniqueDetective;
}
/*
* @MethodName : move
* @Auther : treblez
* @Description : The realization of the left and right of the mobile logic U D L R
* @InputParm : 
* @OutParm : 
*/
void Detective::move(string position,Location* location) {
	int nowLocation = this->getLocation();
	volatile int x = Util::getX(nowLocation);
	volatile int y = Util::getY(nowLocation);
	if (position == "U"|| position == "u") {
		x -= 1;

		if (x <= 0) cout << "Explore the front area later!" << endl;
		else if (location->findConnectivity(Util::getLocation(x,y), Util::getLocation(x+1, y)))
		{
			this->setLocation(Util::getLocation(x, y));
			cout << "Now your location: " << location->getName(Util::getLocation(x, y)) << endl;
		}
		else{
			cout << "No Through Road!" << endl;
		}
	}else if (position == "D" || position == "d") {
		x += 1;
		if (y <= 0) cout << "Explore the front area later!" << endl;
		else if (location->findConnectivity(Util::getLocation(x, y), Util::getLocation(x-1, y)))
		{
			this->setLocation(Util::getLocation(x, y));
			cout << "Now your location: "<<location->getName(Util::getLocation(x, y))<<endl;
		}
		else {
			cout << "No Through Road!" << endl;
		}
	}
	else if (position == "L" || position == "l") {
		y-=1;
		if (y <= 0) cout << "Explore the front area later!" << endl;
		else if (location->findConnectivity(Util::getLocation(x, y), Util::getLocation(x, y+1)))
		{
			this->setLocation(Util::getLocation(x, y));
			cout << "Now your location: " << location->getName(Util::getLocation(x, y)) << endl;
		}
		else {
			cout << "No Through Road!" << endl;
		}
	}
	else if (position == "R" || position == "r") {
		y += 1;
		if (y <= 0) cout << "Explore the front area later!" << endl;
		else if (location->findConnectivity(Util::getLocation(x, y), Util::getLocation(x, y - 1)))
		{
			this->setLocation(Util::getLocation(x, y));
			cout << "Now your location: " << location->getName(Util::getLocation(x, y)) << endl;
		}
		else {
			cout << "No Through Road!" << endl;
		}
	}

}

void Detective::addInventory(Item item) {
	Inventory.push_back(item);
}

bool Detective::deleteInventory(string name) {
	for (auto iter = Inventory.begin(); iter != Inventory.end(); iter++) {
		if (iter->getName() == name) {
			Inventory.erase(iter);
			return true;
		}
			
	}
	return false;
}

void Detective::printInventory() {
	cout << "The contents of your bag:"<<endl;
	for (auto a : Inventory)
		cout << a.getName() << endl;
}