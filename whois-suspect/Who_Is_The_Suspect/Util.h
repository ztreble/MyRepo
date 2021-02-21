#pragma once
/*
* @ClassName : Util
* @Auther : treblez
* @E-Mail : treblez@126.com 
* @Date : 2020/11/03
* @Description : Tool class, do random
*/
#include <vector>
#include <iostream>
#include <random>
#include "Item.h"
using namespace std;
class Util
{


public:
	static vector<Item> allItem;
	static int getRandomRoom(int seed);
	static int getRandomItemNum();
	static Item getRandomItem();
	static int getRandomNum(int,int);
	static void printFunction();
	static int getX(int);
	static int getY(int);
	static int getLocation(int, int);
	static string getItemDescription(string name);
	static Item findItem(string);
 };

