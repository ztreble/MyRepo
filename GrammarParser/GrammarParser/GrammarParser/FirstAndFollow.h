#include<iostream>
#include<string>
#include<set>
#include<map>
#include <Windows.h>
#include <fstream>

using namespace std;

/*
所有非终结符均为单个字符，#代表空串
E->TE'
E'->+TE'|#
T->FT'
T'->*FT'|#
F->(E)|i
*/

class FF {
public:
	string fileName = "productions.txt";
	set<string> productions;//产生式集合
	map<string, set<string>> split_productions;//分解后的产生式集合
	set<string> Vt;//终结符集合
	set<string> Vn;//非终结符集合
	map<string, set<string>> first;//First集
	map<string, set<string>> follow;//Follow集

	void init();//从文件读取产生式
	void splitProductions();//分解产生式
	void findVtAndVn();//获得终结符和非终结符
	bool isVn(string s);
	bool isVt(string s);
	set<string> getOneFirst(string s);//获得单个非终结符first集
	void getFirst();//获得所有非终结符first集
	void getFollow();//获得所有非终结符follow集
	void SSS();//求folloe集的步骤3
};

void FF::init() {
	string line;
	ifstream in(fileName);
	if (in) {
		//文法开始符号的follow集中放入$
		getline(in, line);
		productions.insert(line);
		follow[line.substr(0, 1)].insert("$");
		cout << line << endl;
		while (getline(in, line)) {
			productions.insert(line);
			cout << line << endl;
		}
	}
}
void FF::splitProductions() {
	int position = 0;
	for (set<string>::iterator it = productions.begin(); it != productions.end(); it++) {
		string temp = *it;
		for (int i = 0; i < temp.length(); i++) {
			position = temp.find("->");
			string s = temp.substr(0, position);
			string ss = temp.substr(position + 2);
			set<string>sss;
			string t;
			for (int j = 0; j < ss.length(); j++) {
				if (ss[j] == '|') {
					sss.insert(t);
					t = "";
				}
				else
				{
					t.append(ss.substr(j, 1));
				}

			}
			sss.insert(t);
			split_productions.insert(pair<string, set<string>>(s, sss));
		}
	}
	for (map<string, set<string>>::iterator it = split_productions.begin(); it != split_productions.end(); it++) {
		cout << it->first << "    ";
		for (set<string>::iterator ii = it->second.begin(); ii != it->second.end(); ii++) {
			cout << *ii << "    ";
		}
		cout << endl;
	}


}
void FF::findVtAndVn() {
	for (set<string>::iterator it = productions.begin(); it != productions.end(); it++) {
		string temp = *it;
		for (int i = 0; i < temp.length(); i++) {
			if (temp[i] == '-' || temp[i] == '>' || temp[i] == '|')
				continue;
			//是大写字母
			if (temp[i] >= 'A' && temp[i] <= 'Z') {
				//后面带'
				if (temp[i + 1] == '\'') {
					Vn.insert(temp.substr(i, 2));
					i++;
				}
				else {
					Vn.insert(temp.substr(i, 1));
				}
			}
			//是终结符
			else
			{
				Vt.insert(temp.substr(i, 1));
			}
		}
	}

	cout << "非终结符" << endl;
	for (set<string>::iterator it = Vn.begin(); it != Vn.end(); it++) {
		cout << *it << endl;
	}

	cout << "终结符" << endl;
	for (set<string>::iterator it = Vt.begin(); it != Vt.end(); it++) {
		cout << *it << endl;
	}
}
bool FF::isVn(string s) {
	if (Vn.find(s) != Vn.end()) {
		return true;
	}
	return false;
}
bool FF::isVt(string s) {
	if (Vt.find(s) != Vt.end()) {
		return true;
	}
	return false;
}
set<string> FF::getOneFirst(string s) {
	if (split_productions.count(s) > 0) {
		set<string>temp = split_productions[s];
		for (set<string>::iterator it = temp.begin(); it != temp.end(); it++) {
			string stemp = *it;
			if (stemp == "#") {
				first[s].insert("#");
			}
			else {
				int flagAll = 0;//所有的非终结符的first集都有#；
				for (int i = 0; i < stemp.length(); i++) {
					int flag = 0;//当前的非终结符的first集有#；
					if (stemp[i + 1] == '\'') {//带'的非终结符
						set<string>t1 = getOneFirst(stemp.substr(i, 2));
						for (set<string>::iterator ii = t1.begin(); ii != t1.end(); ii++) {
							if (*ii == "#") {//此时空串不可插入
								flag = 1;
							}
							else {
								first[s].insert(*ii);
							}
						}
						i++;
					}
					else if (isVn(stemp.substr(i, 1)))//单个非终结符
					{
						set<string>t2 = getOneFirst(stemp.substr(i, 1));
						for (set<string>::iterator ii = t2.begin(); ii != t2.end(); ii++) {
							if (*ii == "#") {//此时空串不可插入
								flag = 1;
							}
							else {
								first[s].insert(*ii);
							}
						}
					}
					else {//终结符
						first[s].insert(stemp.substr(i, 1));
					}
					if (i == stemp.length() - 1 && flag == 1) {
						flagAll = 1;
					}
					if (flag == 0)
						break;

				}
				if (flagAll == 1) {
					first[s].insert("#");
				}
			}
		}
	}
	return first[s];
}
void FF::getFirst() {
	for (map<string, set<string>>::iterator it = split_productions.begin(); it != split_productions.end(); it++) {
		getOneFirst(it->first);
	}
	cout << "First集" << endl;
	for (map<string, set<string>>::iterator it = first.begin(); it != first.end(); it++) {
		cout << it->first << "  :  ";
		for (set<string>::iterator ii = it->second.begin(); ii != it->second.end(); ii++)
		{
			cout << *ii << "    ";
		}
		cout << endl;
	}

}
void FF::getFollow() {
	for (map<string, set<string>>::iterator it = split_productions.begin(); it != split_productions.end(); it++) {
		string left = it->first;
		set<string>right = it->second;
		for (set<string>::iterator ii = right.begin(); ii != right.end(); ii++) {
			string temp = *ii;

			for (int i = 0; i < temp.length(); i++) {
				if (isVt(temp.substr(i, 1))) {//终结符
					continue;
				}
				else if (i + 1 < temp.length() && temp[i + 1] == '\'') {//带有’的非终结符
					if (isVt(temp.substr(i + 2, 1))) {//非终结符后面是终结符
						follow[temp.substr(i, 2)].insert(temp.substr(i + 2, 1));
						i++;
					}
					else {//非终结符后面是非终结符s
						//把后面非终结符的first集ff加入follow集中
						string s;
						if (i + 3 < temp.length() && temp[i + 3] == '\'') {
							s = temp.substr(i + 2, 2);
						}
						else {
							s = temp.substr(i + 2, 1);
						}
						set<string> ff = first[s];
						for (set<string>::iterator nn = ff.begin(); nn != ff.end(); nn++) {
							if (*nn != "#")
								follow[temp.substr(i, 2)].insert(*nn);
						}
					}
				}
				else {//不带’的非终结符

					if (i + 1 < temp.length() && isVt(temp.substr(i + 1, 1))) {//非终结符后面是终结符
						follow[temp.substr(i, 1)].insert(temp.substr(i + 1, 1));
						i++;
					}
					else {//非终结符后面是非终结符s
						//把后面非终结符的first集ff加入follow集中
						string s;
						if (i + 2 < temp.length() && temp[i + 2] == '\'') {
							s = temp.substr(i + 1, 2);
						}
						else {
							s = temp.substr(i + 1, 1);
						}
						set<string> ff = first[s];
						for (set<string>::iterator nn = ff.begin(); nn != ff.end(); nn++) {
							if (*nn != "#")
								follow[temp.substr(i, 1)].insert(*nn);
						}
					}
				}
			}
		}
	}
	//这一个需要多进行几次，因为follow是不断增长的
	SSS();
	SSS();

	cout << "Follow集" << endl;
	for (map<string, set<string>>::iterator it = follow.begin(); it != follow.end(); it++) {
		cout << it->first << "  :  ";
		for (set<string>::iterator ii = it->second.begin(); ii != it->second.end(); ii++)
		{
			cout << *ii << "    ";
		}
		cout << endl;
	}
}
void FF::SSS() {
	for (map<string, set<string>>::iterator it = split_productions.begin(); it != split_productions.end(); it++) {
		string left = it->first;
		set<string>right = it->second;
		for (set<string>::iterator ii = right.begin(); ii != right.end(); ii++) {
			string temp = *ii;
			for (int j = temp.length() - 1; j > 0; j--) {
				string now;
				if (temp[j] == '\'') {
					now = temp.substr(j - 1, 2);
					j--;
				}
				else now = temp.substr(j, 1);
				if (isVt(now)) {//产生式最后是终结符
					break;
				}
				else {//产生式最后是非终结符
					set<string>aa = follow[left];
					for (set<string>::iterator pp = aa.begin(); pp != aa.end(); pp++) {
						follow[now].insert(*pp);
					}
				}
				if (first[now].find("#") == first[now].end())
					break;
			}
		}
	}
}
int main() {
	FF ff;
	ff.init();
	ff.splitProductions();
	ff.findVtAndVn();
	ff.getFirst();
	ff.getFollow();
}