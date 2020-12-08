#include "FirstAndFollow.h"
using namespace std;

FirstAndFollow::FirstAndFollow() {
	cout << "使用本类构建First和Follow集合，调用顺序为init(),splitProductions(),getFirst(),getFollow()" << endl;
}

void FirstAndFollow::init() {
	string line;
	ifstream in(fileName);
	if (in) {
		getline(in, line);
		productions.insert(line);
		//文法开始符号的follow集中放入$
		//follow[line.substr(0, 1)].insert("$");
		cout << "文件中的输入是："<<endl;
		cout << line << endl;
		while (getline(in, line)) {
			productions.insert(line);
			cout << line << endl;
		}
	}
	else {
		throw "Read config file wrong!";
	}
	
}

void FirstAndFollow::splitProductions() {
	int position = 0;
	if (productions.empty())
		throw "未初始化或者文件有误";
	for (const std::string& nowProductionRule : productions) {
		position = nowProductionRule.find("->");
		string leftProduction = nowProductionRule.substr(0, position);
		string rightProduction = nowProductionRule.substr(position + static_cast <unsigned __int64>(2));
		unordered_set<string> nowRule;

		string rightMolecular;
		for (int j = 0; j < rightProduction.length(); j++) {
			if (rightProduction[j] == '|') {
				nowRule.insert(rightMolecular);
				rightMolecular = "";
			}
			else
			{
				rightMolecular.append(rightProduction.substr(j, 1));
			}
		}
		nowRule.insert(rightMolecular);
		splitedProductions.insert(pair<string, unordered_set<string>>(leftProduction, nowRule));
	}
	/// <summary>
	/// 单元测试
	/// </summary>
	cout << "分解后的产生式如下：" << endl;
	for (auto & splitedProduction : splitedProductions) {
		cout << splitedProduction.first << "    ";
		for (const auto & ii : splitedProduction.second) {
			cout << ii << "    ";
		}
		cout << endl;
	}
}

void FirstAndFollow::findVtAndVn() {
	if (productions.empty())
		throw "未初始化或者文件有误";
	for (std::string temp : productions) {
		for (int i = 0; i < temp.length(); i++) {
			if (temp[i] == '-' || temp[i] == '>' || temp[i] == '|')
				continue;
			//是大写字母
			if (temp[i] >= 'A' && temp[i] <= 'Z') {
				//后面带'
				if (temp[i + static_cast<unsigned __int64>(1)] == '\'') {
					noneTerminal.insert(temp.substr(i, 2));
					i++;
				}
				else {
					noneTerminal.insert(temp.substr(i, 1));
				}
			}
			//是终结符
			else
			{
				terminalSymbol.insert(temp.substr(i, 1));
			}
		}
	}
	cout << "非终结符" << endl;
	for (const auto & it : noneTerminal) {
		cout << it << endl;
	}
	cout << "终结符" << endl;
	for (const auto & it : terminalSymbol) {
		cout << it << endl;
	}
}

bool FirstAndFollow::isVn(const string& s) {
	if (noneTerminal.empty())
		throw "未初始化非终结符集合";
	if (noneTerminal.find(s) !=noneTerminal.end()) {
		return true;
	}
	return false;
}
bool FirstAndFollow::isVt(const string& s) {
	if (terminalSymbol.empty())
		throw "未初始化终结符集合";
	if (terminalSymbol.find(s) != terminalSymbol.end()) {
		return true;
	}
	return false;
}
unordered_set<string> FirstAndFollow::getOneFirst(string s) {
	if (splitedProductions.count(s) <= 0)	throw "终结符表达式有误或者未初始化";
	else {
		unordered_set<string> nowProduction = splitedProductions[s];
		for (const auto& it : nowProduction) {
			string nowRightPart = it;
			if (nowRightPart == "#")
				first[s].insert("#");
			else {
				int allIsNull = 0;
				for (int i = 0; i < nowRightPart.length(); i++) {
					//当前非终结符有#
					int flag = 0;
					if (nowRightPart[i + static_cast <unsigned __int64>(1)] == '\'') {
						unordered_set<string> nextFirst = getOneFirst(nowRightPart.substr(i, 2));
						for (const auto& next : nextFirst) {
							//此时要处理下一个符号
							if (next == "#")
								flag = 1;
							else first[s].insert(next);
						}
						i++;
					}
					else if (isVn(nowRightPart.substr(i, 1))) {
						unordered_set<string> nextFirst = getOneFirst(nowRightPart.substr(i, 1));
						for (const auto& next : nextFirst) {
							//此时要处理下一个符号
							if (next == "#")
								flag = 1;
							else first[s].insert(next);
						}
					}
					else if(isVt(nowRightPart.substr(i, 1))){
						string terminalTemp = nowRightPart.substr(i, 1);
						first[s].insert(terminalTemp);
					}
					if (i==nowRightPart.length()-1&&flag ==1)
					{
						allIsNull = 1;
					}
					if (flag == 0)
						break;
				}
				if (allIsNull)
					first[s].insert("#");
			}
		}
	}
	return first[s];
}


