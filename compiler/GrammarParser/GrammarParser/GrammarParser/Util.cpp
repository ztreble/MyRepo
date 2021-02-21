#include "Util.h"
#include <string>
unordered_map<string, unordered_set<string>> Util::eliminateTheLeftRecursion(unordered_set<string> noneTerminal,unordered_map<string, unordered_set<string>>& splitedProductions) {
	//循环替代间接左递归
	vector<string> order;
	for (const auto& a : noneTerminal)
		order.push_back(a);
	int size = noneTerminal.size();
	for (int i=0; i < size; i++) {
		//检测递归的非终结符
		string Si = order[i];
		//此非终结符的产生式
		unordered_set<string> nowRight = splitedProductions[Si];
		for (int j = 0; j < i; j++) {
			//当前检测的产生式左部的符号
			std::string Sj = order[j];
			unordered_set<string> nowReplaceRight = splitedProductions[Sj];
			pair<string, unordered_set<string>> returnPair;
			auto tmpTraverse = nowReplaceRight;
			for (const auto& rightPart : tmpTraverse) {
				//出现冲突
				if (rightPart.size() >= Si.length()&&Si == rightPart.substr(0, Si.length())) {
					string tmpStr = rightPart.substr(Si.length(), rightPart.size()- Si.length());
					//删除这个产生式
					nowReplaceRight.erase(rightPart);
					for (auto replacePart : nowRight) {
						if (replacePart=="#")
						{
							replacePart = "";
						}
						//插入替换后的直接左递归产生式
						nowReplaceRight.insert(replacePart + tmpStr);
					}
				}
			}
			splitedProductions.erase(Sj);
			//检测完全部产生式之后，替换为新的规则
			
			splitedProductions.insert(pair<string, unordered_set<string>>(Sj, nowReplaceRight));
		}
		
	}
	//消除直接左递归
	//S->Sabc|abc|bc|c
	eliminateTheDirectLeftRecursion(splitedProductions);
	return splitedProductions;
}

unordered_map<string, unordered_set<string>> Util::eliminateTheDirectLeftRecursion(unordered_map<string, unordered_set<string>>& splitedProductions) {

	for (const auto& nowProduction : splitedProductions) {
		bool flag = false;
		string nowLeft = nowProduction.first;
		unordered_set<string> nowRight = nowProduction.second;
		string possibleNewLeft = nowLeft ;
		unordered_set<string> possibleNewRight;
		string possiblAddLeft = nowLeft + "'";
		unordered_set<string> possibleAddRight;
		string possibleRight;
		for (const auto& nowRightPart : nowRight){
			if (nowLeft ==nowRightPart.substr(0,nowLeft.length())) {
				//出现冲突，启动消除
				flag = true;
				break;
			}
		}
		if (flag) {
			splitedProductions.erase(nowLeft);
			for (const auto& nowRightPart : nowRight) {
				//首位相等
				if (nowLeft == nowRightPart.substr(0, nowLeft.length())) {
					
					possibleAddRight.insert(nowRightPart.substr(nowLeft.length(), nowRightPart.length() - nowLeft.length())+ possiblAddLeft);
				}
				else {
					if (nowRightPart == "#")
						possibleNewRight.insert(possiblAddLeft);
					else
					possibleNewRight.insert(nowRightPart+possiblAddLeft );
				}
			}
			possibleAddRight.insert("#");
			splitedProductions.insert(pair<string, unordered_set<string>>(possibleNewLeft, possibleNewRight));
			splitedProductions.insert(pair<string, unordered_set<string>>(possiblAddLeft, possibleAddRight));
		}


	}

	return splitedProductions;
}