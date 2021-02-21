
#ifndef WEATHER_H
#define WEATHER_H
#include <string>
using namespace std;
class Weather
{
public:
	Weather(int);
	string getDescription();
	int getWeather() const { return weather; }
	void setWeather(int val) { this->weather = val; }
	static double weatherInfluence(int, int);
private:
	int weather;
};



#endif