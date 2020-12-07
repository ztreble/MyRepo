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
int main()
{
	vector<string> production;


	production.emplace_back("S  -> AB | W | C | Q ");
	production.emplace_back("A  -> Ca | ~ ");
	production.emplace_back("B  -> cB' ");
	production.emplace_back("B' -> aACB' | ~ ");
	production.emplace_back("C  -> b | ~ ");
	try {
		getNonTerminal(production); 
	}catch (const char* msg) {
		cerr << msg << endl;
	}
    return 0;
}

