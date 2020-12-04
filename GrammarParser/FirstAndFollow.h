#pragma once
#include<set>
#include<string>
#include <vector>
#include <map>
using namespace std;
set<string> getFirst(vector<string>&);
multimap<string, string> getIndex(const vector<string>& production);
set<string> getNonTerminal(const vector<string>& production);