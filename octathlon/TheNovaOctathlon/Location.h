
#ifndef LOCATION_H
#define LOCATION_H
#include <string>
using namespace std;
class Location
{
public:
	Location(int);
	string getDescription();

	int getLocation() const { return location; }
	void setLocation(int val) { location = val; }
	static bool locationInfluence(Location,int);
private:
	int location;
};
#endif
