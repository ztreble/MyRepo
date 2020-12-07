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
	/// <summary>
	/// 返回值
	/// </summary>
	productionRule rule;
	/// <summary>
	/// 符号表
	/// </summary>
	set<string> symbol;
	bool continueScann=true;
	/// <summary>
	/// First集
	/// </summary>
	map<string, set<string>> first;
	/// <summary>
	/// 终结符
	/// </summary>
	set<string> terminal;
	/// <summary>
	/// 非终结符
	/// </summary>
	set<string> nonTerminal;
	/// <summary>
	/// 非终结符产生式规则
	/// </summary>
	map<string, set<string>> index;
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
		symbol.insert(nowNonTerminal);
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
				string appendStr(1,nowProduction.at(j));
				nowRecordString.append(appendStr);
			}
			else if ((nowProduction.at(j) == ' ')&&readString == true) {
				readString = false;
				symbol.insert(nowRecordString);
				terminal.insert(nowRecordString);
				map<string, set<string>>::iterator iter = index.find(nowNonTerminal);
				if(iter==index.end())
					index.insert(pair<string,set<string>>(nowNonTerminal,set<string>{nowRecordString}));
				else {
					set<string> productionSet = iter->second;
					productionSet.insert(nowRecordString);
					index.insert(pair<string, set<string>>(nowNonTerminal, productionSet));
				}
			}

		}
	}
	for (const auto& deleteString:nonTerminal) {
		terminal.erase(deleteString);
	}

	while (continueScann) {
		continueScann = false;
		//遍历一遍符号表
		for (const auto& nowSymbol : symbol) {
			//如果是终结符，FIRST(X)={X}
			if (terminal.find(nowSymbol) != terminal.end()) {
				
				map<string, set<string>>::iterator iter = first.find(nowSymbol);
				//first集中没有
				if (iter == first.end()) {
					first.insert(pair<string, set<string>>(nowSymbol, set<string>{nowRecordString}));
					continueScann = true;
				}
			}
			//如果是非终结符
			else if (nonTerminal.find(nowSymbol) != nonTerminal.end()) {
				size_t size;
				//寻找规则
				map<string, set<string>>::iterator indexIter = index.find(nowSymbol);
				if(indexIter==index.end())
					throw "Grammar analyze wrong!";
				else {
					//vector<string> nowNonTerminalFirst = indexIter->second;
					//找到右部
					set<string> nowFirst;
					map<string, set<string>>::iterator firstIter = first.find(nowSymbol);
					//现在的右部是空
					if (firstIter == index.end())
						size=0;
					else {
						nowFirst = firstIter->second;
						size = nowFirst.size();
					}
					set<string> rightSection = indexIter->second;
					for (auto nowSection : rightSection) {
						nowFirst.insert(nowSection);
						//Y是非终结符
						if (nonTerminal.find(nowSection) != nonTerminal.end()) {
							set<string> YFirst= first.find(nowSection)->second;
							for (auto nowYSection : YFirst) {
								if(nowYSection!="~")
									nowFirst.insert(nowYSection);
							}
						}
					}
					if (size != nowFirst.size())
						continueScann = true;

					first.insert(pair<string, set<string>>(nowSymbol,nowFirst));
				}
			}
			else throw "Grammar analyze wrong!";
		}

		
	}
	

	return rule;
}



set<string> getFirst(const vector<string>& production) {
	set<string> first;
	bool flag = true;
	multimap<string, string> mul;
	return first;
}

