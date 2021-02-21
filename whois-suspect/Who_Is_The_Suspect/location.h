#pragma once
#include<vector>
#include <map>
#include<string>
#include "Config.h"
#include "Room.h"
using namespace std;

class Location
{
private:
	const vector<string> name{MAP};
	string locationName[3 + 2][4 + 2];
	string description[TOTALROOM+5];

	int crimeScene;
	//vector<> items;
	int wall[TOTALROOM+5][TOTALROOM+5];
public:
	Location();
	~Location()  = default;
	//从0开始计数的
	map<int, Room> rooms;
	static void printMap(string);
	static int getLocation(int x,int y);
	static void printFunction();
	void printDescription(int);
	void printRoomGoods(int room);
	string getName(int);
	int getCrimeScene() const { return crimeScene; }
	void setCrimeScene(int val) { crimeScene = val; }
	bool findConnectivity(int, int);
};