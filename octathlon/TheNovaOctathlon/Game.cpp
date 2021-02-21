#include "Game.h"
#include "Util.h"

#include<vector>
#include<string>
#include <iostream>
#include <algorithm>
#include <iomanip>

using namespace std;
void Game::beginGame(vector<Team*> team, int number, int times) {
	this->num = number;
	this->times = times;
	vTeam = team;
	cout << "The interval between matches will be decided at random between five and seven days." << endl;
	cout << "The players will be rested and promoted during this period, so the competition is still in suspense!" << endl;
	cout << "Now the draw begins..." << endl;
	cout << "The draw results at each interval are :" << endl;
	for (int i = 0; i < times; i++) {
		int tmp = Util::getInterval(i);
		cout << tmp << " ";
		this->interval.push_back(tmp);
	}
	cout <<endl<< "OK,Now the game begins." << endl;
	for (int j = 0; j < number; j++)
	{
		Score score(j);
		int i=0;
		totalScore.push_back(score);
		total.push_back(i);
	}
	for (int i = 0; i < times; i++) {
		//初始化每一次的比赛成绩
		for(int j=0;j<number;j++)
		{
			Score score(j);
			thisScore.push_back(score);
		}
		cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
		cout << "This is the " << (i+1) << " th race." << endl;
		cout << "The weather is as follows:" << endl;
		Weather* weather = new Weather(Util::getRandomWeather(i + 100));
		cout << weather->getDescription() << endl;
		cout << endl;
		cout << "The location is as follows:" << endl;
		Location* location = new Location(Util::getRandomLocation(i + 100));
		cout << location->getDescription() << endl;
		cout << endl;

		for(int i=0;i<8;i++)
			race(i, (*location).getLocation(), (*weather).getWeather());

		cout <<endl<< "How are our players doing after this game?Let's take a look!" << endl;
		int mark = 1;
		for (vector<Team*>::iterator iter = vTeam.begin(); iter != vTeam.end(); iter++) {
			cout << endl;
			cout << "TEAM " <<mark++<<":";
			(*iter)->printTeamInfo();
		}
		settlement();
		delete weather;
		delete location;
		while (!thisScore.empty())
			thisScore.pop_back();
	}
	totalSettlement();


}