void FirstAndFollow::getFirst() {
	for (auto& split_production : splitedProductions) {
		getOneFirst(split_production.first);
	}
	for (auto& terminal : terminalSymbol) {
		first[terminal].insert(terminal);
	}
	/// <summary>
	/// 单元测试
	/// </summary>
	cout << "First集" << endl;
	for (auto& it : first) {
		cout << it.first <<((it.first.length()==1)?"  ":" ")<< ": { ";
		for (const auto & ii : it.second)
		{
			cout <<" "<< ii << " ;";
		}
		cout <<"\b"<<" }"<< endl;
	}
}

unordered_set<string> FirstAndFollow::getSymbolStringFirst(const string& symbolString) {
	decltype(getSymbolStringFirst(symbolString)) First;
	string nowString;
	bool continueFlag = true;
	for (int i = 0; i <= symbolString.size()&& continueFlag; i++) {
		continueFlag = false;
		if (i + 1 <= symbolString.size() - 1 && symbolString[i + 1] == '\'') {
			nowString = symbolString.substr(i, 2);
			if (first.find(nowString) != first.end()) {
				auto copyString = first[nowString];
				for (const auto& i :copyString) {
					if (i == "#") continueFlag = true;
					else First.insert(i);
				}
			}
		}
		else{
			nowString = symbolString.substr(i,1);
			if (first.find(nowString) != first.end()) {
				auto copyString = first[nowString];
				for (const auto& i : copyString) {
					if (i == "#") continueFlag = true;
					else First.insert(i);
				}
			}
		}
	}
	return First;
}

