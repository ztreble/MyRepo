#include "Manager.h"
Manager::Manager(string name, bool gender, string identity) :
	Person(name, gender, identity)
{

}
Manager::~Manager() {

}
void Manager::printInfo() {
	cout << endl;
	cout << "Manager Information:" << endl;
	Person::printInfo(6, (string)"name", Name(), (string)"gender", Gender() == true ? (string)"man" : (string)"woman", (string)"identity", Identity());
}