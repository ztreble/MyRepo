#include "Person.h"
#include "Util.h"
Person::Person(string name,string description)
	:name(name),description(description),location(Util::getRandomNum(1,12))
{

}