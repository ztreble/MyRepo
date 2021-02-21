#include "FirstRecursiveDescentImpl.h"


FirstRecursiveDescentImpl::FirstRecursiveDescentImpl(FirstAndFollow& ff) :schedule(0), temp(0), ff(ff),jud(true) {
	printf("请输入字符串（长度<50，以#号结束）:\n");
	cin >> tokens;
	//此功能暂时未实现
	//printf("%-10s %-20s %-10s %-10s\n", "文法", "分析串", "分析字符", "剩余串");
	parse_E();
}
/// <summary>
/// 使用XX代替X'
/// </summary>
void FirstRecursiveDescentImpl::parse_E() {
	if (jud) {
		parse_T();
		parse_EE();
		
	}
	if (schedule == tokens.length()) {
		cout << "匹配成功！" << endl;
	}
	return;
}
void FirstRecursiveDescentImpl::parse_EE() {
	if (jud) {
		string token;
		if (schedule >= tokens.size())
			token = "#";
		else if (schedule + 1 < tokens.size() && tokens[schedule + 1] == '\'')
			token = tokens[schedule] + tokens[schedule + 1];
		else token = tokens[schedule];

		auto nextFirst = ff.getSymbolStringFirst(string("+TE'"));
		if (nextFirst.find(tokens) != nextFirst.end()) {
			parse_plus();
			parse_T();
			parse_EE();
			return;
		}
		else if (tokens[schedule++] == '#') {
			jud = false;
			//匹配空集
			return;
		}
		error();
	}
}
void FirstRecursiveDescentImpl::parse_plus() {
	if (jud) {
		string token("" + tokens[schedule++]);
		if (token == "+")
			return;
		error();
	}
	return;
}
void FirstRecursiveDescentImpl::parse_T() {
	if (jud) {
		string token;
		if (schedule + 1 < tokens.size() && tokens[schedule + 1] == '\'')
			token = tokens[schedule] + tokens[schedule + 1];
		else token = tokens[schedule];
		auto nextFirst = ff.getSymbolStringFirst(string("FT'"));
		if (nextFirst.find(token) != nextFirst.end()) {
			parse_F();
			parse_TT();
			return;
		}
		error();
	}
	
	return;
}
void FirstRecursiveDescentImpl::parse_TT() {
	if (jud) {
		string token;
		if (schedule + 1 < tokens.size() && tokens[schedule + 1] == '\'')
			token = tokens[schedule] + tokens[schedule + 1];
		else token = tokens[schedule];
		auto nextFirst = ff.getSymbolStringFirst(string("*FT'"));
		if (nextFirst.find(token) != nextFirst.end()) {
			parse_star();
			parse_F();
			parse_TT();
			return;
		}
		else if (tokens[schedule++] == '#') {
			jud = false;
			//匹配空集
			return;
		}
		error();
	}
	
	return;
}
void FirstRecursiveDescentImpl::parse_F() {
	if (jud) {
		string token;
		if (schedule + 1 < tokens.size() && tokens[schedule + 1] == '\'')
			token = tokens[schedule] + tokens[schedule + 1];
		else token = tokens[schedule];
		auto nextFirst = ff.getSymbolStringFirst(string("(E)"));
		if (nextFirst.find(token) != nextFirst.end()) {
			parse_leftBracket();
			parse_E();
			parse_rightBracket();
			return;
		}
		else if (tokens[schedule++] == 'i') {
			
			return;
		}
		error();
	}
	return;
}


void FirstRecursiveDescentImpl::parse_star() {
	if (jud) {
		if (tokens[schedule++] == '*')
			return;
		error();
	}
	return;
}
void FirstRecursiveDescentImpl::parse_leftBracket() {
	if (jud) {
		if (tokens[schedule++] == '(')
			return;
		error();
	}
	return;
}
void FirstRecursiveDescentImpl::parse_rightBracket() {
	if (jud) {
		if (tokens[schedule++] == ')')
			return;
		error();
	}
	return;
}
void FirstRecursiveDescentImpl::parse_i() {
	if (jud) {
		if (tokens[schedule++] == 'i')
			return;
		error();
	}
	return;
}

void FirstRecursiveDescentImpl::error() {
	cout << "语法分析错误！" << endl;
}

