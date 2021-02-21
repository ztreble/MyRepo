
#ifndef GAME_H
#define GAME_H
#include <vector>
#include "Config.h"
#include "Team.h"
#include"Weather.h"
#include "Location.h"
#include "Score.h"
using namespace std;
class Game
{
public:
	void beginGame(vector<Team*>,int,int);
	vector<Score> thisScore;
	vector<Score> totalScore;
	vector<int> total;
private:
	vector<int> interval;
	vector<Team*> vTeam;
	void race(int session, int location, int weather);
	void settlement();
	void totalSettlement();
	int num;
	int times;
		
};
#endif
