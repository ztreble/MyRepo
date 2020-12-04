#pragma once
#include<set>
#include<string>
#include <vector>
#include <map>
using namespace std;

/*

*/
struct productionRule {
	/*
		first集
	*/
	multimap<string, string> first;
	/*
		产生式规则
	*/
	multimap<string, string> index;
	/*
		非终结符
	*/
	set<string> nonTerminal;
};

set<string> getFirst(const vector<string>&);
multimap<string, string> getIndex(const vector<string>& production);
//set<string> getNonTerminal(const vector<string>& production);
struct productionRule getNonTerminal(const vector<string>& production);