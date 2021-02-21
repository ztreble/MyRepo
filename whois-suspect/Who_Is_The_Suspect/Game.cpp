#include "Game.h"
#include <iostream>
#include <vector>
#include <thread>
#include "Suspect.h"
#include "Util.h"
#include <iostream>
#include <sstream>
#include "Detective.h"
#include "Location.h"
#include <fstream>
#include "Text.h"
using namespace std;
/*
* @ClassName : startGame
* @Auther : treblez
* @E-Mail : treblez@126.com 
* @Date : 2020/11/03
* @Description : 
*/

static vector<Suspect> suspect;
static vector<thread> threads;



/*
* @MethodName : randomWalk
* @Auther : treblez
* @Description : Determine the position of a character based on time rather than the number of turns
* @InputParm : 
* @OutParm : 
*/      
void randomWalk(int peoNum) {
	while (1) {
		std::this_thread::sleep_for(chrono::seconds(10+peoNum * 10));
		suspect[peoNum].setLocation(Util::getRandomRoom(peoNum*peoNum * 100));
		//cout <<"i am "<< suspect[peoNum].getName()<<" my location : "<< suspect[peoNum].getLocation() << endl;
	}
}

/*
* @MethodName : 
* @Auther : treblez
* @Description : Init the Game
* @InputParm : 
* @OutParm : 
*/
void init() {
	
	for (int i = 0; i < 6; i++) {
		stringstream ss;
		ss<<i;
		string name,description;
		ss >> name;
		ss >> description;
		suspect.push_back(Suspect(name,description));
	}
  
	for (int i = 0; i < 6; i++) {
		threads.push_back(thread(randomWalk,i));
	}

	for (auto& thread : threads) {
		thread.detach();
	}
	

	//while (1) {
	//	std::this_thread::sleep_for(chrono::seconds(1));
	//	cout << "main running" << endl;
	//}
}


/*
* @MethodName :startGame
* @Auther : treblez
* @Description :
* @InputParm :
* @OutParm : 
*/
void Game::startGame() {
	Location location;
	//从0开始计数
	int crimeScene = Util::getRandomNum(1, TOTALROOM);
	location.setCrimeScene(crimeScene);
	string func;
	string detectiveName;
	Text::begin();

	//确定人物姓名
	cout << endl;
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	cout << endl;
	cout << "\tFirst thing, worker, what's your name?";
	cin >> detectiveName;
	cout << endl;
	auto detective = Detective::getInstance(detectiveName, DETECTIVEDESCRIPTION);
	detective->setLocation(crimeScene);
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	cout << endl;
	cout << "\tOK,Go for it! " + detectiveName + "~!" << endl;
	cout << endl;
	//打印地图
	Location::printMap(location.getName(detective->getLocation()));
	//打印房间描述
	location.printDescription(detective->getLocation());
	cout << endl;
	//打印功能
	Util::printFunction();
	init();
	while (1) {
		cout << "Type your next action, and you can always type HELP or H to call out the HELP menu : ";
		cin >> func;
		if (func == "QUIT" || func == "Q" || func == "quit")
			exit(0);
		else if (func == "MAP" || func == "M" || func == "m")
			Location::printMap(location.getName(detective->getLocation()));
		else if (func == "U" || func == "D" || func == "L" || func == "R" || func == "u" || func == "d" || func == "l" || func == "r") {
			detective->move(func, &location);
			//锁住这个房间中的所有人
		}
			
		else if (func == "SEARCH" || func == "S"||func=="s") {
			location.printRoomGoods(detective->getLocation()-1);
		}
		/*else if (func.substr(0,7) == "EXAMINE" ) {
			string good = func.substr(9, func.length());
			Util::getDescription(good);
		}
		else if (func.substr(0, 1) == "X") {
			string good = func.substr(3, func.length());
			Util::getDescription(good);
		}*/
		else if (func == "EXAMINE"|| func == "X") {
			string good;
			cin >> good;
			cout << "Result:" << endl;
			cout << Util::getItemDescription(good);
		}
		else if (func == "DROP") {
			string good;
			cin >> good;
			auto judgeItem = detective->deleteInventory(good);
			if (judgeItem) {
				auto tmpRoom = location.rooms.at(detective->getLocation() - 1);

				tmpRoom.addGood(good);
				location.rooms[detective->getLocation() - 1] = tmpRoom;
			}
			else {
				cout << "This item is not in your backpack" << endl;
			}
		}
		else if (func == "PICK"||func=="P" ) {
			string good;
			cin >> good;
			//第一步，使用名字在location中删掉房间中的物品
			auto tmpRoom = location.rooms.at(detective->getLocation()-1);
			auto judgeItem = tmpRoom.deleteGoods(good);
			if (judgeItem) {
				location.rooms[detective->getLocation() - 1] = tmpRoom;
				//第二步，在背包中加入物品
				detective->addInventory(Util::findItem(good));
				cout << "SUCCESS!"<<endl;
			}

		}
		else if (func == "INV" || func == "I") {
			detective->printInventory();
		}
		else if (func == "Display") {
			cout << "The people in this room are:" << endl;
			for (auto a : suspect) {
				if (a.getLocation() == detective->getLocation())
					cout << a.getName()<<endl;
			}
		}
		else if (func == "QUESTION"||func=="Q") {
			string name;
			cin >> name;
			for (auto a : suspect) {
				if (a.getName() == name)
					cout << a.getAlibi()<<endl;
			}
		}
		else if (func == "ACCUSE" || func == "A") {
			string name;
			cin >> name;
			for (auto a : suspect) {
				if (a.getName() == name&&a.getMurder()==true)
					cout <<"You win the game!"<<endl;
				else {
					cout << "Your lost!" << endl;
				}
				return;

			}
		}
		else if (func == "HELP" || func == "H") {
			Util::printFunction();
		}
		else {
			cout << "Your input is incorrect!" << endl;
		}
	}


}
