#pragma once
#include "FirstAndFollow.h"
/// <summary>
// 递归下降语法的实现类，使用的语法是
/// E->TE'
/// E'->+TE' | #
/// T->FT'
/// T'->*FT' | #
//	F->(E) | i
/// </summary>
class FirstRecursiveDescentImpl 
{
public:
	FirstRecursiveDescentImpl(FirstAndFollow&);
	/// <summary>
	/// 当前遍历进度
	/// </summary>
	int schedule;
	string tokens;
	char analysisw[50];
	FirstAndFollow ff;
	/// <summary>
	/// 当前分析的串的所处的文法位置
	/// </summary>
	int temp;
	/// <summary>
	/// 判断有没有匹配完
	/// </summary>
	bool jud;
	void error();
	void output(int count);
	void parse_EE();
	void parse_E();
	void parse_T();
	void parse_TT();
	void parse_F();
	void parse_plus();
	void parse_star();
	void parse_leftBracket();
	void parse_rightBracket();
	void parse_i();
};
