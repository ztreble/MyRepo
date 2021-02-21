#include "FirstAndFollow.h"



/*****************************************************************************
* @Name : getIndex
* @Brief : Get index from production rules
* @Inparam : 
* @Outparam : 
*****************************************************************************/
multimap<string, string> getIndex(const vector<string>& production) {

}

set<string> getNonTerminal(const vector<string>& production) {
	set<string> nonTerminal;
	/*
	S  -> AB
	A  -> Ca | ~
	B  -> cB'
	B' -> aACB' | ~
	C  -> b | ~
	*/
	string nowString;
	int productionSize = production.size();
	for (int i = 0; i < productionSize; i++) {
		nowString = production[i];
		string::size_type subScript = nowString.find("->");
		if(subScript==nowString.npos){
			throw "Non-terminal analyze wrong!";
		}
		nonTerminal.insert(nowString.substr(0, subScript));
	}
	return nonTerminal;
}



set<string> getFirst(const vector<string>& production) {
	bool flag = 1;
	multimap<string, string> mul;

}

