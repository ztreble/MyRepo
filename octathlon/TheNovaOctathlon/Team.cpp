#include "Team.h"
Team::Team(Athlete athleteFirst, Athlete athleteSecond, Psychologist psychologist, Manager manager, Therapist therapist)
	:athleteFirst(athleteFirst),athleteSecond(athleteSecond),psychologist(psychologist),manager(manager),therapist(therapist)
{

}

void Team::printTeamInfo() {
	getAthleteFirst().printInfo();
	getAthleteSecond().printInfo();
	getPsychologist().printInfo();
	getManager().printInfo();
	getTherapist().printInfo();
}