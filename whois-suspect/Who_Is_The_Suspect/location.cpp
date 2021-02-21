#include "Location.h"
#include <iostream>
#include <string>
#include <sstream>
#include "Util.h"
#include "Config.h"
Location::Location(){

	for (int i = 1; i <= 3; i++)
		for (int j = 1; j <= 4; j++) {
			locationName[i][j] = name[(i - 1) * 4 + j - 1];
			Room* newRoom = new Room(locationName[i][j],description[(i - 1) * 4 + j - 1],false);
			int goodsNum;
			vector<Item> tmpGoods;
			goodsNum = Util::getRandomItemNum();
			while (goodsNum--) 
				tmpGoods.push_back(Util::getRandomItem());
			newRoom->setGoods(tmpGoods);

			switch ((i - 1) * 4 + j - 1)
			{
			
			case 0: {
		/*		newRoom->setGoods(Item())
				break;*/
				
			}
			default:
				break;
			}
			rooms.insert(pair<int, Room>((i - 1) * 4 + j - 1, *newRoom));

		}
		

	stringstream ss;
	ss << WALL;
	int a, b;
	for (int i = 0; i <= TOTALROOM; i++)
		for (int j = 0; j <= TOTALROOM; j++)
			wall[i][j] = 0;
	try {
		int ptr = 0;
		while (true) {
			ss >> a ;
			if (a == 0) break;
			ss >> b;
			if(a>TOTALROOM|| b > TOTALROOM || a<=0 || b<=0) 
				throw "Please check the parameter WALL";
			wall[a][b] = 1;
			wall[b][a] = 1;
			ptr++;
			if (ptr > MAXWARNING)
				throw "Data out of range";
		}
	}
	catch (const string& a) {
		cout << a;
	}

	

}
/*
* @MethodName : printMap
* @Auther : treblez
* @Description : For the sake of saving time, only 4*4 maps and fixed name are printed here
* @InputParm : 
* @OutParm : 
*/
void Location::printMap(string nowLocation){
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	cout << endl;
	cout << "\tMAP"<<endl;
	cout << "\t" <<"Now you are: "+nowLocation<< endl;
	cout << endl;
	cout << "\t+ - - - - - - - - - + - - - - - - - - - + - - - - - - - - - + - - - - - - - - - +" << endl;
	cout << "\t|                   |                   |                   |                   |" << endl;
	cout << "\t|     laundry              kitchen             wardrobe     |      lounge       |" << endl;
	cout << "\t|                   |                   |                   |                   |" << endl;
	cout << "\t+ - - - - - - - - - + - - -       - - - + - - - - - - - - - + - - -       - - - +" << endl;
	cout << "\t|                   |                   |                   |                   |" << endl;
	cout << "\t|       study              storage      |      bedroom             gallery      |" << endl;
	cout << "\t|                   |                   |                   |                   |" << endl;
	cout << "\t+ - - - - - - - - - + - - -       - - - + - - - - - - - - - + - - -       - - - +" << endl;
	cout << "\t|                   |                   |                   |                   |" << endl;
	cout << "\t      basement              entry               dining             bathroom     |" << endl;
	cout << "\t|                   |                   |                   |                   |" << endl;
	cout << "\t+ - - - - - - - - - + - - - - - - - - - + - - - - - - - - - + - - - - - - - - - +" << endl;
	cout << endl;
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;

}
string Location::getName(int room) {
	return this->name[room-1];
}
void Location::printDescription(int room) {
	string response="";
	switch (room)
	{
	default: {
		response += "The room is magnificent\n";
	}
		break;
	}
	if (room == this->crimeScene) response += "The room was where the murder had taken place, and the white marble was now stained with pitiless red blood.\n";
	cout << response;
	cout << "The objects in this room are:" << endl;
	Room nowRoom = rooms.at(room-1);


	for(auto good : nowRoom.getGoods())
		cout << good.getName() << endl;
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
}

bool Location::findConnectivity(int x, int y) {

	return (this->wall[x][y] == 1);
}

void Location::printRoomGoods(int room) {
	cout << "The objects in this room are:" << endl;
	Room nowRoom = rooms.at(room);

	for (auto good : nowRoom.getGoods())
		cout << good.getName() << endl;
}

