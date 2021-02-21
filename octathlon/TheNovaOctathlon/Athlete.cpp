#include "Athlete.h"


Athlete::Athlete(string name, bool gender, string identity, int* capacity, int* potential, int characterstic):
	Person(name,gender,identity),characterstic(characterstic),status(1.0)
{
	memset(this->capacity, 0, PROJECT_NUM_SIZE);
	memcpy(this->capacity, capacity, PROJECT_NUM_SIZE);
	memset(this->potential, 0, PROJECT_NUM_SIZE);
	memcpy(this->potential, potential, PROJECT_NUM_SIZE);
}

void Athlete::printInfo() {
	//this->printInfo
	cout << endl;
	cout << "Athlete Information:" << endl;
	Person::printInfo(10, (string)"name", Name(), (string)"gender", Gender()==true?(string)"man":(string)"woman", (string)"identity", Identity(), (string)"characterstic", Person::getCharacterstic(characterstic));
	cout << "Capacity : ";
	for (int i = 0; i < PROJECT_NUM; i++)
		cout << capacity[i] << " ";
	cout << endl << "Potential : ";
	for (int i = 0; i < PROJECT_NUM; i++)
		cout << potential[i] << " ";
}
