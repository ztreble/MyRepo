#include "Util.h"
#include <fstream>
#include <iostream>
#include<string>
#include "Config.h"
#include<time.h>
#include<math.h>
int Util::getInterval(int i) {
	srand((unsigned)time(NULL)+i);
	return rand() % (2) + 5;
}

std::vector<Team*> Util::getRandomTeam(int num)
{

	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;

	vector<Team*> vTeam ;
	for (int i = 0; i < num; i++) {
		cout << "TEAM [" << i+1 << "] INFORMATION AS BELOW" << endl;

		Team* team = new Team(*getRandomAthlete(true,i+1000),*getRandomAthlete(false,i+2000),*getRandomPsychologist(i+3000),*getRandomManager(i+4000),*getRandomTherapist(i+5000));
		team->printTeamInfo();
		cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
		vTeam.push_back(team);
	}


	return vTeam;
}

Athlete* Util::getRandomAthlete(bool IfMan,int root) {
	//Athlete athlete;
	Athlete* athlete = NULL;
	if (IfMan) {
		athlete=new Athlete(Util::getRandomManName(root), 0, (string)"Athlete", getRandomCapacity(root),getRandomPotential(root), getRandomInt(root));
	}

	else {
		athlete = new Athlete(Util::getRandomManName(root), 1, (string)"Athlete", getRandomCapacity(root), getRandomPotential(root), getRandomInt(root));
	}
	return athlete;

}
bool Util::getRandomGender(int root) {
	srand((unsigned)time(NULL) + root);
	int a = (rand() % (10));
	if (a > 5) return true;
	else return false;
}
string Util::getRandomManName(int root) {
	srand((unsigned)time(NULL) + root);
	ifstream readFile(MAN_NAME_FILE_PATH);
	string name;
	int a = (rand() % (MAN_NAME_FILE_LENGTH)+1);
	while (a--)
		getline(readFile, name);
	readFile.close();
	return name;
}
string Util::getRandomWomanName(int root) {
	srand((unsigned)time(NULL) + root);
	ifstream readFile(WOMAN_NAME_FILE_PATH);
	string name;
	int a = (rand() % (WOMAN_NAME_FILE_LENGTH)+1);
	while (a--)
		getline(readFile, name);
	readFile.close();
	return name;
}
int* Util::getRandomCapacity(int root) {
	srand((unsigned)time(NULL)+root);
	int *a = (int*)malloc((sizeof(int))*(PROJECT_NUM));
	for (int i = 0; i < PROJECT_NUM; i++) {
		a[i] = (rand() % (100));
	}
	return a;
}
int* Util::getRandomPotential(int root) {
	srand((unsigned)time(NULL) + root);
	int *a = (int*)malloc((sizeof(int))*(PROJECT_NUM));
	for (int i = 0; i < PROJECT_NUM; i++) {
		a[i] = (rand() % (5));
	}
	return a;
}
int Util::getRandomInt(int root) {
	srand((unsigned)time(NULL) + root);
	return (rand() % (10));
}
int Util::getRandomWeather(int root) {
	srand((unsigned)time(NULL) + root);
	return (rand() % (7));
}int Util::getRandomLocation(int root) {
	srand((unsigned)time(NULL) + root);
	return (rand() % (7));
}
Psychologist* Util::getRandomPsychologist(int root) {
	bool gender = getRandomGender(root);
	return  new Psychologist(gender==true?getRandomManName(root):getRandomWomanName(root),gender,"psychologist",getRandomInt(root));
}
Manager* Util::getRandomManager(int root) {
	bool gender = getRandomGender(root);
	return  new Manager(gender == true ? getRandomManName(root) : getRandomWomanName(root), gender, "Coach");
}
Coach* Util::getRandomCoach(int root) {
	bool gender = getRandomGender(root);
	return  new Coach(gender == true ? getRandomManName(root) : getRandomWomanName(root), gender, "Coach", getRandomInt(root));
}

Therapist* Util::getRandomTherapist(int root) {
	bool gender = getRandomGender(root);
	return new Therapist(gender == true ? getRandomManName(root) : getRandomWomanName(root), gender, "Coach", getRandomInt(root));
}

int Util::getTrackScore(double A,double B,double C,double P) {
	return (int)(A*pow(B - P, C));
}

int Util::getFieldScore(double A, double B, double C, double P) {
	return (int)(A*pow(P - B, C));
}
bool Util::getRisk(int odds, int seed) {
	srand((unsigned)time(NULL) + seed);
	int risk= (rand() % (101));
	//风险较大，为百分之二十
	if (odds == 1) {
		if (risk > 80) return true;
		else return false;
	}
	//风险较小，为百分之五
	else {
		if (risk > 95) return true;
		else return false;
	}
}