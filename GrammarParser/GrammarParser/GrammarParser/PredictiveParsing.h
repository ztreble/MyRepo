#pragma once
#include <unordered_map>
#include <map>
#include<unordered_set>
#include <string>
#include "FirstAndFollow.h"
//#include <pair>
using namespace std;
using table_type = unordered_map<string, unordered_map<string, unordered_multimap<string, string>>>;
class PredictiveParsing
{
	
	
public:
	table_type predictiveAnalysisTable;
	void getPredictiveAnalysisTable(FirstAndFollow&);
	
};

