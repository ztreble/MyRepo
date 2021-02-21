#include "Therapist.h"
Therapist::Therapist(string name, bool gender, string identity, int capacity)
	:Person(name, gender, identity), capacity(capacity)
{
}

Therapist::~Therapist()
{
}

void Therapist::printInfo() {
	cout << endl;
	cout << "Therapist Information:" << endl;
	Person::printInfo(8, (string)"name", Name(), (string)"gender", Gender() == true ? (string)"man" : (string)"woman", (string)"identity", Identity(), (string)"capacity", (string)("" + capacity));
}