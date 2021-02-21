#include "Location.h"
#include "Util.h"
Location::Location(int location)
	:location(location)
{

}

bool Location::locationInfluence(Location nowLocation,int seed){
	return Util::getRisk(nowLocation.location, seed);
}
string Location::getDescription() {
	if (!(location >= 0 && location < 7)) return (string)"wrong weather...please check system.";
	switch (location) {
	case 0: {//峡谷
		return (string)"Here is a canyon, strange but a canyon.";
	}
	case 1: {//平地
		return (string)"The flat terrain makes it a good place for racing";
	}
	case 2: {//火山
		return (string)"There are volcanoes here. Who on earth decided to compete where there are volcanoes!";
	}
	case 3: {//丘陵
		return (string)"There is a hill 50 meters high, which is not surprising.";
	}
	case 4: {//兔子洞
		return (string)"There are rabbit holes everywhere -- but no Alice";
	}
	case 5: {//狮子
		return (string)"There are prairies and lions -- shouldn't they be in zoos?";
	}
	case 6: {//积水
		return (string)"There's some standing water -- is there any chance our men will drown?";
	}
	}
	return (string)"Sorry,something wrong.";
}