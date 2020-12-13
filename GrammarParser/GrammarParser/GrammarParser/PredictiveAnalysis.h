#pragma once
#include <string>
#include <stack>
#include "FirstAndFollow.h"
#include "PredictiveParsing.h"
using namespace std;
/// <summary>
/// 预测分析法实现类
/// </summary>
class PredictiveAnalysis
{
public:
	/// <summary>
	/// S栈，用于存放比对符号
	/// </summary>
	stack<string> S;
	/// <summary>
	/// 当前输入符号
	/// </summary>
	string a;
	/// <summary>
	/// 遍历序指针
	/// </summary>
	int iter;
	/// <summary>
	/// 输入语句
	/// </summary>
	string sentense;
	explicit PredictiveAnalysis(const string& begin,  const FirstAndFollow* ff,const  PredictiveParsing* pp);
	void loopAndAnalyze();
	void error();
	void init();
	const FirstAndFollow* ff;
	const PredictiveParsing* pp;
};

