#include "PredictiveParsing.h"
void PredictiveParsing::getPredictiveAnalysisTable(FirstAndFollow& ff) {
	unordered_map<string, unordered_set<string>> splitedProductions = ff.splitedProductions;

	for (const auto& nowProductions : splitedProductions) {
		string left = nowProductions.first;
		for (const auto& nowRight : nowProductions.second) {
			auto nowFirst = ff.getSymbolStringFirst(nowRight);
			for (const auto& symbol : nowFirst) {
				if (ff.isVt(symbol)) {
					predictiveAnalysisTable[symbol][left].insert(nowProductions);
					predictiveAnalysisTable[left][symbol].insert(nowProductions);
				}
				else if (symbol == "#") {
					auto follow = ff.follow[left];
					for (const auto& followMember : follow) {
						if (ff.isVt(followMember)) {
							predictiveAnalysisTable[followMember][left].insert(nowProductions);
							predictiveAnalysisTable[left][followMember].insert(nowProductions);
						}
					}
				}
			}
		}
	}

	/// <summary>
	/// 测试用例，输出表达式左部来验证正确性
	/// </summary>
	/// <param name="ff"></param>
	for (const auto& test : predictiveAnalysisTable) {
		cout << test.first<<endl;
		for (const auto& secondTier : test.second) {
			cout << secondTier.first << endl;
			for (const auto& thirdTier : secondTier.second) {
				cout << thirdTier.first << endl;
			}
		}
	}

}