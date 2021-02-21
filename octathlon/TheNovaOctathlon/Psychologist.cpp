#include "Psychologist.h"
Psychologist::Psychologist(string name, bool gender, string identity, int capacity)
:Person(name,gender,identity),capacity(capacity)
{
}

Psychologist::~Psychologist()
{
}

void Psychologist::printInfo() {
	cout << endl<<endl;
	cout << "Psychologist Information:" << endl;
	Person::printInfo(8, (string)"name", Name(), (string)"gender", Gender() == true ? (string)"man" : (string)"woman", (string)"identity", Identity(), (string)"capacity", (string)("" + capacity));
}