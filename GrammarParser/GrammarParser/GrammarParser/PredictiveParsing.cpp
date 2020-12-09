#include "PredictiveParsing.h"
void PredictiveParsing::getPredictiveAnalysisTable(FirstAndFollow& ff) {
	unordered_map<string, unordered_set<string>> splitedProductions = ff.splitedProductions;

	for (const auto& nowProductions : splitedProductions) {
		string left = nowProductions.first;
		for (const auto& nowRight : nowProductions.second) {
			auto nowFirst = ff.getSymbolStringFirst(nowRight);
			for (const auto& symbol : nowFirst) {
				if (symbol != "#"&&ff.isVt(symbol)) {
					predictiveAnalysisTable[left][symbol].insert({left,nowRight});
				}
				else if (symbol == "#") {
					auto follow = ff.follow[left];
					for (const auto& followMember : follow) {
						if (ff.isVt(followMember)) {
							predictiveAnalysisTable[left][followMember].insert({ left,nowRight });
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
	cout << "语法分析表" << endl;
	auto terminalLength = ff.terminalSymbol.size();
	auto nonTerminalLength = ff.noneTerminal.size();
	for (int i = 0; i <= (terminalLength + 1) * 10-2; i++)
		printf("-");
	puts("");
	printf("|%9s", "|");
	for(const auto& terminal :ff.terminalSymbol)
		printf_s("%5s%5s", terminal.c_str(), "|");
	puts("");
	for (int i = 0; i <= (terminalLength + 1) * 10-2; i++)
		printf("-");
	puts("");
	for (const auto& nonTerminal : ff.noneTerminal)
	{
		printf_s("|%5s%4s", nonTerminal.c_str(), "|");
		
		for (const auto& terminal : ff.terminalSymbol)
			if(predictiveAnalysisTable[nonTerminal][terminal].empty())	printf("%10s", "|");
			else for (const auto& nowstring : predictiveAnalysisTable[nonTerminal][terminal]) {
					printf_s("%7s%3s", nowstring.second.c_str(), "|");
			}

		puts("");
		for (int i = 0; i <= (terminalLength + 1) * 10-2; i++)
			printf("-");
		puts("");
	}
		/*for (const auto& test : predictiveAnalysisTable) {
			cout << "横坐标是："<<test.first<<endl;
			for (const auto& secondTier : test.second) {
				cout << "纵坐标是："<<secondTier.first << endl;
				for (const auto& thirdTier : secondTier.second) {
					cout << "左部是："<<thirdTier.first << endl;
					cout << "-->" << thirdTier.second<<endl;
				}
			}
		}*/
	
}