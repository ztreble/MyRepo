#pragma once
#include<iostream>
#include<string>
#include<set>
#include<map>
#include <Windows.h>
#include <fstream>
#include<vector>
#include <unordered_map>
#include <unordered_set>
#include "Config.h"
using namespace std;
/// <summary>
/// 使用本类构建First和Follow集合，调用顺序为init(),splitProductions(),getFirst(),getFollow()
/// </summary>
class FirstAndFollow {
public:
	string fileName = PRODUCTIONRULE; 
	FirstAndFollow();
	/// <summary>
	/// 产生式集合
	/// </summary>
	/// un
	unordered_set<string> productions;
	
	/// <summary>
	/// 分解后的产生式集合
	/// </summary>
	unordered_map<string, unordered_set<string>> splitedProductions;
	/// <summary>
	/// 终结符集合
	/// </summary>
	unordered_set<string> terminalSymbol;
	/// <summary>
	/// 非终结符集合
	/// </summary>
	unordered_set<string> noneTerminal;
	/// <summary>
	/// First集
	/// </summary>
	unordered_map<string, unordered_set<string>> first;
	/// <summary>
	/// Follow集
	/// </summary>
	unordered_map<string, unordered_set<string>> follow;
	/// <summary>
	/// 设置目录
	/// </summary>
	/// <param name="name"></param>
	void setFileName(const string& name) { this->fileName = name; }
	/// <summary>
	/// 从文件读取产生式
	/// </summary>
	void init();
	/// <summary>
	/// 分解产生式
	/// </summary>
	void splitProductions();
	/// <summary>
	/// 获得终结符和非终结符
	/// </summary>
	void findVtAndVn();
	/// <summary>
	/// 判断是否是非终结符
	/// </summary>
	/// <param name="s">字符串s</param>
	/// <returns></returns>
	bool isVn(const string& s) const;
	/// <summary>
	/// 判断是否是终结符
	/// </summary>
	/// <param name="s">字符串s</param>
	/// <returns></returns>
	bool isVt(const string& s) const;
	unordered_set<string> getOneFirst(string s);//获得单个非终结符first集
	void getFirst();//获得所有非终结符first集
	/// <summary>
	/// 获取字符串的First集合
	/// </summary>
	/// <param name=""></param>
	/// <returns></returns>
	unordered_set<string> getSymbolStringFirst(const string&);
	void getFollow();//获得所有非终结符follow集
};