#include "FirstAndFollow.h"



/*****************************************************************************
* @Name : getIndex
* @Brief : Get index from production rules
* @Inparam : 
* @Outparam : 
*****************************************************************************/
multimap<string, string> getIndex(const vector<string>& production) {
	multimap<string, string> index;

	size_t productionSize = production.size();
	for (int i = 0; i < productionSize; i++) {
		
	}

	return index;
}




struct productionRule getNonTerminal(const vector<string>& production) {
	productionRule rule;
	
	set<string> terminal;
	set<string> nonTerminal;
	multimap<string, string> index;
	string nowProduction,nowRecordString;
	size_t productionSize = production.size();
	//获取非终结符，产生式规则
	for (int i = 0; i < productionSize; i++) {
		nowProduction = production[i];
		bool readString=false;
		string::size_type subScript = nowProduction.find_first_of(" ");

		if(subScript==nowProduction.npos){
			throw "Grammar analyze wrong!";
		}
		string 	nowNonTerminal = nowProduction.substr(0, subScript);
		nonTerminal.insert(nowNonTerminal);
		subScript = nowProduction.find_first_of("->");
		if (subScript == nowProduction.npos) {
			throw "Grammar analyze wrong!";
		}
		for (size_t j = subScript+2; j < nowProduction.size(); j++) {
			if (nowProduction.at(j) == '|')
				continue;
			else if (nowProduction.at(j) !=' '&&readString==false) {
				readString = true;
				nowRecordString = nowProduction.at(j);
			}
			else if (nowProduction.at(j) != ' '&&readString == true) {
				
				nowRecordString = nowRecordString.append(""+nowProduction.at(j));
			}
			else if (nowProduction.at(j) == ' '&&readString == true) {
				readString = false;
				terminal.insert(nowRecordString);
				index.insert(nowNonTerminal, nowRecordString);
			}

		}
	}
	for (const auto& deleteString:nonTerminal) {

	}
	return rule;
}



set<string> getFirst(const vector<string>& production) {
	set<string> first;
	bool flag = true;
	multimap<string, string> mul;
	return first;
}

