#pragma once
#include <vector>
#include "Location.h"
#include "Person.h"

/*
* @ClassName : Detective
* @Auther : treblez
* @E-Mail : treblez@126.com 
* @Date : 2020/11/04
* @Description : Use the singleton pattern to get the only Detective object
*/



class Detective :
	public Person
{
private:
	vector<string> note;
	static int a;
	static Detective* uniqueDetective;
	static vector<Item> Inventory;
	Detective();
public:
	bool deleteInventory(string);
	void move(string,Location*);
	static int * b;
	void printInventory();
	static Detective* getInstance(string,string);
	std::vector<Item> getInventory() const { return Inventory; }
	void addInventory(Item);
	void setInventory(std::vector<Item> val) { Inventory = val; }
};
