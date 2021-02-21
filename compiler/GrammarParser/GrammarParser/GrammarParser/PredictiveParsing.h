#pragma once
#include <unordered_map>
#include <map>
#include<unordered_set>
#include <string>
#include "FirstAndFollow.h"
//#include <pair>
using namespace std;
using table_type = unordered_map<string, unordered_map<string, unordered_multimap<string, string>>>;
/// <summary>
/// 预测分析表实现类 预测分析表只支持LL(1)文法，使用之前需要检查文法 unordered_multimap是为了未来支持扩展
/// </summary>
class PredictiveParsing
{
public:
	table_type predictiveAnalysisTable;
	void getPredictiveAnalysisTable(FirstAndFollow&);
};

