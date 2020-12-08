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

int main()
{
	unordered_map<string, unordered_map<string, unordered_multimap<string, unordered_set<string>>>> a = {};
	unordered_multimap<string, unordered_set<string>> q;
	q.insert(pair<string, unordered_set<string>>{"a", { "sdaf","dsaf" }});
	FirstAndFollow ff;
	PredictiveParsing pp;
	try {
		ff.init();
		ff.splitProductions();
		ff.findVtAndVn();
		ff.getFirst();
		ff.getFollow();
		pp.getPredictiveAnalysisTable(ff);
	}
	catch (const char* e) {
		cout << "There was an error: " << endl << "\t" << e << endl;
	}
	
}

