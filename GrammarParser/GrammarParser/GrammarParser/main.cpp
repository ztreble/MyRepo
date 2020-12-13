/*************************************************************************************************
 *   く__,.ヘヽ.　　　　/　,ー､ 〉	
 *　　　　　＼ ', !-a a - i 　/　/´				
 *   　　　 　 ／｀ｰ'　　　 L/／｀ヽ､
 *   　　 　 /　 ／,　 /|　 ,　 ,　　　 ',
 *   　　　ｲ 　/ /--/　ｉ　L_ ﾊ ヽ!　 i
 *   　　　 ﾚ ﾍ 7ｲ／｀ﾄ　 ﾚ'ｧ-ﾄ､!ハ|　 |										
 *   　　　　 !,/7 '0'　　 ´0iソ| 　 |　　　
 *   　　　　 |.从"　　_　　 ,,,, / |./ 　 |
 *   　　　　 ﾚ'| i＞.､,,__　_,.イ / 　.i 　|
 *   　　　　　 ﾚ'| | / k_７_/ﾚ'ヽ,　ﾊ.　|
 *   　　　　　　 | |/i 〈|/　 i　,.ﾍ |　　|
 *   　　　　　　.|/ /　ｉ： 　 ﾍ!　　　　　＼|
 *   　　　 　 　 ヽ>､ﾊ 　 _,.ﾍ､ 　 /､!
 *   　　　　　　 !'〈//｀Ｔ´', ＼ ｀'7'ｰr'
 *   　　　　　　 ﾚ'ヽL__|___i,___,ンﾚ|ノ
 *   　　　　　 　　　ﾄ-,/　|___./
 *   　　　　　 　　　'ｰ'　　!_,.:
  ***********************************************************************************************/
#include <iostream>
#include "FirstAndFollow.h"
#include "PredictiveParsing.h"
#include <unordered_map>
#include<functional>
#include "Util.h"
#include "FirstRecursiveDescentImpl.h"
#include "PredictiveAnalysis.h"
#include <stack>
/// <summary>
/// 示例程序
/// </summary>
/// <returns></returns>
int main()
{
	unordered_multimap<string, string> a;
	try {
		/// <summary>
		/// First和Follow集构造
		/// </summary>
		FirstAndFollow ff;
		/// <summary>
		/// 预测分析表构造
		/// </summary>
		/// <returns></returns>
		PredictiveParsing pp;
		
		//初始化和生成式分离
		ff.init();
		ff.splitProductions();
		//找出终结符
		ff.findVtAndVn();
		//消除左递归
		//Util::eliminateTheLeftRecursion(ff.noneTerminal, ff.splitedProductions);
		//获取First和Follow集合
		ff.getFirst();
		ff.getFollow();
		//获取预测分析表
		pp.getPredictiveAnalysisTable(ff);
		//预测分析法
		PredictiveAnalysis pa("E", &ff, &pp);
		pa.init();
		//递归下降语法分析
		//FirstRecursiveDescentImpl firstRecursiveDescentImpl(ff);
		
	}
	catch (const char* e) {
		cout << "There was an error: " << endl << "\t" << e << endl;
	}
}

