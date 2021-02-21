#include "Coach.h"

Coach::Coach(string name, bool gender, string identity, int capacity) :
	Person(name, gender, identity), capacity(capacity)
{

}
Coach::~Coach() {

}
void Coach::printInfo() {
	cout << endl;
	cout << "Coach Information:" << endl;

	Person::printInfo(8, (string)"name", Name(), (string)"gender", Gender() == true ? (string)"man" : (string)"woman", (string)"identity", Identity(), (string)"capacity", (string)("" + capacity));
} 