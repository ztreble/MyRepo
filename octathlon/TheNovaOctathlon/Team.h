#ifndef TEAM_H
#define TEAM_H
#include "Athlete.h"
#include "Psychologist.h"
#include "Manager.h"
#include "Therapist.h"
#include "Coach.h"
class Team
{
public:
	Team() = default;
	~Team() = default;
	Team(Athlete,Athlete,Psychologist,Manager,Therapist);
	double points;
	void relax(int time);
	void printTeamInfo();
	Athlete getAthleteFirst() const { return athleteFirst; }
	void setAthleteFirst(Athlete val) { athleteFirst = val; }
	Athlete getAthleteSecond() const { return athleteSecond; }
	void setAthleteSecond(Athlete val) { athleteSecond = val; }
	Psychologist getPsychologist() const { return psychologist; }
	Manager getManager() const { return manager; }
	void setManager(Manager val) { manager = val; }
	Therapist getTherapist() const { return therapist; }
	void setTherapist(Therapist val) { therapist = val; }
private:
	Athlete athleteFirst;
	Athlete athleteSecond;
	Psychologist psychologist;
	Manager manager;
	Therapist therapist;
	//Coach coach_field;
	//Coach coach_track;
};

#endif