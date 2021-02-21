#pragma once
#include "FirstAndFollow.h"
class Util
{
public:
	/// <summary>
	/// 消除左递归
	/// </summary>
	/// <param name=""></param>
	static unordered_map<string, unordered_set<string>> eliminateTheLeftRecursion(unordered_set<string> ,unordered_map<string, unordered_set<string>>&);
	/// <summary>
	/// 消除直接左递归
	/// </summary>
	static unordered_map<string, unordered_set<string>> eliminateTheDirectLeftRecursion(unordered_map<string, unordered_set<string>>&);
	/// <summary>
	/// 判断是否是LL（1）文法
	/// 1.文法不含有左递归
	//	2.文法中每一个非终结符产生式中每一个候选式的FIRST集两两不相交（可以反复提取左公因子来接近这个条件）
	//	3.如果文法中的每一个非终结符的FIRST集若含有ε，则每一个候选式的FIRST集和该非终结符的FOLLOW集不相交
	/// </summary>
	/// <param name=""></param>
	/// <returns></returns>
	bool judLL1(FirstAndFollow&);

};

