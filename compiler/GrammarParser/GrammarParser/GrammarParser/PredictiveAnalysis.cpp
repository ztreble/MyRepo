#include "PredictiveAnalysis.h"

//<summary>
//构造函数，begin是开始符号
//</summary>
//<param name="begin"></param>
PredictiveAnalysis::PredictiveAnalysis(const string& begin, const FirstAndFollow* ff,const  PredictiveParsing* pp) :S({ "#",begin }), iter(0), ff(ff), pp(pp) {

}
void PredictiveAnalysis::init() {
	printf("请输入字符串（长度<50，以#号结束）:\n");
	cin >> sentense;
	loopAndAnalyze();
}

void PredictiveAnalysis::loopAndAnalyze() {
	a = sentense.at(iter);
	string nowTop = S.top();
	S.pop();
	//终结符是Vt，非终结符是Vn
	//是终结符
	if (ff->isVt(nowTop)) {
		if (nowTop == "#" && a == "#") {
			cout << "匹配成功！" << endl;
			return;
		}
		else if (nowTop == a) {
			iter++;
			loopAndAnalyze();
		}
		else error();
	}
	//非终结符
	else {
		/*if (nowTop == "#" && a == "#") {
			return;
		}
		else*/ 
		{
		//
		auto tmpNow = pp->predictiveAnalysisTable.at(nowTop).at(a);
		if (tmpNow.empty()) {
			error();
		}
		else {
			string rightPart = tmpNow.find(nowTop)->second;
			if (rightPart != "#") {
				stack<string> tmpStack;
				for (int i = 0; i < rightPart.size(); i++) {
					if (i + 1 <= rightPart.size() && rightPart[i + 1] == '\'') {
						tmpStack.push(string({ rightPart[i] }).append("'"));
						i++;
					}
					else tmpStack.push(string({ rightPart[i] }));
				}
				while (!tmpStack.empty()) {
					S.push(tmpStack.top());
					tmpStack.pop();
				}
				//if(*(iter.operator*)=="#")
				
			}
			//非终结符，回调
			loopAndAnalyze();
		}
		}
	}
}
void PredictiveAnalysis::error() {
	throw "Predictive analysis error!";
}