void Game::race(int session, int location, int weather) {
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	switch (session) {
	case 0: {
		cout << "Now the 100-meter run begins" << endl;
		break;
	}
	case 1: {
		cout << "Now the discus begins" << endl;
		break;
	}
	case 2: {
		cout << "Now the longJump begins" << endl;
		break;
	}
	case 3: {
		cout << "Now the walker begins" << endl;
		break;
	}
	case 4: {
		cout << "Now the javelin begins" << endl;
		break;
	}
	case 5: {
		cout << "Now the hurdles begins" << endl;
		break;
	}
	case 6: {
		cout << "Now the highJump begins" << endl;
		break;
	}
	case 7: {
		cout << "Now the oneThousandFiveHundredRace begins" << endl;
		break;
	}
	}

	for (int i = 0; i < vTeam.size(); i++) {

		Athlete first = ((vTeam.at(i)))->getAthleteFirst();
		Athlete second = ((vTeam.at(i)))->getAthleteSecond();

		switch (session) {
		case 0: {
			double firstTime = 5 + (((double)100 / first.capacity[0]) / Weather::weatherInfluence(first.characterstic, weather)) / first.status;//用时
			double secondTime = 5 + (((double)100 / second.capacity[0]) / Weather::weatherInfluence(second.characterstic, weather)) / first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(2) << firstTime << "s In the 100-meter run!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(2) << secondTime << "s In the 100-meter run!" << endl;
			int scoreFirst = Util::getTrackScore(25.4347, 18, 1.81, firstTime);
			int scoreSecond = Util::getTrackScore(25.4347, 18, 1.81, secondTime);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).oneHundredRace[0] = scoreFirst;
			thisScore.at(i).oneHundredRace[1] = scoreSecond;

			cout << "100-meter run team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			totalScore.at(i).oneHundredRace[0] += scoreFirst;
			totalScore.at(i).oneHundredRace[1] += scoreSecond;
			total.at(i) += (scoreFirst + scoreSecond);
			break;
		}
		case 1: {
			double firstLength = 5 + ((double)first.capacity[1] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			double secondLength = 5 + ((double)second.capacity[1] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(4) << firstLength << "m In the discus!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(4) << secondLength << "m In the discus!" << endl;
			int scoreFirst = Util::getFieldScore(12.91, 4, 1.1, firstLength);
			int scoreSecond = Util::getFieldScore(12.91, 4, 1.1, secondLength);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).discus[0] = scoreFirst;
			thisScore.at(i).discus[1] = scoreSecond;
			cout << "Discus throw team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			totalScore.at(i).discus[0] += scoreFirst;
			totalScore.at(i).discus[1] += scoreSecond;
			
			break;
		}
		case 2: {
			double firstLength = 5 + 0.045*((double)first.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			double secondLength = 5 + 0.045*((double)second.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(4) << firstLength << "m In the longJump!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(4) << secondLength << "m In the longJump!" << endl;
			int scoreFirst = Util::getFieldScore(0.14354, 220, 1.4, firstLength*100);
			int scoreSecond = Util::getFieldScore(0.14354, 220, 1.4, secondLength*100);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).longJump[0] = scoreFirst;
			thisScore.at(i).longJump[1] = scoreSecond;
			cout << "longJump team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;
			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			totalScore.at(i).longJump[0] += scoreFirst;
			totalScore.at(i).longJump[1] += scoreSecond;
			break;
		}
		case 3: {
			double firstTime = 40 + (((double)100 / first.capacity[0]) / Weather::weatherInfluence(first.characterstic, weather)) / first.status;//用时
			double secondTime = 40 + (((double)100 / second.capacity[0]) / Weather::weatherInfluence(second.characterstic, weather)) / first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(2) << firstTime << "s In the walker!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(2) << secondTime << "s In the walker!" << endl;
			int scoreFirst = Util::getTrackScore(1.53775,82,1.81, firstTime);
			int scoreSecond = Util::getTrackScore(1.53775, 82, 1.81, secondTime);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).walker[0] = scoreFirst;
			thisScore.at(i).walker[1] = scoreSecond;
			cout << "walker team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			totalScore.at(i).walker[0] += scoreFirst;
			totalScore.at(i).walker[1] += scoreSecond;
			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			break;

		}
		case 4: {
			double firstLength = 5 + 0.035*((double)first.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			double secondLength = 5 + 0.035*((double)second.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(4) << firstLength << "m In the longJump!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(4) << secondLength << "m In the longJump!" << endl;
			int scoreFirst = Util::getFieldScore(0.14354, 220, 1.4, firstLength*100);
			int scoreSecond = Util::getFieldScore(0.14354, 220, 1.4, secondLength*100);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).longJump[0] = scoreFirst;
			thisScore.at(i).longJump[1] = scoreSecond;
			cout << "longJump team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			totalScore.at(i).longJump[0] += scoreFirst;
			totalScore.at(i).longJump[1] += scoreSecond;
			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			break;
		}
		case 5: {
			double firstLength = 50 + 0.4*((double)first.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			double secondLength = 50 + 0.4*((double)second.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(4) << firstLength << "m In the javelin!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(4) << secondLength << "m In the javelin!" << endl;
			int scoreFirst = Util::getFieldScore(10.14,7,1.08, firstLength);
			int scoreSecond = Util::getFieldScore(10.14, 7, 1.08, secondLength);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).javelin[0] = scoreFirst;
			thisScore.at(i).javelin[1] = scoreSecond;
			cout << "javelin team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			totalScore.at(i).javelin[0] += scoreFirst;
			totalScore.at(i).javelin[1] += scoreSecond;
			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			break;
		}
		case 6: {
			double firstLength = 4 + 0.02*((double)first.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			double secondLength = 4 + 0.02*((double)second.capacity[2] / 10)*Weather::weatherInfluence(second.characterstic, weather)*first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(4) << firstLength << "m In the highJump!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(4) << secondLength << "m In the highJump!" << endl;
			int scoreFirst = Util::getFieldScore(0.2797,100,1.35, firstLength*100);
			int scoreSecond = Util::getFieldScore(0.2797, 100, 1.35, secondLength*100);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).highJump[0] = scoreFirst;
			thisScore.at(i).highJump[1] = scoreSecond;
			cout << "highJump team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			totalScore.at(i).highJump[0] += scoreFirst;
			totalScore.at(i).highJump[1] += scoreSecond;
			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			break;
		}
		case 7: {
			double firstTime = 180 + (((double)100 / first.capacity[0]) / Weather::weatherInfluence(first.characterstic, weather)) / first.status;//用时
			double secondTime = 180 + (((double)100 / second.capacity[0]) / Weather::weatherInfluence(second.characterstic, weather)) / first.status;
			cout << "team " << i << ": " << first.Name() << " got a score of " << setprecision(2) << firstTime << "s In the oneThousandFiveHundredRace!" << endl;
			cout << "team " << i << ": " << second.Name() << " got a score of " << setprecision(2) << secondTime << "s In the oneThousandFiveHundredRace!" << endl;
			int scoreFirst = Util::getTrackScore(0.03768,480,1.85, firstTime);
			int scoreSecond = Util::getTrackScore(0.03768, 480, 1.85, secondTime);
			scoreFirst = (scoreFirst > 1000 ? 1000 : scoreFirst);
			scoreSecond = (scoreSecond > 1000 ? 1000 : scoreSecond);
			thisScore.at(i).oneThousandFiveHundredRace[0] = scoreFirst;
			thisScore.at(i).oneThousandFiveHundredRace[1] = scoreSecond;
			cout << "oneThousandFiveHundredRace team " << i << " athlete A Score : " << scoreFirst << " athlete B Score : " << scoreSecond;

			totalScore.at(i).oneThousandFiveHundredRace[0] += scoreFirst;
			totalScore.at(i).oneThousandFiveHundredRace[1] += scoreSecond;
			thisScore.at(i).total += (scoreFirst + scoreSecond);
			totalScore.at(i).total += (scoreFirst + scoreSecond);

			break;
		}
		}
		//修改状态
		if (first.status != 1.0) {
			if (!Weather::weatherInfluence(weather, session + 1000)) {
				Athlete a= vTeam.at(i)->getAthleteFirst();
				a.status = 0.8;
				vTeam.at(i)->setAthleteFirst(a);
			}
		}
		if (second.status!= 1.0) {
			if (!Weather::weatherInfluence(weather, session + 1000)) {
				Athlete a = vTeam.at(i)->getAthleteSecond();
				a.status = 0.8;
				vTeam.at(i)->setAthleteFirst(a);
			}
		}
		cout << endl;
	}
}
bool mySort(const Score &pre,const Score &lst) {
	return pre.total > lst.total;
}

void Game::settlement() {
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	cout << "It's time to settle up for this contest!" << endl;

	if (thisScore.empty())
		return;


	sort(thisScore.begin(), thisScore.end(), mySort);
	cout << "The winner is: " << endl;
	cout << "Team " << thisScore.at(0).num << endl;
	cout << "Their fractions is:" << endl;
	cout << thisScore.at(0).total << endl;
	if (this->num == 1) return;
	cout << "The runner-up is: " << endl;
	cout << "Team " << thisScore.at(1).num << endl;
	cout << "Their fractions is:" << endl;
	cout << thisScore.at(1).total << endl;
	if (this->num == 2) return;
	cout << "the third runner-up is:" << endl;
	cout << "Team " << "Team " << thisScore.at(2).num << endl;
	cout << "Their fractions is:" << endl;
	cout << thisScore.at(2).total << endl;
	if (this->num == 3) return;
	cout << "The rest are ranked in order:" << endl;
	for (int j = 3; j < this->num; j++) {
		cout << "Team " << thisScore.at(0).num << endl;
		cout << "Their fractions is:" << endl;
		cout << thisScore.at(2).total << endl;
	}
	
}
void Game::totalSettlement() {
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	cout << "Now it's the final and exciting time to present the trophy" << endl;

	if (totalScore.empty())
		return;

	sort(totalScore.begin(), totalScore.end(), mySort);
	cout << "The winner is: " << endl;
	cout <<"Team "<< totalScore.at(0).num << endl;
	cout << "Their fractions is:" << endl;
	cout << totalScore.at(0).total << endl;
	if (this->num == 1) return;
	cout << "The runner-up is: " << endl;
	cout << "Team " << totalScore.at(1).num << endl;
	cout << "Their fractions is:" << endl;
	cout << totalScore.at(1).total << endl;
	if (this->num == 2) return;
	cout << "the third runner-up is:" << endl;
	cout << "Team " << totalScore.at(2).num << endl;
	cout << "Their fractions is:" << endl;
	cout << totalScore.at(2).total << endl;
	if (this->num == 3) return;
	cout << "The rest are ranked in order:" << endl;
	for (int j = 3; j < this->num; j++) {
		
		cout << "Team " << totalScore.at(0).num << endl;
		cout << "Their fractions is:" << endl;
		cout << totalScore.at(2).total << endl;
	}
}

//铁饼

//void Game::longJump(vector<Team*>, Location, Weather);//跳远
//void Game::walker(vector<Team*>, Location, Weather);	//竞走
//
//void Game::javelin(vector<Team*>, Location, Weather);	//标枪
//void Game::hurdles(vector<Team*>, Location, Weather);	//跨栏
//void Game::highJump(vector<Team*>, Location, Weather);//跳高
//void Game::oneThousandFiveHundredRace(vector<Team*>, Location, Weather);	//1500