void FirstAndFollow::getFollow() {
	bool isFirst = true;
	size_t begSize = 0;
	size_t endSize = 0;
	for(const auto& var : follow)
	{
		begSize += (var.second.size());
	}
	for (const auto& nowProduction : splitedProductions) {
		
		if (isFirst) {
			follow[nowProduction.first].emplace(string{ "#" });
			isFirst = false;
		}
		unordered_set<string>  RightPart = nowProduction.second;
		for (const string& nowRightPart : RightPart) {
			for (int i = 0; i < nowRightPart.size(); i++) {
	//			//最后一个元素，小心i不能是'
				if (i == nowRightPart.size()-1 && isVn({ nowRightPart[i] })) {
					for(const auto& nowCopy: follow[nowProduction.first])
						follow[to_string(nowRightPart[i])].emplace(nowCopy);
					//existFollow.emplace(follow(nowProduction.first));
				}
				//跟随'的非终结符
				else if (nowRightPart[i + static_cast < unsigned __int64>(1)] == '\'') {
					string nowB = string({nowRightPart[i]}).append("'");
					//后面没有元素了
					if (i + static_cast < unsigned __int64>(1) == nowRightPart.size() - 1) {
						for (const auto& nowCopy : follow[nowProduction.first])
							follow[nowB].emplace(nowCopy);
					}
					//后面至少有两个元素
					else if (i + 3 <= nowRightPart.size() - 1) {
						if (nowRightPart[i + 3] == '\'') {
							//下一个非终结符的first集合包含#
							string tmpB = string({nowRightPart[i + 2]}).append("'");
							if (first[tmpB].find("#") != first[tmpB].end()) {
								for (const auto& nowCopy : follow[nowProduction.first])
									follow[nowB].emplace(nowCopy);
							}
							//不包含#
							{
								unordered_set<string> tmpFirst = first[tmpB];
								tmpFirst.erase("#");
								for (const auto& nowCopy : tmpFirst)
									follow[nowB].emplace(nowCopy);
							}
						}
						else/* if (isVn({nowRightPart[i + 2]}))*/ {
							string tmpB = {nowRightPart[i + 2]};
							if (first[tmpB].find("#") != first[tmpB].end()) {
								for (const auto& nowCopy : follow[nowProduction.first])
									follow[nowB].emplace(nowCopy);
							}
							{
								unordered_set<string> tmpFirst = first[tmpB];
								tmpFirst.erase("#");
								for (const auto& nowCopy : tmpFirst)
									follow[nowB].emplace(nowCopy);
							}
						}
					}
					//后面只有一个元素
					else /*if (isVn({nowRightPart[i + 2]}))*/ {
						string tmpB ={ nowRightPart[i + 2]};
						if (first[tmpB].find("#") != first[tmpB].end()) {
							for (const auto& nowCopy : follow[nowProduction.first])
								follow[nowB].emplace(nowCopy);
						}
						 {
							unordered_set<string> tmpFirst = first[tmpB];
							tmpFirst.erase("#");
							for (const auto& nowCopy : tmpFirst)
								follow[nowB].emplace(nowCopy);
						}
					}
					i++;
				}
				//普通的非终结符
				else if (isVn({nowRightPart[i]})) {
					string nowB ={ nowRightPart[i]};
					//后面没有元素了
					if (i  == nowRightPart.size() - 1) {
						for (const auto& nowCopy : follow[nowProduction.first])
							follow[nowB].emplace(nowCopy);

					}
					//后面至少有两个元素
					else if (i + 2 <= nowRightPart.size() - 1) {
						if (nowRightPart[i + 2] == '\'') {
							//下一个非终结符的first集合包含#
							string tmpB = string({ nowRightPart[i + 1] }).append("'");
							if (first[tmpB].find("#") != first[tmpB].end()) {
								for (const auto& nowCopy : follow[nowProduction.first])
									follow[nowB].emplace(nowCopy);
							}
							{
								unordered_set<string> tmpFirst = first[tmpB];
								tmpFirst.erase("#");
								for (const auto& nowCopy : tmpFirst)
									follow[nowB].emplace(nowCopy);
							}
						}
						else /*if (isVn({nowRightPart[i + 1]}))*/ {
							string tmpB ={ nowRightPart[i + 1]};
							if (first[tmpB].find("#") != first[tmpB].end()) {
								for (const auto& nowCopy : follow[nowProduction.first])
									follow[nowB].emplace(nowCopy);
							}
							{
								unordered_set<string> tmpFirst = first[tmpB];
								tmpFirst.erase("#");
								for (const auto& nowCopy : tmpFirst)
									follow[nowB].emplace(nowCopy);
							}
						}
					}
					//后面只有一个元素
					else /*if (isVn({nowRightPart[i + 1]})) */{
						string tmpB ={ nowRightPart[i + 1]};
						if (first[tmpB].find("#") != first[tmpB].end()) {
							for (const auto& nowCopy : follow[nowProduction.first])
								follow[nowB].emplace(nowCopy);
						}
						{
							unordered_set<string> tmpFirst = first[tmpB];
							tmpFirst.erase("#");
							for (const auto& nowCopy : tmpFirst)
								follow[nowB].emplace(nowCopy);
						}
					}
				}
				//终结符
				else {
					continue;
				}
			}
		}

	}
	for (const auto& var : follow)
	{
		endSize += (var.second.size());
	}
	if (begSize != endSize) {
		getFollow();
		return;
	}
	/// <summary>
	/// 单元测试
	/// </summary>
	cout << "Follow集" << endl;
	for (auto& it : follow) {
		cout << it.first << ((it.first.length() == 1) ? "  " : " ") << ": { ";
		for (const auto& ii : it.second)
		{
			cout << " " << ii << " ;";
		}
		cout << "\b" << " }" << endl;
	}
}